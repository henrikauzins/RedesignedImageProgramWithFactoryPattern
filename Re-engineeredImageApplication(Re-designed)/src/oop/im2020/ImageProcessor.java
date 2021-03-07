package oop.im2020;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import oop.im2020.ImageOperationFactory.OperationType;


/**
 *
 * @author auzinsh
 */
public class ImageProcessor extends JFrame {
	private static final long serialVersionUID = 1L;

	// declares JFilechooser, ImagePanel object, image, loaded image, JMenu for Image operations and and new image operations factory 
	private final JFileChooser chooser = new JFileChooser();
	private final ImagePanel imagePanel = new ImagePanel();

	private BufferedImage image;

	private File loadedImage;

	private final JMenu opMenu = new JMenu("Operations");
	
	
	public ImageOperationFactory newFactory = new ImageOperationFactory();

	public ImageProcessor(ImageOperationFactory newFactory) {
		this.chooser.setMultiSelectionEnabled(false);
		this.chooser.setCurrentDirectory(new File(".")); // set current directory
		
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		this.newFactory = newFactory;

		final JMenuBar menuBar = new JMenuBar();
		final JMenu fileMenu = new JMenu("File");
		
		// declares a menu item to act as a open image button 
		final JMenuItem openItem = new JMenuItem("Open");
		// adds action listener to menu item
		openItem.addActionListener(ev -> doOpenImage());
		fileMenu.add(openItem);
		// declares a menu item to act as a revert image button 
		final JMenuItem revertItem = new JMenuItem("Revert");
		// adds action listener to revertItem 
		revertItem.addActionListener(ev -> revert());
		fileMenu.add(revertItem);
		// adds program variables to the JMenu Bar
		menuBar.add(fileMenu);
		menuBar.add(this.opMenu);
		setJMenuBar(menuBar);
		// adds the imagePanel to the center of the BorderLayout
		add(this.imagePanel, BorderLayout.CENTER);
		pack();

	
		// loops through the factory to add the available operations to the menu from ImageOperationFactory
		for(ImageOperationFactory.OperationType opType : ImageOperationFactory.OperationType.values()) {
			addMenuOperation(opMenu, opType.toString());
		}

		this.setVisible(true);
	}

	
	// adds menu operation to operation menu
	private void addMenuOperation(final JMenu menu, final String identifier) {
		final JMenuItem item = new JMenuItem(identifier);
		item.addActionListener(ev -> doOperation(identifier));
		this.opMenu.add(item);
	}
	// allows the user to choose an image to show on the image panel
	private File chooseImageFile() {
		if (this.chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			return this.chooser.getSelectedFile();
		} else {
			return null;
		}
	}
	// opens the image onto the image panel
	private void doOpenImage() {
		final File file = chooseImageFile();
		if (file != null) {
			loadImage(file);
		}
	}
	// loads the image onto the image panel
	private void loadImage(final File file) {
		try {
			this.image = ImageIO.read(file);
			this.loadedImage = file;
			setImage( this.image);
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
	}
	// sets the selected image
	private void setImage(final BufferedImage image) {
		this.image = image;
		this.imagePanel.setImage(image);
		pack();
	}
	
	// reverts image operation effect off the image
	private void revert() {
		if (this.loadedImage != null) {
			loadImage(this.loadedImage);
		}
	}
	// undertakes the selected operation by the user and references the abstract operation class and image operation factory
	private void doOperation(final String identifier) {
	
		ImageOperationFactory.OperationType opType = ImageOperationFactory.OperationType.valueOf(identifier);
		// references the factory to the abstract operation class
		Operation operation = this.newFactory.newImageOperation(opType);
		this.imagePanel.setImage(operation.doOperation(image));
	}

	// runs the code in the ImageProcessor constructor 
	public static void launch() {
		new ImageProcessor(new ImageOperationFactory());
	}
// runs the launch main method that calls the launch method
	public static void main(final String[] args) {
		// runs the launch method
		SwingUtilities.invokeLater(() -> launch());
	}

}
