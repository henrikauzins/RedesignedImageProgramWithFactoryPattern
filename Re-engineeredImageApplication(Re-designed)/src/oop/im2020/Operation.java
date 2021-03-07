package oop.im2020;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

// abstract class for image operations that extends JFrame to allow for the operation functionality to work on images
public abstract class Operation extends JFrame {
	
	

	public Operation() {
		
	}
// abstract method for image operations
	 public abstract BufferedImage doOperation(BufferedImage image);
}
