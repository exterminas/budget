/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.gui;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Michael
 */
public class MainUISet {
    private MainDisplay display;
    private MonthDisplay monthDisplay;
    private JButton newYear;
    private JButton newTransaction;
    private JButton switchYear;
    private JButton saveButton;
    private JButton loadButton;
    private JButton transactionOptions;
    private JLabel curYear;

    public MainUISet(MainDisplay display, MonthDisplay monthDisplay, JButton newYear, JButton newTransaction, JButton switchYear, JButton saveButton, JButton loadButton, JButton transactionOptions, JLabel curYear) {
        this.display = display;
        this.monthDisplay = monthDisplay;
        this.newYear = newYear;
        this.newTransaction = newTransaction;
        this.switchYear = switchYear;
        this.saveButton = saveButton;
        this.loadButton = loadButton;
        this.transactionOptions = transactionOptions;
        this.curYear = curYear;
    }

    public MainDisplay getDisplay() {
        return display;
    }

    public void setDisplay(MainDisplay display) {
        this.display = display;
    }

    public MonthDisplay getMonthDisplay() {
        return monthDisplay;
    }

    public void setMonthDisplay(MonthDisplay monthDisplay) {
        this.monthDisplay = monthDisplay;
    }

    public JButton getNewYear() {
        return newYear;
    }

    public void setNewYear(JButton newYear) {
        this.newYear = newYear;
    }

    public JButton getNewTransaction() {
        return newTransaction;
    }

    public void setNewTransaction(JButton newTransaction) {
        this.newTransaction = newTransaction;
    }

    public JButton getSwitchYear() {
        return switchYear;
    }

    public void setSwitchYear(JButton switchYear) {
        this.switchYear = switchYear;
    }

    public JButton getSaveButton() {
        return this.saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public void setLoadButton(JButton loadButton) {
        this.loadButton = loadButton;
    }

    public JButton getTransactionOptions() {
        return transactionOptions;
    }

    public void setTransactionOptions(JButton transactionOptions) {
        this.transactionOptions = transactionOptions;
    }

    public JLabel getCurYear() {
        return curYear;
    }

    public void setCurYear(JLabel curYear) {
        this.curYear = curYear;
    }
    
    
    
    
}
