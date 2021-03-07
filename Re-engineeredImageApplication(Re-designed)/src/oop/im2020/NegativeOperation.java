package oop.im2020;

import java.awt.image.BufferedImage;

public class NegativeOperation extends Operation{

	private final NegativeUI negativeUI = new NegativeUI();
	
	
	public NegativeOperation () {
		
	}
	
	@Override
	public BufferedImage doOperation(BufferedImage image) {
		final OperationDialog dialog = new OperationDialog(this, negativeUI);
		dialog.setVisible( true);
		// runs the code if the operation is not cancelled b the user
		if (!dialog.wasCancelled()) {
			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					final int inputRGB = OperationUtilities.getRGB(x, y, image);
					// changes the image to negative 
					final int outputRGB = OperationUtilities.negative(inputRGB);
					OperationUtilities.setRGB(x, y, outputRGB, image);
				}
			}
		}
		return image;
		
	}

}
