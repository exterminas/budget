/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.gui;

import budgetos.application.ProgramState;
import budgetos.application.Transaction;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Michael
 */
public class MainDisplay extends JTextArea {

    private ProgramState state;

    public MainDisplay(String blub, ProgramState state) {
        super(blub);
        this.state = state;
    }

    public void displayTransactions() {

        if (!state.getCurrentMonth().getTransactions().isEmpty()) {

            if (state.getDisplayAsList()) {

                super.setText("Transactions for " + state.getCurrentMonth() + " of " + state.getCurrentYear() + "." + "\r\n");
                super.append("\r\n");

                for (Transaction trans : state.getCurrentMonth().getTransactions()) {
                    super.append(trans.getCategory() + " : " + trans.getDescription() + "\r\n");
                    super.append("      " + trans + "\r\n" + "\r\n");
                }
            } else if (state.getDisplayAsList() == false) {
                super.setText("Transactions for " + state.getCurrentMonth() + " of " + state.getCurrentYear() + "." + "\r\n");
                super.append("\r\n");

                for (String cat : state.getCategories()) {
                    ArrayList<Transaction> searchResults = new ArrayList<Transaction>();

                    for (Transaction trans : state.getCurrentMonth().getTransactions()) {
                        if (trans.getCategory().equals(cat)) {
                            searchResults.add(trans);

                        }

                    }

                    if (!searchResults.isEmpty()) {
                        super.append("\r\n");
                        super.append(cat + " : " + "\r\n");
                        
                        for (Transaction trans : searchResults) {
                            
                            
                            super.append("      " + trans + " : " + trans.getDescription() + "\r\n");
                        }

                    }

                }

            }

        } else {super.setText("No transactions in " + state.getCurrentMonth() + " of " + state.getCurrentYear() + "." + "\r\n" + "\r\n" + "Click 'Add Transactions' to add Transactions to this month.'" ); }

    }

}
