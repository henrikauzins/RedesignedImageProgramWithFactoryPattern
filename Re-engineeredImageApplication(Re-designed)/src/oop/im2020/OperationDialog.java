/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop.im2020;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author auzinsh
 */
public class OperationDialog extends JDialog {

	// declares buttons to apply or cancel the image operation
    private final JButton applyButton = new JButton("Apply");
    private final JButton cancelButton = new JButton("Cancel");
    // boolean to check if the operation was cancelled or not
    private boolean wasCancelled = true;
    
    // takes the abstract Operation class and JPanel as parameters
    public OperationDialog(Operation frame, JPanel optionPanel) {
        super(frame, true);
      
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(this.applyButton);
        buttonPanel.add(this.cancelButton);

        add(optionPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        // adds action listeners to apply and revert buttons
        this.applyButton.addActionListener(ev -> doApplyButton());
        this.cancelButton.addActionListener(ev -> setVisible(false));

        pack();
    }
    
    // if the user cancels the operation, no image transformation will take place
	public boolean wasCancelled() {
    	return this.wasCancelled;
    }
	// if the user applies the operation, image transformation will take place
    private void doApplyButton() {
        setVisible(false);
        this.wasCancelled = false;
    }
}
