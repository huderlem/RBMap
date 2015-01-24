RBMap
==============================

This is a map editor for Pokemon Red & Blue Versions (Maybe Yellow Version, too.  I haven't tested it.).

How to use it:

1. Click File -> Load ROM.  Search for the Pokemon ROM you're working with.  Click "Open".
2. After a second or two, the map and tile panes should be filled in.  If they didn't load correctly, see the Config section below.
3. Click on a tile in the far-right pane and then paint that tile onto the map pane.  It's pretty self-explanatory.
4. When you want to save a map, click "Export Map".  This will allow you to save the map data to a file.
	Then, you can use that file to copy/paste its data into the ROM.
	If you're working with the assembly, just save that map into the maps folder in the disassembly project.

You can change width/height of maps, too.


Config
==============================
For this map editor to work, it needs to know a couple addresses in the ROM to be able to load the maps and tilesets.
You can change these addresses in a file called "config.json" in the Config folder.

Here are the default settings for Pokemon Red Version:
{
    "MapHeaderPointers": "0x1ae",
    "MapHeaderBanks": "0xc23d",
    "Tilesets": "0xc7be",
    "color0": "0xffffff",
    "color1": "0x909090",
    "color2": "0x404040",
    "color3": "0x000000"
}

If you're working with the disassembly project, then it's very possible that the addresses for MapHeaderPointers, MapHeaderBanks, and Tilesets have changed.  
You can find their current addresses in the ROM by looking in the pokered.sym file.  

***Make sure to use the full address. For example, MapHeaderBanks's address is 0x423d in Bank 3, but in the config.json file, you would put 0xc23d.***

The 4 color settings are if you prefer different colors to be used in the maps and tiles. They're set to different shades of gray by default.
