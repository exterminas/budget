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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 *
 * @author Michael
 */
public class TransactionOptionsWindow implements ActionListener {

    private ProgramState state;
    private MainDisplay display;

    public TransactionOptionsWindow(ProgramState state, MainDisplay display) {
        this.state = state;
        this.display = display;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (state.getTransactionOptions() == false) {
            state.setTransactionOptions(true);
            createComponents();
        }
    }

    private void createComponents() {

        JFrame frame = new JFrame("Transaction Display Options");
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

        JLabel output = new JLabel();

        if (state.getDisplayAsList()) {

            output.setText("Transactions are currently being displayed as a list.");
            frame.add(output, constraints);
        } else {
            output.setText("Transactions are currently being displayed as sorted by their category.");
            frame.add(output, constraints);
        }

        JButton group = new JButton("Display transactions grouped by their category.");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        frame.add(group, constraints);

        JButton list = new JButton("Display transactions as a list.");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        frame.add(list, constraints);

        if (state.getDisplayAsList()) {
            list.setEnabled(false);
        } else {
            group.setEnabled(false);
        }

        SortAsCategory categoryWatcher = new SortAsCategory(state, display, output, list, group);
        group.addActionListener(categoryWatcher);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                state.setTransactionOptions(Boolean.FALSE);

            }
        });

        frame.pack();
        frame.setVisible(true);
    }

}
