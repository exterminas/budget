/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.gui;

import budgetos.application.Month;
import budgetos.application.ProgramState;
import budgetos.application.Transaction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 *
 * @author Michael
 */
public class MonthButtonWatcher implements ActionListener {
    private JButton monthButton;
    private MainDisplay mainDisplay;
    private ProgramState state;
    
    public MonthButtonWatcher(JButton monthButton, MainDisplay mainDisplay, ProgramState state) {
        this.state = state;
        this.monthButton = monthButton;
        this.mainDisplay = mainDisplay;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = monthButton.getText();
        
        for (Month month : state.getCurrentYear().getMonths()) {
            if (month.toString().equals(buttonText)) {
                state.setCurrentMonth(month);
                
                mainDisplay.displayTransactions();
                
            }
        
        }
        
    }
    
    private void displayTransactions(JTextArea area) {
        ArrayList<Transaction> transList = state.getCurrentMonth().getTransactions();
        
        if (transList.isEmpty()) {
            area.setText("No transactions in " + state.getCurrentMonth() + " of " + state.getCurrentYear() + "." + "\r\n" + "\r\n" + "Click 'Add Transactions' to add Transactions to this month.'" );
            
        } else {
            area.setText("Transactions for " + state.getCurrentMonth() + " of " + state.getCurrentYear() + "."  + "\r\n" + "\r\n" );
            
            for (Transaction trans : transList) {
                area.append(trans + "\r\n");
            }
        
        }
    
    }
    
    
}
