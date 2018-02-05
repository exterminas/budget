/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.gui;

import budgetos.application.ProgramState;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 *
 * @author Michael
 */
public class NewYearWatcher implements ActionListener {

    private ProgramState state;
    private MainDisplay mainOutput;
    private MonthDisplay monthDisplay;
    private JButton transactions;
    private JButton options;
    private JLabel curyear;
    private JButton switchYear;
    private MainUISet uiSet;
    
    // JButton button, MonthDisplay monthDisplay, MainDisplay mainOutput, JButton transactions, JButton options, JLabel curyear, JButton switchYear

    public NewYearWatcher(ProgramState state, MainUISet uiSet) {
        this.state = state;
        this.mainOutput = uiSet.getDisplay();
        this.monthDisplay = uiSet.getMonthDisplay();
        this.transactions = uiSet.getNewTransaction();
        this.options = uiSet.getTransactionOptions();
        this.curyear = uiSet.getCurYear();
        this.switchYear = uiSet.getSwitchYear();
        this.uiSet = uiSet;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (state.getMonthWindow() == false) {
            state.setMonthWindow(true);
            createComponents();
        }

    }

    private void createComponents() {
        JFrame frame = new JFrame("New Year");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new BorderLayout());

        JLabel year = new JLabel("Enter Year:");
        centerPanel.add(year, BorderLayout.NORTH);

        JTextArea enterYear = new JTextArea();
        centerPanel.add(enterYear, BorderLayout.CENTER);

        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel lowerPanel = new JPanel(new BorderLayout());

        JLabel outputLabel = new JLabel("Click here to create a new Year:");
        lowerPanel.add(outputLabel, BorderLayout.NORTH);

        JButton createMonth = new JButton("OK");
        lowerPanel.add(createMonth, BorderLayout.CENTER);

        frame.add(lowerPanel, BorderLayout.SOUTH);

        NewYearEvent event = new NewYearEvent(state, enterYear, outputLabel, monthDisplay, transactions, options, curyear, switchYear, uiSet);
        createMonth.addActionListener(event);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                state.setMonthWindow(false);

            }
        });

        frame.pack();
        frame.setVisible(true);

    }

}
