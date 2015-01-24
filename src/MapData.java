
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Class used for loading a holding a map's data.
 */
public class MapData {
    
    int mapNum;
    long headerAddr;
    int bank;
    int tilesetNumber;
    int originalHeight, originalWidth;
    int height, width;
    int mapPointer;
    int textPointer;
    int scriptPointer;
    int connection;
    int[][] connectionData;
    int objectDataPointer;
    ObjectData objectData;
    int[] tileMap;
    
    private static long mapHeaderPointersAddress;
    private static long mapHeaderBanksAddress;
    
    public static MapData[] maps;
        
    public MapData()
    { }
    
    /**
     * Reads the config file to load some addresses.
     */
    public static void LoadAddressesFromJSON() {
        File config = new File("Config/config.json");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(config));
            JSONParser parser = new JSONParser();
            try {
                Map json = (Map) parser.parse(reader);
                mapHeaderPointersAddress = Long.decode((String)json.get("MapHeaderPointers"));
                json.get("MapHeaderBanks");
                mapHeaderBanksAddress = Long.decode((String)json.get("MapHeaderBanks"));
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
     * Reads the map's tilemap from the ROM.
     * @param rom
     * @throws IOException 
     */
    public void loadTileMap(RandomAccessFile rom) throws IOException {
        tileMap = new int[width * height];
        rom.seek(mapPointer%0x4000 + bank*0x4000);
        for (int i = 0; i < width*height; i++)
        {
            tileMap[i] = rom.readByte()&0xff;
        }
    }
    
    /**
     * Reads a two-byte pointer from the current location in the file
     * first byte is low; second byte is high
     * @param rom
     * @return
     * @throws IOException 
     */
    public static int readPointer(RandomAccessFile rom) throws IOException {
        int lo = rom.readByte()&0xff;
        int hi = rom.readByte()&0xff;
        return (hi << 8) | lo;
    }
    
    /**
     * Constructs a MapData object that corresponds to the given map number.
     * @param mapNum
     * @param rom
     * @return MapData instance
     * @throws java.io.IOException
     */
    public static MapData loadMap(int mapNum, RandomAccessFile rom) throws IOException {
        // First, load the address of the map's header.
        rom.seek(mapHeaderPointersAddress + mapNum * 2);
        long headerAddr = readPointer(rom);
        
        rom.seek(mapHeaderBanksAddress +mapNum);
        int bank = rom.readByte()&0xff;
        
        headerAddr = bank*0x4000 + headerAddr%0x4000;
        
        // Proceed to read the header and store the appropriate data located there.
        MapData md = new MapData();
        // Surround it all in a try-catch. If an exception occurs, we'll assume
        // it's an invalid map.
        try {
            md.mapNum = mapNum;
            md.headerAddr = headerAddr;
            md.bank = bank;
            rom.seek(headerAddr);
            md.tilesetNumber = rom.readByte()&0xff;
            md.height = rom.readByte()&0xff;
            md.width = rom.readByte()&0xff;
            md.originalHeight = md.height;
            md.originalWidth = md.width;

            md.mapPointer = readPointer(rom);
            md.textPointer = readPointer(rom);
            md.scriptPointer = readPointer(rom);

            int conn = rom.readByte()&0xff;
            md.connection = conn;
            int numConnections = ((conn >> 3) & 1) + ((conn >> 2) & 1) + ((conn >> 1) & 1) + (conn & 1);
            md.connectionData = new int[numConnections][11];
            
            // load all the connection data
            for (int i = 0; i < numConnections; i++)
            {
                // Connection data is 11 bytes for each direction.
                byte[] connBytes = new byte[11];
                rom.read(connBytes, 0, 11);
                // convert byte[] to int[]
                for (int j = 0; j < connBytes.length; j++) {
                    md.connectionData[i][j] = connBytes[j];
                }
            }

            md.objectDataPointer = readPointer(rom);
            md.objectData = ObjectData.loadObjectData(rom, md.objectDataPointer%0x4000 + 0x4000*bank);

            md.loadTileMap(rom);
        }
        catch (Exception e) 
        { }
        
        return md;
    }
        
    /**
     * Load all the maps.
     * @param rom
     * @throws java.io.IOException
     */
    public static void init(RandomAccessFile rom) throws IOException {
        // Maximum of 256 maps.
        maps = new MapData[256];
        for (int i = 0; i < maps.length; i++)
        {
            maps[i] = loadMap(i, rom);
        }
    }
    
    /**
     * Changes the block that the user clicks on when editing the tileMap.
     * @param map
     * @param blockNum
     * @param tileNum
     */
    public static void changeBlock(MapData map, int blockNum, int tileNum) {
        if (map.tileMap[tileNum] != blockNum)
        {
            map.tileMap[tileNum] = blockNum;
        }
    }
    
    /**
     * Resize the tilemap and dimensions of the map.
     * @param newWidth
     * @param newHeight
     * @param selectedBlock
     */
    public void resizeMap(int newWidth, int newHeight, int selectedBlock) {
        int oldSize = width*height;
        width = newWidth;
        height = newHeight;
        int newSize = width*height;
        
        // make new tilemap
        int[] newTilemap = Arrays.copyOf(tileMap, newSize);
        if (oldSize < newSize)
        {
            // Fill the rest with an arbitrary tile number.
            for (int i = oldSize; i < newSize; i++)
            {
                newTilemap[i] = selectedBlock;
            }
        }
        tileMap = newTilemap;
    }
    
    static {
        LoadAddressesFromJSON();
    }
}
