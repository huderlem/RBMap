import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class responsible for loading and holding a map's object data.
 */
public class ObjectData 
{
    int borderTile;
    int[][] warpData;
    int[][] signData;
    int[][] npcData;
    int[][] trainerData;
    int[][] itemData;
    int[][] warpToData;
    
    /**
     * Creates an ObjectData instance for a map by reading in the data from the ROM
     * @param rom The game ROM file.
     * @param addr Address of object data to load.
     * @return ObjectData object
     * @throws java.io.IOException
     */
    public static ObjectData loadObjectData(RandomAccessFile rom, long addr) throws IOException 
    {
        rom.seek(addr);
        ObjectData od = new ObjectData();
        
        // Read map's object data while setting the ObjectData instance's values.
        od.borderTile = rom.readByte()&0xff;
        
        // Read warp data.
        int numWarps = rom.readByte()&0xff;
        od.warpData = new int[numWarps][4];
        for (int i = 0; i < numWarps; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                od.warpData[i][j] = rom.readByte()&0xff;
            }
        }
        
        // Read sign data.
        int numSigns = rom.readByte()&0xff;
        od.signData = new int[numSigns][3];
        for (int i = 0; i < numSigns; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                od.signData[i][j] = rom.readByte()&0xff;
            }
        }
        
        // Read objects. (people, trainers, items)
        int numObjects = rom.readByte()&0xff;
        // Use ArrayList because initially, we don't know how many of each object type there will be.
        ArrayList<int[]> peopleData = new ArrayList<int[]>();
        ArrayList<int[]> trainerData = new ArrayList<int[]>();
        ArrayList<int[]> itemData = new ArrayList<int[]>();
        for (int k = 0; k < numObjects; k++)
        {
            // Assume it's a normal NPC, which has a 6-byte entry.
            int[] curObject = new int[6];
            for (int i = 0; i < 5; i++)
            {
                curObject[i] = rom.readByte()&0xff;
            }
            
            // Text number.
            int textNum = rom.readByte()&0xff;
            curObject[5] = textNum;
            
            // If text number has bit 6 set, then it's a trainer object.
            // If text number has bit 7 set, then it's an item object.
            // If both bits 6 and 7 are reset, then it's a normal NPC object.
            if ((textNum & (1<<6)) != 0 )// Trainer object
            {
                int[] trainerObj = new int[8];
                System.arraycopy(curObject, 0, trainerObj, 0, curObject.length);
                trainerObj[6] = rom.readByte()&0xff;
                trainerObj[7] = rom.readByte()&0xff;
                trainerData.add(trainerObj);
            }
            else if ((textNum & (1<<7)) != 0) // Item object
            {
                int[] itemObj = new int[7];
                System.arraycopy(curObject, 0, itemObj, 0, curObject.length);
                itemObj[6] = rom.readByte()&0xff;
                itemData.add(itemObj);
            } 
            else // Normal NPC object
            {
                peopleData.add(curObject);
            }
        }
        
        // Copy the data from the ArrayLists into the ObjectData instance's int arrays.
        od.npcData = new int[peopleData.size()][6];
        for (int i = 0; i < peopleData.size(); i++)
        {
            od.npcData[i] = peopleData.get(i);
        }

        od.trainerData = new int[trainerData.size()][8];
        for (int i = 0; i < trainerData.size(); i++)
        {
            od.trainerData[i] = trainerData.get(i);
        }

        od.itemData = new int[itemData.size()][7];
        for (int i = 0; i < itemData.size(); i++)
        {
            od.itemData[i] = itemData.get(i);
        }

        od.warpToData = new int[numWarps][4];
        for (int i = 0; i < numWarps; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                od.warpToData[i][j] = rom.readByte()&0xff;
            }
        }
        
        return od;
    }

    /**
     * Adds images to the Map to represent locations of warps/npcs/etc. on the
     * map view.
     * @param panel JPanel
     * @param curMap MapData
     * @param f MainFrame
     */
    public void addToOverlay(JPanel panel, MapData curMap, MainFrame f) {
        // Width and height are doubled because the map is made up of 2x2 blocks,
        // but objects are only 1x1 blocks.
        final int mapWidth = curMap.width * 2;
        final int mapHeight = curMap.height * 2;
        
        // Add an image to the map view for each warp.
        URL warpImageURL = MainFrame.class.getResource("/warp.png");
        for (int i = 0; i < curMap.objectData.warpData.length; i++)
        {
            final int[] warp = curMap.objectData.warpData[i];
            // Create a JLabel that you can click on and move around.
            final JLabel label = new JLabel(new ImageIcon(warpImageURL));
            label.setBounds(warp[1]*Tileset.TILE_WIDTH*2, warp[0]*Tileset.TILE_HEIGHT*2, 
                    Tileset.TILE_WIDTH*2, Tileset.TILE_HEIGHT*2);
            // Allow user to drag the warp around on the map.
            label.addMouseMotionListener(new MouseAdapter () {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (e.getModifiers() == MouseEvent.BUTTON3_MASK)
                    {
                        Point panelCoords = e.getComponent().getParent().getLocationOnScreen();
                        int newX = Math.min(Math.max((e.getXOnScreen() - panelCoords.x) / (Tileset.TILE_WIDTH * 2), 0), mapWidth - 1);
                        int newY = Math.min(Math.max((e.getYOnScreen() - panelCoords.y) / (Tileset.TILE_HEIGHT * 2), 0), mapHeight - 1);
                        label.setBounds(newX*Tileset.TILE_WIDTH * 2, newY * Tileset.TILE_HEIGHT * 2, Tileset.TILE_WIDTH * 2, Tileset.TILE_HEIGHT * 2);
                        warp[0] = newY;
                        warp[1] = newX;
                    }
                }
            });
            
            // Add the JLabel to the map view.
            panel.add(label);
        }
        
        // Add an image to the map view for each item.
        URL itemImageURL = MainFrame.class.getResource("/item.png");
        for (int i = 0; i < curMap.objectData.itemData.length; i++)
        {
            final int[] item = curMap.objectData.itemData[i];
            // Create a JLabel that you can click on and move around.
            final JLabel label = new JLabel(new ImageIcon(itemImageURL));
            label.setBounds((item[2]-4)*Tileset.TILE_WIDTH*2, (item[1]-4)*Tileset.TILE_HEIGHT*2, 
                    Tileset.TILE_WIDTH*2, Tileset.TILE_HEIGHT*2);
            
            // Allow user to drag the item around on the map.
            label.addMouseMotionListener(new MouseAdapter () {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (e.getModifiers() == MouseEvent.BUTTON3_MASK)
                    {
                        Point panelCoords = e.getComponent().getParent().getLocationOnScreen();
                        int newX = Math.min(Math.max((e.getXOnScreen() - panelCoords.x) / (Tileset.TILE_WIDTH * 2), 0), mapWidth - 1);
                        int newY = Math.min(Math.max((e.getYOnScreen() - panelCoords.y) / (Tileset.TILE_HEIGHT * 2), 0), mapHeight - 1);
                        label.setBounds(newX*Tileset.TILE_WIDTH * 2, newY * Tileset.TILE_HEIGHT * 2, Tileset.TILE_WIDTH * 2, Tileset.TILE_HEIGHT * 2);
                        item[1] = newY + 4;
                        item[2] = newX + 4;
                    }
                }
            });
            
            // Add the JLabel to the map view.
            panel.add(label);
        }
        
        // Add an image to the map view for each npc.
        URL npcImageURL = MainFrame.class.getResource("/npc.png");
        for (int i = 0; i < curMap.objectData.npcData.length; i++) 
        {
            final int[] npc = curMap.objectData.npcData[i];
            // Create a JLabel that you can click on and move around.
            final JLabel label = new JLabel(new ImageIcon(npcImageURL));
            label.setBounds((npc[2]-4)*Tileset.TILE_WIDTH*2, (npc[1]-4)*Tileset.TILE_HEIGHT*2, 
                    Tileset.TILE_WIDTH*2, Tileset.TILE_HEIGHT*2);
            
            // Allow user to drag the npc around on the map.
            label.addMouseMotionListener(new MouseAdapter () {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (e.getModifiers() == MouseEvent.BUTTON3_MASK)
                    {
                        Point panelCoords = e.getComponent().getParent().getLocationOnScreen();
                        int newX = Math.min(Math.max((e.getXOnScreen() - panelCoords.x) / (Tileset.TILE_WIDTH * 2), 0), mapWidth - 1);
                        int newY = Math.min(Math.max((e.getYOnScreen() - panelCoords.y) / (Tileset.TILE_HEIGHT * 2), 0), mapHeight - 1);
                        label.setBounds(newX*Tileset.TILE_WIDTH * 2, newY * Tileset.TILE_HEIGHT * 2, Tileset.TILE_WIDTH * 2, Tileset.TILE_HEIGHT * 2);
                        npc[1] = newY + 4;
                        npc[2] = newX + 4;
                    }
                }
            });
            
            // Add the JLabel to the map view.
            panel.add(label);
        }
        
        // Add an image to the map view for each trainer.
        URL trainerImageURL = MainFrame.class.getResource("/trainer.png");
        for (int i = 0; i < curMap.objectData.trainerData.length; i++)
        {
            final int[] trainer = curMap.objectData.trainerData[i];
            // Create a JLabel that you can click on and move around.
            final JLabel label = new JLabel(new ImageIcon(trainerImageURL));
            label.setBounds((trainer[2]-4)*Tileset.TILE_WIDTH*2, (trainer[1]-4)*Tileset.TILE_HEIGHT*2, 
                    Tileset.TILE_WIDTH*2, Tileset.TILE_HEIGHT*2);
            
            // Allow user to drag the trainer around on the map.
            label.addMouseMotionListener(new MouseAdapter () {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (e.getModifiers() == MouseEvent.BUTTON3_MASK)
                    {
                        Point panelCoords = e.getComponent().getParent().getLocationOnScreen();
                        int newX = Math.min(Math.max((e.getXOnScreen() - panelCoords.x) / (Tileset.TILE_WIDTH * 2), 0), mapWidth - 1);
                        int newY = Math.min(Math.max((e.getYOnScreen() - panelCoords.y) / (Tileset.TILE_HEIGHT * 2), 0), mapHeight - 1);
                        label.setBounds(newX * Tileset.TILE_WIDTH * 2, newY * Tileset.TILE_HEIGHT * 2, Tileset.TILE_WIDTH * 2, Tileset.TILE_HEIGHT * 2);
                        trainer[1] = newY + 4;
                        trainer[2] = newX + 4;
                    }
                }
            });
            
            // Add the JLabel to the map view.
            panel.add(label);
        }
        
        // Add an image to the map view for each sign.
        URL signImageURL = MainFrame.class.getResource("/sign.png");
        for (int i = 0; i < curMap.objectData.signData.length; i++)
        {
            final int[] sign = curMap.objectData.signData[i];
            // Create a JLabel that you can click on and move around.
            final JLabel label = new JLabel(new ImageIcon(signImageURL));
            label.setBounds(sign[1]*Tileset.TILE_WIDTH*2, sign[0]*Tileset.TILE_HEIGHT*2, 
                    Tileset.TILE_WIDTH*2, Tileset.TILE_HEIGHT*2);
            
            // Allow user to drag the sign around on the map.
            label.addMouseMotionListener(new MouseAdapter () {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (e.getModifiers() == MouseEvent.BUTTON3_MASK)
                    {
                        Point panelCoords = e.getComponent().getParent().getLocationOnScreen();
                        int newX = Math.min(Math.max((e.getXOnScreen() - panelCoords.x)/(Tileset.TILE_WIDTH * 2), 0), mapWidth - 1);
                        int newY = Math.min(Math.max((e.getYOnScreen() - panelCoords.y)/(Tileset.TILE_HEIGHT * 2), 0), mapHeight - 1);
                        label.setBounds(newX*Tileset.TILE_WIDTH * 2, newY * Tileset.TILE_HEIGHT * 2, Tileset.TILE_WIDTH * 2, Tileset.TILE_HEIGHT * 2);
                        sign[0] = newY;
                        sign[1] = newX;
                    }
                }
            });
            
            // Add the JLabel to the map view.
            panel.add(label);
        }
    }
}
