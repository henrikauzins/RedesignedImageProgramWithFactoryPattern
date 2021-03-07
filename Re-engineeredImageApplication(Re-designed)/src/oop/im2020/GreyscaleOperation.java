package oop.im2020;

import java.awt.image.BufferedImage;

public class GreyscaleOperation extends Operation{

	// declares grayscale UI object
	private final GrayscaleUI grayscaleUI = new GrayscaleUI();
	
	
	public GreyscaleOperation () {
		
	}
	
	@Override
	public BufferedImage doOperation(BufferedImage image) {
		// creates new operation dialog object that takes the operation UI as a parameter
		// allows the user to interact with the operation
		final OperationDialog dialog = new OperationDialog(this, grayscaleUI);
		dialog.setVisible( true);
		// runs the operation code if user does not cancel the operation
		if (!dialog.wasCancelled()) {
			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					final int inputRGB = OperationUtilities.getRGB(x, y, image);
					// changes the image to grayscale
					final int outputRGB = OperationUtilities.grayscale(inputRGB);
					OperationUtilities.setRGB(x, y, outputRGB, image);
				}
			}
		}
		return image;
		
	}

}
