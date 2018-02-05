/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.gui;

import budgetos.application.ProgramState;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author Michael
 */
public class UserInterface implements Runnable {

    private JFrame frame;
    private ProgramState programState;

    @Override
    public void run() {
        //create a frame

        frame = new JFrame("Budget Os");
        frame.setPreferredSize(new Dimension(1024, 768));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        programState = new ProgramState();
        createComponents(frame.getContentPane(), programState);

        //Once the frame is complete it is being displayed
        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container contentPane, ProgramState programState) {
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        JToolBar toolBar = new JToolBar();
        JButton loadButton = new JButton("Load Budget Data");
        JButton saveButton = new JButton("Save Budget Data");
        saveButton.setEnabled(false);
        JButton extrapolate = new JButton("Wild Extrapolation");
        toolBar.add(loadButton);
        toolBar.add(saveButton);
        toolBar.add(extrapolate);
        toolBar.setFloatable(false);
        toolBar.setBorderPainted(true);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 6;
        constraints.weightx = 0.1;
        constraints.weighty = 0.0;
        constraints.fill = GridBagConstraints.BOTH;
        contentPane.add(toolBar, constraints);

        JPanel sideBar = new JPanel(new GridBagLayout());
        sideBar.setVisible(true);

        JLabel monthLabel = new JLabel();
        monthLabel.setText("Current year: ");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.01;
        constraints.weighty = 0.01;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        sideBar.add(monthLabel, constraints);

        MainDisplay mainText = new MainDisplay("Welcome to Budget-Os. An easy to use ap to manage your personal budget." + "\r\n" + "Get started by adding a new Year you want to manage by clicking 'Add new Year' on the left Toolbar.", programState);
        mainText.setEditable(false);
        mainText.setFont(mainText.getFont().deriveFont(18f));

        JPanel mainTextPanel = new JPanel(new BorderLayout());
        mainTextPanel.add(mainText, BorderLayout.CENTER);

        JScrollPane scroll = new JScrollPane(mainText);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mainTextPanel.add(scroll);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 5;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;

        contentPane.add(mainTextPanel, constraints);

        MonthDisplay monthDisplay = new MonthDisplay(programState, mainText);
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridheight = 3;
        constraints.gridwidth = 1;
        constraints.weightx = 0.8;
        constraints.weighty = 0.8;
        constraints.fill = GridBagConstraints.BOTH;
        monthDisplay.setEnabled(false);
        sideBar.add(monthDisplay, constraints);

        JButton newMonthButton = new JButton("Add new Year");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.25;
        constraints.weighty = 0.25;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        sideBar.add(newMonthButton, constraints);
        
        JButton newTransactionButton = new JButton("New Transaction");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.25;
        constraints.weighty = 0.25;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        newTransactionButton.setEnabled(false);
        sideBar.add(newTransactionButton, constraints);
        

        JButton curYearButton = new JButton("Switch Year");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.25;
        constraints.weighty = 0.25;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        curYearButton.setEnabled(false);
        sideBar.add(curYearButton, constraints);
        


        JButton optionsButton = new JButton("Options");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.25;
        constraints.weighty = 0.25;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        optionsButton.setEnabled(false);
        sideBar.add(optionsButton, constraints);



        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        contentPane.add(sideBar, constraints);
        
        MainUISet uiSet = new MainUISet(mainText, monthDisplay, newMonthButton, newTransactionButton, curYearButton, saveButton, loadButton, optionsButton, monthLabel);
        
        NewYearWatcher yearWatcher = new NewYearWatcher(programState, uiSet);
        newMonthButton.addActionListener(yearWatcher);
        
        SwitchYearWindow switchYearWatcher = new SwitchYearWindow(programState, uiSet); 
        curYearButton.addActionListener(switchYearWatcher);
        
        NewTransactionsWindow transWatcher = new NewTransactionsWindow(mainText, programState);
        newTransactionButton.addActionListener(transWatcher);
        
        TransactionOptionsWindow optionsWatcher = new TransactionOptionsWindow(programState, mainText);
        optionsButton.addActionListener(optionsWatcher);
        
        LoadButtonEvent loadwatcher = new LoadButtonEvent(programState, uiSet);
        loadButton.addActionListener(loadwatcher);
        
        SaveButtonEvent saveWatcher = new SaveButtonEvent(programState, uiSet);
        saveButton.addActionListener(saveWatcher);

    }

}
