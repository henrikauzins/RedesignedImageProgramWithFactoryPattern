/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.im2020;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

/**
 *
 * @author auzinsh
 */
// this class provides the panel to where the selected image by the user will be shown
public class ImagePanel extends JComponent {

	// buffered image variable declared
    private BufferedImage image;

    // sets the preferred height and width of the image
    public void setImage(final BufferedImage image) {
        this.image = image;
        setPreferredSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
        // repaints the image 
        repaint();
    }

    // draws the image onto the JComponent
    @Override
    public void paintComponent(final Graphics g) {
        g.drawImage(this.image, 0, 0, this);
    }
}
