
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        
        mapSpinner.setEnabled(false);
        tilesetSpinner.setEnabled(false);
        widthSpinner.setEnabled(false);
        heightSpinner.setEnabled(false);
        gridToggleCheckBox.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        saveFileChooser = new javax.swing.JFileChooser();
        mapSpinner = new javax.swing.JSpinner();
        mapNumberLabel = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        mapEditorPanel = new javax.swing.JPanel();
        blockScrollPane = new javax.swing.JScrollPane();
        blockPanel = new javax.swing.JPanel();
        mapScrollPane = new javax.swing.JScrollPane();
        mapPanel = new javax.swing.JPanel();
        widthLabel = new javax.swing.JLabel();
        heightSpinner = new javax.swing.JSpinner();
        heightLabel = new javax.swing.JLabel();
        widthSpinner = new javax.swing.JSpinner();
        gridToggleCheckBox = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tilesetSpinner = new javax.swing.JSpinner();
        exportButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        loadRomMenuItem = new javax.swing.JMenuItem();

        fileChooser.setDialogTitle("Open ROM");
        fileChooser.setFileFilter(new MyCustomFilter());

        saveFileChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        saveFileChooser.setDialogTitle("");
        saveFileChooser.setSelectedFile(new java.io.File("C:\\Users\\mhuderle\\Game Development\\Color:Metal Gen Info\\RBY\\maize\\maps"));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mapSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 247, 1));
        mapSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mapSpinnerStateChanged(evt);
            }
        });

        mapNumberLabel.setBackground(new java.awt.Color(255, 51, 51));
        mapNumberLabel.setText("Map Number");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        blockScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        blockPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        blockPanel.setLayout(new java.awt.GridLayout(64, 4, 0, 4));
        blockScrollPane.setViewportView(blockPanel);

        mapPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mapPanel.setPreferredSize(new java.awt.Dimension(0, 0));

        org.jdesktop.layout.GroupLayout mapPanelLayout = new org.jdesktop.layout.GroupLayout(mapPanel);
        mapPanel.setLayout(mapPanelLayout);
        mapPanelLayout.setHorizontalGroup(
            mapPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 357, Short.MAX_VALUE)
        );
        mapPanelLayout.setVerticalGroup(
            mapPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 365, Short.MAX_VALUE)
        );

        mapScrollPane.setViewportView(mapPanel);

        widthLabel.setText("Width");

        heightSpinner.setModel(new javax.swing.SpinnerNumberModel(2, 2, 50, 1));
        heightSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                heightSpinnerStateChanged(evt);
            }
        });

        heightLabel.setText("Height");

        widthSpinner.setModel(new javax.swing.SpinnerNumberModel(2, 2, 50, 1));
        widthSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                widthSpinnerStateChanged(evt);
            }
        });

        gridToggleCheckBox.setText("Toggle Grid");
        gridToggleCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gridToggleCheckBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Use right-click to move");

        jLabel3.setText("warps, items, signs, etc.");

        org.jdesktop.layout.GroupLayout mapEditorPanelLayout = new org.jdesktop.layout.GroupLayout(mapEditorPanel);
        mapEditorPanel.setLayout(mapEditorPanelLayout);
        mapEditorPanelLayout.setHorizontalGroup(
            mapEditorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mapEditorPanelLayout.createSequentialGroup()
                .add(mapEditorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(mapEditorPanelLayout.createSequentialGroup()
                        .add(mapEditorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(mapEditorPanelLayout.createSequentialGroup()
                                .add(9, 9, 9)
                                .add(widthLabel))
                            .add(mapEditorPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .add(widthSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(mapEditorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(heightLabel)
                            .add(heightSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(mapEditorPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(mapEditorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(gridToggleCheckBox)
                            .add(jLabel2)
                            .add(jLabel3))))
                .add(10, 10, 10)
                .add(mapScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(blockScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 166, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        mapEditorPanelLayout.setVerticalGroup(
            mapEditorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mapEditorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(mapEditorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(mapEditorPanelLayout.createSequentialGroup()
                        .add(gridToggleCheckBox)
                        .add(45, 45, 45)
                        .add(mapEditorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(widthLabel)
                            .add(heightLabel))
                        .add(2, 2, 2)
                        .add(mapEditorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(widthSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(heightSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(69, 69, 69)
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel3)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, blockScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, mapScrollPane))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tiles", null, mapEditorPanel, "Edit the current map's tiles");

        tilesetSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        tilesetSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tilesetSpinnerStateChanged(evt);
            }
        });

        exportButton.setText("Export Map");
        exportButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportButtonMouseClicked(evt);
            }
        });

        jLabel1.setText("Tileset Number");

        jMenuBar1.setName("RBMap"); // NOI18N

        fileMenu.setText("File");

        loadRomMenuItem.setText("Load ROM");
        loadRomMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadRomMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadRomMenuItem);

        jMenuBar1.add(fileMenu);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(mapSpinner, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                        .add(mapNumberLabel)
                        .add(tilesetSpinner))
                    .add(exportButton)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(13, 13, 13)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTabbedPane1)
                    .add(layout.createSequentialGroup()
                        .add(mapNumberLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(mapSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(tilesetSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(exportButton)
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Filter to display only files that contain ".gb".
     * Note that this will catch .gb and .gbc. It will also catch extensions like ".gbfoo".
     */
    class MyCustomFilter extends javax.swing.filechooser.FileFilter {
        @Override
        public boolean accept(File file) {
            return file.isDirectory() || file.getAbsolutePath().contains(".gb");
        }

        @Override
        public String getDescription() {
            // This description will be displayed in the dialog,
            // hard-coded = ugly, should be done via I18N
            return "Gameboy ROMs (.gb, .gbc)";
        }
    }
    
    private void loadRomMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadRomMenuItemActionPerformed
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                rom = new RandomAccessFile(fileChooser.getSelectedFile(), "rw");
                if (validROM() == true){
                    // Load all map and tileset data in the game.
                    MapData.init(rom);
                    Tileset.init(rom);
                    
                    // Set current map to Pallet Town.
                    setCurrentMap(0);
                    
                    // Enable GUI components.
                    mapSpinner.setEnabled(true);
                    tilesetSpinner.setEnabled(true);
                    
                    widthSpinner.setEnabled(true);
                    widthSpinner.setValue(curMap.width);
                    
                    heightSpinner.setEnabled(true);
                    heightSpinner.setValue(curMap.height);
                    
                    gridToggleCheckBox.setEnabled(true);
                    gridToggleCheckBox.setSelected(gridToggle);
                } else {
                    rom = null;
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_loadRomMenuItemActionPerformed

    private void mapSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mapSpinnerStateChanged
        setCurrentMap((Integer)mapSpinner.getValue());
    }//GEN-LAST:event_mapSpinnerStateChanged

    private void widthSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_widthSpinnerStateChanged
        curMap.resizeMap((Integer)widthSpinner.getValue(), curMap.height, selectedBlock);
        refreshTilemap(curMap.tilesetNumber);
    }//GEN-LAST:event_widthSpinnerStateChanged

    private void heightSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_heightSpinnerStateChanged
        curMap.resizeMap(curMap.width, (Integer)heightSpinner.getValue(), selectedBlock);
        refreshTilemap(curMap.tilesetNumber);
    }//GEN-LAST:event_heightSpinnerStateChanged

    private void gridToggleCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gridToggleCheckBoxActionPerformed
        gridToggle = gridToggleCheckBox.isSelected();
        refreshTilemap(curMap.tilesetNumber);
    }//GEN-LAST:event_gridToggleCheckBoxActionPerformed

    private void tilesetSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tilesetSpinnerStateChanged
         refreshTilemap((Integer)tilesetSpinner.getValue());
         refreshBlocks((Integer)tilesetSpinner.getValue());
    }//GEN-LAST:event_tilesetSpinnerStateChanged

    /**
     * Export the current map to a file. 
     */
    private void exportButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportButtonMouseClicked
        // Save the map to a file!
        int returnVal = saveFileChooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            try {
                RandomAccessFile out = new RandomAccessFile(saveFileChooser.getSelectedFile(), "rw");
                for (int i = 0; i < curMap.tileMap.length; i++)
                {
                    out.write(curMap.tileMap[i]);
                }
                out.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_exportButtonMouseClicked

    /**
     * Checks to see if the opened file is a RED or BLUE ROM file.
     */
    private boolean validROM(){
        try {
            rom.seek(0x134);
            int byte1 = rom.read();
            int byte2 = rom.read();
            int byte3 = rom.read();
            int byte4 = rom.read();
            if (byte1 == 0x50 && byte2 == 0x4f && byte3 == 0x4b && byte4 == 0x45)
            {
                return true;
            }
            
            return false;    
        } catch (IOException e) {
            return false;
        }
    }
    
    /**
     * Sets the currently active map to the given map number.
     * @param mapNum 
     */
    private void setCurrentMap(int mapNum) {
        curMap = MapData.maps[mapNum];
        tilesetSpinner.setValue(curMap.tilesetNumber);
        if (curMap.tilesetNumber < Tileset.tilesets.length)
        {
            refreshTilemap(curMap.tilesetNumber);
            refreshBlocks(curMap.tilesetNumber);
        } 
        else
        {
            displayInvalidTileset();
            return;
        }
        
        mapPanel.revalidate();
        mapPanel.repaint();
        blockPanel.revalidate();
        blockPanel.repaint();
        
        // width and height updates
        widthSpinner.setValue(curMap.width);
        heightSpinner.setValue(curMap.height);
        
    }
    
    /**
     * Loads the given tileset's blocks into the block panel.
     * @param tilesetNumber 
     */
    private void refreshBlocks(int tilesetNumber) {
        if (tilesetNumber == -1)
            tilesetNumber = (Integer)tilesetSpinner.getValue();
        
        Tileset curTS = Tileset.tilesets[tilesetNumber];

        blockPanel.removeAll();
        // Add the tileset blocks.
        for (int i = 0; i < curTS.blocks.length; i++) {
            final JLabel label = new JLabel(new ImageIcon(curTS.blocks[i]));
            blockPanel.add(label);
            final int num = i;
            label.addMouseListener(new MouseAdapter () {
                @Override
                public void mousePressed(MouseEvent e) {
                    selectedBlock = num;
                    refreshBlocks(-1);
                }
            });
            
            if (i == selectedBlock)
            {
                label.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
    }
    
    /**
     * Updates the map's tilemap displayed in the middle of the screen.
     */
    private void refreshTilemap(int tilesetNumber) {
        Tileset curTS = Tileset.tilesets[tilesetNumber];
        
        mapPanel.removeAll();
        mapPanel.setBounds(0, 0, curMap.width*Tileset.TILE_WIDTH*4, curMap.height*Tileset.TILE_HEIGHT*4);
        mapPanel.setPreferredSize(new Dimension(curMap.width*Tileset.TILE_WIDTH*4, curMap.height*Tileset.TILE_HEIGHT*4));
        
        // add the overlays for objects and such
        curMap.objectData.addToOverlay(mapPanel, curMap, this);
        
        // add the tilemap 
        for (int i = 0; i < curMap.tileMap.length; i++) {
            if (curMap.tileMap[i] < curTS.blocks.length)
            {
                final JLabel label = new JLabel(new ImageIcon(curTS.blocks[curMap.tileMap[i]]));
                int x = i % curMap.width;
                int y = i / curMap.width;
                label.setToolTipText("(" + x + ", " + y + ")");
                mapPanel.add(label);
                
                label.setBounds((i%curMap.width)*Tileset.TILE_WIDTH*4, (i/curMap.width)*Tileset.TILE_HEIGHT*4, 
                        Tileset.TILE_WIDTH*4, Tileset.TILE_HEIGHT*4);
                
                if (gridToggle)
                {
                    label.setBorder(BorderFactory.createLineBorder(Color.black));
                }
                
                final int tileIndex = i;
                final int tNum = tilesetNumber;
                label.addMouseListener(new MouseAdapter () {

                    @Override
                    public void mousePressed(MouseEvent e) {
                        MapData.changeBlock(curMap, selectedBlock, tileIndex);
                        label.setIcon(new ImageIcon(Tileset.tilesets[tNum].blocks[curMap.tileMap[tileIndex]]));
                    }
                    
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // Handle mouse dragging to more easily "paint" the map.
                        if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
                            MapData.changeBlock(curMap, selectedBlock, tileIndex);
                            label.setIcon(new ImageIcon(Tileset.tilesets[tNum].blocks[curMap.tileMap[tileIndex]]));
                        }
                    }
                });
            }
            else
            {
                displayInvalidBlock();
                return;
            }
        }
        
        mapPanel.revalidate();
        mapPanel.repaint();
    }
    
    private void displayInvalidTileset() {
        mapPanel.removeAll();
        blockPanel.removeAll();
        mapPanel.add(new JLabel("This map has an invalid tileset #."));
        mapPanel.revalidate();
        mapPanel.repaint();
    }
    
    private void displayInvalidBlock() {
        mapPanel.removeAll();
        blockPanel.removeAll();
        mapPanel.add(new JLabel("This map has an invalid block number in its tilemap."));
        mapPanel.revalidate();
        mapPanel.repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                MainFrame f = new MainFrame();
                f.setVisible(true);
                f.setTitle("RBMap");
            }
        });
        
    }
    
    private RandomAccessFile rom;
    private static MapData curMap;
    private static int selectedBlock = 0;
    
    private static boolean gridToggle = false;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel blockPanel;
    private javax.swing.JScrollPane blockScrollPane;
    private javax.swing.JButton exportButton;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JCheckBox gridToggleCheckBox;
    private javax.swing.JLabel heightLabel;
    private javax.swing.JSpinner heightSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem loadRomMenuItem;
    private javax.swing.JPanel mapEditorPanel;
    private javax.swing.JLabel mapNumberLabel;
    private javax.swing.JPanel mapPanel;
    private javax.swing.JScrollPane mapScrollPane;
    private javax.swing.JSpinner mapSpinner;
    private javax.swing.JFileChooser saveFileChooser;
    private javax.swing.JSpinner tilesetSpinner;
    private javax.swing.JLabel widthLabel;
    private javax.swing.JSpinner widthSpinner;
    // End of variables declaration//GEN-END:variables
}
