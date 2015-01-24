import java.util.HashMap;

/**
 * Class to convert text in the ROM to human-readable strings.
 */
public class TextHandler {
    
    /**
     * Maps bytes in the ROM to human-readable strings.
     */
    private static final HashMap<Integer, String> tbl = new HashMap();
    
    /**
     * Builds a human-readable string from an array of bytes representing
     * text in the ROM.
     * @param buffer
     * @return 
     */
    public static String BytesToString(int[] buffer){
        String str = "";
        for (int b : buffer){
            str += tbl.get(b);
        }
        
        return str;
    }

    static{
        tbl.put(0x4f, "=");
        tbl.put(0x57, "#");
        tbl.put(0x51, "*");
        tbl.put(0x52, "[PLAYER]");
        tbl.put(0x53, "[RIVAL]");
        tbl.put(0x54, "PKMN");
        tbl.put(0x55, "+");
        tbl.put(0x58, "$");
        tbl.put(0x7f, " ");
        tbl.put(0x80, "A");
        tbl.put(0x81, "B");
        tbl.put(0x82, "C");
        tbl.put(0x83, "D");
        tbl.put(0x84, "E");
        tbl.put(0x85, "F");
        tbl.put(0x86, "G");
        tbl.put(0x87, "H");
        tbl.put(0x88, "I");
        tbl.put(0x89, "J");
        tbl.put(0x8a, "K");
        tbl.put(0x8b, "L");
        tbl.put(0x8c, "M");
        tbl.put(0x8d, "N");
        tbl.put(0x8e, "O");
        tbl.put(0x8f, "P");
        tbl.put(0x90, "Q");
        tbl.put(0x91, "R");
        tbl.put(0x92, "S");
        tbl.put(0x93, "T");
        tbl.put(0x94, "U");
        tbl.put(0x95, "V");
        tbl.put(0x96, "W");
        tbl.put(0x97, "X");
        tbl.put(0x98, "Y");
        tbl.put(0x99, "Z");
        tbl.put(0x9c, ":");
        tbl.put(0xa0, "a");
        tbl.put(0xa1, "b");
        tbl.put(0xa2, "c");
        tbl.put(0xa3, "d");
        tbl.put(0xa4, "e");
        tbl.put(0xa5, "f");
        tbl.put(0xa6, "g");
        tbl.put(0xa7, "h");
        tbl.put(0xa8, "i");
        tbl.put(0xa9, "j");
        tbl.put(0xaa, "k");
        tbl.put(0xab, "l");
        tbl.put(0xac, "m");
        tbl.put(0xad, "n");
        tbl.put(0xae, "o");
        tbl.put(0xaf, "p");
        tbl.put(0xb0, "q");
        tbl.put(0xb1, "r");
        tbl.put(0xb2, "s");
        tbl.put(0xb3, "t");
        tbl.put(0xb4, "u");
        tbl.put(0xb5, "v");
        tbl.put(0xb6, "w");
        tbl.put(0xb7, "x");
        tbl.put(0xb8, "y");
        tbl.put(0xb9, "z");
        tbl.put(0xba, ",");
        tbl.put(0xbc, "\'l");
        tbl.put(0xbd, "\'s");
        tbl.put(0xbe, "\'t");
        tbl.put(0xbf, "\'v");
        tbl.put(0xe0, "\'");
        tbl.put(0xe1, "PK");
        tbl.put(0xe2, "MN");
        tbl.put(0xe3, "-");
        tbl.put(0xe4, "\'r");
        tbl.put(0xe5, "\'m");
        tbl.put(0xe6, "?");
        tbl.put(0xe7, "!");
        tbl.put(0xe8, ".");
        tbl.put(0xf4, ",");
        tbl.put(0xf6, "0");
        tbl.put(0xf7, "1");
        tbl.put(0xf8, "2");
        tbl.put(0xf9, "3");
        tbl.put(0xfa, "4");
        tbl.put(0xfb, "5");
        tbl.put(0xfc, "6");
        tbl.put(0xfd, "7");
        tbl.put(0xfe, "8");
        tbl.put(0xff, "9");
    }
}
