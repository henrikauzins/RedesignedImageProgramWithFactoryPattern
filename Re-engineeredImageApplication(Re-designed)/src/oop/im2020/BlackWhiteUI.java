/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.im2020;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

/**
 *
 * @author childm
 */
public class BlackWhiteUI extends JPanel {
  
	// declares Java Swing jslider attribute 
    private final JSlider alphaSlider = new JSlider(0, 100);
    
    public BlackWhiteUI() {
        super(new BorderLayout());

      // adds the alphaslider to the south of the borderlayout
        add(this.alphaSlider, BorderLayout.SOUTH);
    }

// gets the value of the slider selected to match the image to black/white
    public int getAlpha() {
        return this.alphaSlider.getValue();
    }
}
