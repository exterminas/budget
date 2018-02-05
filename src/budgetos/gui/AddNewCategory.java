/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.gui;

import budgetos.application.ProgramState;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Michael
 */

//This class is for the button that adds a custom category
//this button is part of the new transaction window

public class AddNewCategory implements ActionListener {
    
    //It keeps track of the text area of the new transaction window
    private JTextArea text;
    
    //the current program state.
    private ProgramState state;
    
    //a combobox
    private JComboBox combo;
    
    //and the frame and output label of the new transaction window
    private JFrame frame;
    private JLabel output;

    public AddNewCategory(JTextArea text, ProgramState state, JComboBox combo, JFrame frame, JLabel output) {
        this.text = text;
        this.state = state;
        this.combo = combo;
        this.frame = frame;
        this.output = output;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //If the button is clicked, the class will...
        
        
        //check if the text the user entered as a new custom category is not yet part of the existing list of categories...
        if (!state.getCategories().contains(text.getText())) {

            //... and if the user  entered text at all.
            if (!text.getText().isEmpty()) {
                
                //It will then add the entered string to the list of categories as tracked by the programstate
                state.getCategories().add(text.getText());
                
                //and refresh the combobox in the new transaction window
                String[] cat = new String[state.getCategories().size()];
                cat = state.getCategories().toArray(cat);
                
                combo = new JComboBox(cat);
                
                //Then it will create outputs in the new transaction window and dispose of the new transactions window
                //this is so that user has to open it again and the new combobox of categories is loaded correctly
                output.setText("Custom category added! Enter new Text to create another category!");
                text.setText("");
                

               // frame.setVisible(false); 
               // frame.dispose();
                state.setTransactionWindow(false);

            } else {
                //Display message if user has not entered text
                output.setText("You must enter text to create a new category.");
                text.setText("");
            }
        } else {
            //display message if that is already a category
            output.setText("That is already a category! Please enter new Text!");
            text.setText("");
        }

    }

}
