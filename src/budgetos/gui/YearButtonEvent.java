/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.gui;

import budgetos.application.ProgramState;
import budgetos.application.Year;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Michael
 */
public class YearButtonEvent implements ActionListener {
    private ProgramState state;
    private MainDisplay display;
    private JButton button;
    private JLabel yearLabel;

    public YearButtonEvent(ProgramState state, MainDisplay display, JButton button, JLabel yearLabel) {
        this.state = state;
        this.display = display;
        this.button = button;
        this.yearLabel = yearLabel;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String buttonLabel = button.getText();
        
        for (Year year : state.getYears()) {
            if (year.toString().equals(buttonLabel)) {
                state.setCurrentYear(year);
                state.setCurrentMonth(year.getMonths().get(0));
                display.displayTransactions();
                yearLabel.setText("Current year: " + year);
            }
        }
        
        
    }
    
    
    
}
