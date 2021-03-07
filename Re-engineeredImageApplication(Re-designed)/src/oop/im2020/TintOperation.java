package oop.im2020;

import java.awt.image.BufferedImage;

public class TintOperation extends Operation {

	// declares new instance of TintUI
	private final TintUI tintUI = new TintUI();
	
	
	public TintOperation () {
		
	}
	@Override
	public BufferedImage doOperation(BufferedImage image) {
		// creates new operation dialog box
		final OperationDialog dialog = new OperationDialog(this, tintUI);
		dialog.setVisible( true);
		// runs and applies operation if user presses apply 
		if (!dialog.wasCancelled()) {
			// gets the chosen colour by the user 
			final ColourComponent band = tintUI.getChosenColor();
			// gets the value of the JSlider 
			final double alpha = tintUI.getAlpha() / 100.0;
			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					final int inputRGB = OperationUtilities.getRGB(x, y, image);
					// runs the tint operation to transform the image
					final int outputRGB = OperationUtilities.tint(inputRGB, alpha, band);
					OperationUtilities.setRGB(x, y, outputRGB, image);
				}
			}
		}
		// returns the output back to the user
		return image;
		
	}

}
