import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Class for loading and displaying the tilesets in the game.
 */
public class Tileset {
    
    public static int[] COLORS = new int[4];
    public final static int TILE_WIDTH = 8;
    public final static int TILE_HEIGHT = 8;
    
    private static long tilesetHeadersAddress;
    
    public static Tileset[] tilesets;
    
    int bank;
    int blocksPointer;
    int tilesPointer;
    int collisionDataPointer;
    int[] talkingOverTiles = new int[3]; // 0xff if they aren't used; up to three total
    int grassTile; // 0xff if unused
    int animationFlag;
    
    BufferedImage[] tiles;
    BufferedImage[] blocks;
    
    /**
     * Reads the config file for some addresses and the color palette.
     */
    public static void LoadAdressesJSON() {
        File config = new File("Config/config.json");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(config));
            JSONParser parser = new JSONParser();
            try {
                Map json = (Map) parser.parse(reader);
                tilesetHeadersAddress = Long.decode((String)json.get("Tilesets"));
                COLORS[0] = Integer.decode((String)json.get("color0"));
                COLORS[1] = Integer.decode((String)json.get("color1"));
                COLORS[2] = Integer.decode((String)json.get("color2"));
                COLORS[3] = Integer.decode((String)json.get("color3"));
            } catch (IOException ex) {
                Logger.getLogger(MapData.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(MapData.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MapData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Reads a tile from the ROM and returns an 8x8-pixel BufferedImage.
     */
    private static BufferedImage readTile(byte[] tileBytes) {
        BufferedImage tileImg = new BufferedImage(TILE_WIDTH, TILE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        int[] convertedBytes = new int[TILE_WIDTH*TILE_HEIGHT];
        // Perform interlace merge to load the 2-bit per pixel format.
        for (int i = 0; i < TILE_HEIGHT; i++)
        {
            int lo = tileBytes[i*2];
            int hi = tileBytes[i*2+1];
            for (int j = 0; j < 8; j++)
            {
                int bit = 7 - j;
                int colorIndex = (((hi>>bit)&1)<<1) | ((lo>>bit)&1);
                convertedBytes[i*TILE_WIDTH + j] = COLORS[colorIndex];
            }
        }
        
        tileImg.getRaster().setDataElements(0, 0, TILE_WIDTH, TILE_HEIGHT, convertedBytes);
        return tileImg;
    }
    
    /**
     * Reads and creates the tileset blocks pointed to by @addr.
     * @param addr
     * @param file
     * @param tiles
     * @return BufferedImage[] representing the tileset blocks
     * @throws IOException 
     */
    public static BufferedImage[] getBlocks(long addr, RandomAccessFile file, BufferedImage[] tiles) throws IOException {
        BufferedImage[] blocks = new BufferedImage[256]; // maximum of 256 blocks
        for (int i = 0; i < blocks.length; i++)
        {
            blocks[i] = createBlock(tiles, addr + i*16, file); // every block is 16 bytes
        }
        
        return blocks;
    }
    
    /**
     * Creates an image representing a map block (4x4-tile block).
     * @param tiles
     * @param blockAddr address of blocks
     * @param rom
     * @return BufferedImage depicting the block
     * @throws java.io.IOException 
     */
    public static BufferedImage createBlock(BufferedImage[] tiles, long blockAddr, RandomAccessFile rom) throws IOException {
        rom.seek(blockAddr);
        BufferedImage blockImg = new BufferedImage(TILE_WIDTH*4, TILE_HEIGHT*4, BufferedImage.TYPE_INT_RGB);
        // Blocks are made up of 16 consecutive bytes in the ROM.
        byte[] blockBytes = new byte[16];
        rom.read(blockBytes, 0, 16);
        for (int i = 0; i < blockBytes.length; i++)
        {
            Graphics g = blockImg.getSubimage((i%4)*TILE_WIDTH, (i/4)*TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT).getGraphics();
            g.drawImage(tiles[blockBytes[i]&0xff], 0, 0, TILE_WIDTH, TILE_HEIGHT, null);
        }
        
        return blockImg;
    }
    
    /**
     * Creates an array of the tiles stored starting at the given address.
     * @param addr address of tiles
     * @param rom
     * @return BufferedImage[] representing each tile.
     * @throws IOException 
     */
    public static BufferedImage[] getTiles(long addr, RandomAccessFile rom) throws IOException {
        BufferedImage[] tiles = new BufferedImage[256]; // maximum of 256 tiles
        rom.seek(addr);
        for (int i = 0; i < tiles.length; i++)
        {
            byte[] bytes = new byte[16];
            rom.read(bytes, 0, 16);
            tiles[i] = readTile(bytes);
        }
        
        return tiles;
    }
    
    /**
     * Load the tiles and blocks of the given tileset number.
     * @param tilesetNum
     * @param rom
     * @return
     * @throws IOException 
     */
    public static Tileset loadTileset(int tilesetNum, RandomAccessFile rom) throws IOException {
        rom.seek(tilesetHeadersAddress + tilesetNum * 12); // 12 bytes per tileset header
        
        // read the tileset's headers and create a Tileset object
        Tileset ts = new Tileset();
        ts.bank = rom.readByte()&0xff;
        ts.blocksPointer = MapData.readPointer(rom)%0x4000 + ts.bank*0x4000;
        ts.tilesPointer = MapData.readPointer(rom)%0x4000 + ts.bank*0x4000;
        // TODO: collisionData is probably bugged here...
        ts.collisionDataPointer = MapData.readPointer(rom)%0x4000 + ((int)(tilesetHeadersAddress/0x4000))*0x4000;
        for (int i = 0; i < 3; i++)
        {
            ts.talkingOverTiles[i] = rom.readByte()&0xff;
        }
        
        ts.grassTile = rom.readByte()&0xff;
        ts.animationFlag = rom.readByte()&0xff;
        ts.tiles = getTiles(ts.tilesPointer, rom);
        ts.blocks = getBlocks(ts.blocksPointer, rom, ts.tiles);
        
        return ts;
    }
    
    /** 
     * Load tilesets. Right now, it's loading exactly 24 of them.
     * @param file
     * @throws IOException 
     */
    public static void init(RandomAccessFile file) throws IOException {        
        // load all tilesets
        // TODO: This is hard-capping the number of tilesets to 24.
        tilesets = new Tileset[0x18];
        for (int i = 0; i < tilesets.length; i++)
        {
            tilesets[i] = loadTileset(i, file);
        }
    }
    
    static {
        LoadAdressesJSON();
    }
}
