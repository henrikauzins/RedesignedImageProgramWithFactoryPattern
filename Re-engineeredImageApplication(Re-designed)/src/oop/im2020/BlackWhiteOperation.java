package oop.im2020;

import java.awt.image.BufferedImage;

// BlackWhiteOperation is extended from Abstract class Operation
public class BlackWhiteOperation extends Operation {

	// BlackWhiteUI object created to allow the user to interact with the operation
	private final BlackWhiteUI blackWhiteUI = new BlackWhiteUI();
	
	
	public BlackWhiteOperation () {
		
	}
	@Override
	public BufferedImage doOperation(BufferedImage image) {
		// creates new dialog box 
		final OperationDialog dialog = new OperationDialog(this, blackWhiteUI);
		dialog.setVisible(true);
		// if the dialog box settings are applied once the user presses ok, the code will run
		if (!dialog.wasCancelled()) {
			
			final double alpha = blackWhiteUI.getAlpha();
			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					final int inputRGB = OperationUtilities.getRGB(x, y, image);
					final int outputRGB = OperationUtilities.threshold(inputRGB, alpha);
					OperationUtilities.setRGB(x, y, outputRGB, image);
				
				}
			}
		}
		return image;	
	}

}
