/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.gui;

import budgetos.application.ProgramState;
import budgetos.application.Transaction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.control.ComboBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Michael
 */
public class NewTransactions implements ActionListener {

    private ProgramState state;
    private JTextArea value;
    private JComboBox category;
    private JTextArea description;
    private JLabel output;
    private MainDisplay mainText;

    public NewTransactions(ProgramState state, JTextArea value, JComboBox category, JTextArea description, JLabel output, MainDisplay mainText) {
        this.state = state;
        this.value = value;
        this.category = category;
        this.description = description;
        this.output = output;
        this.mainText = mainText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            Double amount = Double.parseDouble(value.getText());
            System.out.println(amount);
            String selectedCategory = (String)category.getSelectedItem();
            
            state.getCurrentMonth().addTransaction(new Transaction(amount, description.getText(), selectedCategory));
            mainText.displayTransactions();

            
        } catch (NumberFormatException f) {
            output.setText("Please enter a valid transaction value");
        }



        //   state.getCurrentMonth().addTransaction(new Transaction());
    }

}
