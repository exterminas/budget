/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.gui;

import budgetos.application.ProgramState;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Michael
 */
public class NewYearEvent implements ActionListener {

    private ProgramState state;
    private JTextArea enterYear;
    private JLabel outputLabel;
    private Integer year;
    private MonthDisplay monthDisplay;
    private JButton transactions;
    private JButton options;
    private JLabel curyear;
    private JButton switchYear;
    private MainUISet uiSet;
    
    public NewYearEvent(ProgramState state, JTextArea enterYear, JLabel outputLabel, MonthDisplay monthDisplay, JButton transactions, JButton options, JLabel curyear, JButton switchYear, MainUISet uiSet) {
        this.state = state;
        this.enterYear = enterYear;
        this.outputLabel = outputLabel;
        this.monthDisplay = monthDisplay;
        this.transactions = transactions;
        this.options = options;
        this.curyear= curyear;
        this.switchYear = switchYear;
        this.uiSet = uiSet;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {        
        
        if (enterYear.getText().matches("^[0-9]{4}$")) {
            outputLabel.setText("New Year added.");
            
            year = Integer.parseInt(enterYear.getText());
            state.addYear(year);
            System.out.println(state);
            monthDisplay.setEnabled(true);
            transactions.setEnabled(true);
            options.setEnabled(true);
            uiSet.getSaveButton().setEnabled(true);
            
            curyear.setText("Current year: " + year);
            
            if (state.getYears().size() >=2) {
                switchYear.setEnabled(true);
            }
                        
            
        } else {
            outputLabel.setText("Please enter a valid year.");
        }
        

        

        
    }
    
    
}
