package oop.im2020;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
// Blend Operation is extended from Abstract class Operation
public class BlendOperation extends Operation {
	//JFileChooser variable declared to allow users to select another image to blend through
	private final JFileChooser chooser = new JFileChooser();
	// declaring blendUI object that takes a file chooser parameter
	public BlendUI blendUI = new BlendUI(chooser);
	
	public BlendOperation () {
		
	}

	@Override
	public BufferedImage doOperation(BufferedImage image) {
		// TODO Auto-generated method stub
		// creates new dialog box 
		final OperationDialog dialog = new OperationDialog(this, blendUI);
		dialog.setVisible(true);    
		// if the dialog box settings are applied once the user presses ok, the code will run
		if (!dialog.wasCancelled()) {
			try {
				double sensitivity = blendUI.getSensitivity();
				BufferedImage otherImage = ImageIO.read(blendUI.getOtherImagePath());

				BufferedImage output = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
				for (int x = 0; x < output.getWidth(); x++) {
					for (int y = 0; y < output.getHeight(); y++) {
						int inputRGB = OperationUtilities.getRGB(x, y, image);
						int otherRGB = OperationUtilities.getRGB(x, y, otherImage);
						int outputRGB = OperationUtilities.blend(inputRGB, otherRGB, sensitivity);
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
