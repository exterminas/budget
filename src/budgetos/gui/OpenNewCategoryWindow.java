/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.gui;

import budgetos.application.ProgramState;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 *
 * @author Michael
 */
public class OpenNewCategoryWindow implements ActionListener {

    private JComboBox combo;
    private ProgramState state;
    private JFrame transactions;

    public OpenNewCategoryWindow(JComboBox combo, ProgramState state, JFrame transactions) {
        this.combo = combo;
        this.state = state;
        this.transactions = transactions;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (state.getCategoryWindow() == false) {
            state.setCategoryWindow(true);
            createComponents();
        }

    }

    private void createComponents() {
       
        JFrame frame = new JFrame("Create custom transaction category");
        frame.setPreferredSize(new Dimension(500, 200));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel header = new JLabel("Enter a name for the new category.");
        frame.add(header, BorderLayout.NORTH);

        JTextArea enter = new JTextArea();
        frame.add(enter, BorderLayout.CENTER);
        
        JButton done = new JButton("Add this custom category.");
        frame.add(done, BorderLayout.SOUTH);
        
        AddNewCategory listener = new AddNewCategory(enter, state, combo, transactions, header);
        done.addActionListener(listener);
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                state.setCategoryWindow(Boolean.FALSE);

            }
        });

        frame.pack();
        frame.setVisible(true);
    }
    
    
    

}
