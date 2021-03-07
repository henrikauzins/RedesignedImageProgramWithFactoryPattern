package oop.im2020;

public class ImageOperationFactory {
	// declares enums that represent the program operations
	// six various image operations exist in the programs
	public enum OperationType {ChromaKey, Grayscale, Tint, Negative, BlackAndWhite, Blend};

	public Operation newImageOperation(OperationType opType) {
	    switch (opType) {
	    // when the user selects an operation from the menu, the selected operation will return a new instance
	      case ChromaKey:
	        return new ChromaKeyOperation();
	      case Grayscale:
	        return new GreyscaleOperation();
	      case Tint:
	        return new TintOperation();
	      case Negative:
	    	return new NegativeOperation();
	      case BlackAndWhite:
	    	return new BlackWhiteOperation();
	      case Blend:
	    	  return new BlendOperation();
	      
	      default:
	    	  // if operation does not exist, an exception will run
	        throw new IllegalArgumentException("Unknown operation: " + opType);
	    }
}
	
}
