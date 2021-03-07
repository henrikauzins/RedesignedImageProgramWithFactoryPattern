package oop.im2020;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;



public class ChromaKeyOperation extends Operation {
	private final JFileChooser chooser = new JFileChooser();
	public ChromaKeyUI chromaKeyUI = new ChromaKeyUI(chooser);
	
	public ChromaKeyOperation () {
		
	}

	@Override
	public BufferedImage doOperation(BufferedImage image) {
		// TODO Auto-generated method stub
		final OperationDialog dialog = new OperationDialog(this, chromaKeyUI);
		dialog.setVisible(true);    	
		if (!dialog.wasCancelled()) {
			try {
				double sensitivity = chromaKeyUI.getSensitivity();
				BufferedImage otherImage = ImageIO.read(chromaKeyUI.getOtherImagePath());

				int targetRGB = chromaKeyUI.getTargetColor().getRGB();

				BufferedImage output = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
				for (int x = 0; x < output.getWidth(); x++) {
					for (int y = 0; y < output.getHeight(); y++) {
						int inputRGB = OperationUtilities.getRGB(x, y, image);
						int otherRGB = OperationUtilities.getRGB(x, y, otherImage);
						// runs chromakey method in Operation utilities to apply transformation to the selected image
						int outputRGB = OperationUtilities.chromaKey(inputRGB, otherRGB, targetRGB, sensitivity);
						OperationUtilities.setRGB(x, y, outputRGB, output);
					}
				}
				return output;
			} catch (IOException ex) {
				ex.printStackTrace();
				return image;
			}
		} else {
			return image;
		}
	}
}
