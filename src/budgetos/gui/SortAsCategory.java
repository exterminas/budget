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

/**
 *
 * @author Michael
 */
public class SortAsCategory implements ActionListener {
    private ProgramState state;
    private MainDisplay display;
    private JLabel output;
    private JButton asList;
    private JButton asGroup;

    public SortAsCategory(ProgramState state, MainDisplay display, JLabel output, JButton asList, JButton asGroup) {
        this.state = state;
        this.display = display;
        this.output = output;
        this.asList = asList;
        this.asGroup = asGroup;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        state.setDisplayAsList(Boolean.FALSE);
        output.setText("Transactions are currently being displayed as sorted by their category.");
        display.displayTransactions();
        asGroup.setEnabled(false);
        asList.setEnabled(true);
        
    }
    
    
    
    
}
