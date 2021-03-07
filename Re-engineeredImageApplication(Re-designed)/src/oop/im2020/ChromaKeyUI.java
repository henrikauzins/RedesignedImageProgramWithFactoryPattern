/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.im2020;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.colorchooser.AbstractColorChooserPanel;

/**
 *
 * @author childm
 */
public class ChromaKeyUI extends JPanel {

	// declares textfield, button colour choosers and value slider
    private final JTextField otherImagePath = new JTextField(60);
    private final JButton fileChooserButton = new JButton("Open");
    private final JColorChooser colorChooser = new JColorChooser();
    private final JSlider alphaSlider = new JSlider(0, 100);

    private final JFileChooser chooser;
    private File file;

    public ChromaKeyUI(final JFileChooser chooser) {
        super(new BorderLayout());

        this.chooser = chooser;
        
        AbstractColorChooserPanel[] defaultPanels = this.colorChooser.getChooserPanels();
//        for (AbstractColorChooserPanel abstractColorChooserPanel : defaultPanels) {
//			if ( abstractColorChooserPanel.getDisplayName().equals("RGB")) {
//				this.colorChooser.setChooserPanels( new AbstractColorChooserPanel[] {abstractColorChooserPanel});
//				break;
//			}
//		}
        this.colorChooser.setPreviewPanel( new JPanel());

        final JPanel pathPanel = new JPanel();
        // adds the file chooser button and image path to the JPanel
        pathPanel.add(this.otherImagePath);
        pathPanel.add(this.fileChooserButton);
        //sets a border for pathPanel, colour chooser and alpha value slider
        pathPanel.setBorder(BorderFactory.createTitledBorder("Image to blend"));
        colorChooser.setBorder(BorderFactory.createTitledBorder("Colour to blend through"));
        alphaSlider.setBorder(BorderFactory.createTitledBorder("How close to match the colour to blend through"));

        // adds the JPanel, colour chooser and alpha value slider to various places within the border layout
        add(pathPanel, BorderLayout.NORTH);
        add(this.colorChooser, BorderLayout.CENTER);
        add(this.alphaSlider, BorderLayout.SOUTH);

        this.otherImagePath.setEditable(false);

        // action listener is added through a lambda expression to allow the user to choose a another image file
        this.fileChooserButton.addActionListener(ev -> showChooser());
    }

    // these methods show the filchooser dialog box, gets the selected colour and the sensitivity value  
    
    private void showChooser() {
        if (this.chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.file = this.chooser.getSelectedFile();
            this.otherImagePath.setText(this.file.getPath());
        }
    }

    public File getOtherImagePath() {
        return this.file;
    }

    public double getSensitivity() {
        return this.alphaSlider.getValue() / 100.0;
    }

    public Color getTargetColor() {
        return this.colorChooser.getColor();
    }
}
