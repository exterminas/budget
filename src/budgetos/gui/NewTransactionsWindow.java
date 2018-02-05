/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.gui;

import budgetos.application.ProgramState;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 *
 * @author Michael
 */
public class NewTransactionsWindow implements ActionListener {

    private MainDisplay Display;
    private ProgramState state;

    public NewTransactionsWindow(MainDisplay Display, ProgramState state) {
        this.Display = Display;
        this.state = state;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (state.getTransactionWindow() == false) {
            state.setTransactionWindow(true);
            createComponents();
        }
    }

    private void createComponents() {
        JFrame frame = new JFrame("New Transactions");
        frame.setPreferredSize(new Dimension(500, 200));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        
        JLabel transLabel = new JLabel("Enter Transaction Value:");
        frame.add(transLabel, constraints);
        
        JTextArea transArea = new JTextArea();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        frame.add(transArea, constraints);
        
        JLabel categoryLabel = new JLabel("Select Transaction category:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        frame.add(categoryLabel, constraints);
        
        String[] cat = new String[state.getCategories().size()];
        cat = state.getCategories().toArray(cat);
        JComboBox categories = new JComboBox(cat);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        frame.add(categories, constraints);
        
        JButton categoryButton = new JButton("Create custom category");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        frame.add(categoryButton, constraints);
        OpenNewCategoryWindow opener = new OpenNewCategoryWindow(categories, state, frame);
        categoryButton.addActionListener(opener);
        
        JLabel enterDescription = new JLabel("Enter an optional Transaction description:");
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        frame.add(enterDescription, constraints);
        
        JTextArea Description = new JTextArea("Blabla");
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridheight = 4;
        constraints.gridwidth = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;
        frame.add(Description, constraints);
        
        JLabel output = new JLabel("Click below when you are done.");
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        frame.add(output, constraints);
             
        JButton confirm = new JButton("Add this Transaction");
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        frame.add(confirm, constraints);
        NewTransactions transWatcher = new NewTransactions(state, transArea, categories, Description, output, Display);
        confirm.addActionListener(transWatcher);
     
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                state.setTransactionWindow(Boolean.FALSE);

            }
        });

        frame.pack();
        frame.setVisible(true);

    }

}
