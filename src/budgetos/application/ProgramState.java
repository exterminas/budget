/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.application;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Michael
 */
public class ProgramState {

    //This class is used to hold the current budget data and information about the state of the program.
    //It holds a list of years
    private ArrayList<Year> years;

    //It stores one year (of the list) as the currently selected year
    private Year currentYear;

    //... and one month of that year as the currently selected month
    private Month currentMonth;

    //It also stores all the possibly categories for Transactions (including custom categories entered by the user)
    private ArrayList<String> categories;

    // this class also keeps track of which additional windows of the app are currently open
    private Boolean monthWindow;
    private Boolean transactionWindow;
    private Boolean categoryWindow;
    private Boolean displayAsList;
    private Boolean switchYearWindow;
    
    //finally, it tracks currently slected display options for transactions
    private Boolean transactionOptions;

    public ProgramState() {

        //List of years starts out as empty
        years = new ArrayList<Year>();

        //the list of categories contains only default categories initially
        categories = new ArrayList<String>();
        String[] cat = {"Food", "Rent", "Car", "Insurance", "Luxury", "Medical", "Utilities"};
        categories.addAll(Arrays.asList(cat));

        //all the windows are closed on start up
        transactionWindow = false;
        monthWindow = false;
        categoryWindow = false;
        transactionOptions = false;
        switchYearWindow = false;
        
        //by default, transactions are displayed as a list
        displayAsList = true;
    }

    public void addYear(Integer year) {
        //this method takes and integer and creates an appropriate object of the Year class to add it to the list of years tracked by the program state.

        Boolean yearcheck = false;
        
        //first it checks if there is already a year with this number on the list
        for (Year anno : years) {
            if (anno.getYearNumber() == year) {
                yearcheck = true;
            }
        }

        //if that's not the case...
        if (!yearcheck) {
            //a new year object is created
            Year newYear = new Year(year);
            years.add(newYear);
            
            //if becomes the current year
            currentYear = newYear;
            
            //january of that year becomes current month
            currentMonth = newYear.getMonth(0);
            yearcheck = false;
        }

    }
    
    //standard setters and getters

    public Month getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(Month month) {
        currentMonth = month;
    }

    public void setCurrentMonth(String monthName) {
        for (Month month : this.currentYear.getMonths()) {
            if (month.toString().equals(monthName)) {
                this.currentMonth = month;
            }

        }

    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public ArrayList<Year> getYears() {
        return years;
    }

    public void setYears(ArrayList<Year> years) {
        this.years = years;
    }

    public Boolean getDisplayAsList() {
        return displayAsList;
    }

    public void setDisplayAsList(Boolean displayAsList) {
        this.displayAsList = displayAsList;
    }

    public Boolean getSwitchYearWindow() {
        return switchYearWindow;
    }

    public void setSwitchYearWindow(Boolean switchYearWindow) {
        this.switchYearWindow = switchYearWindow;
    }
    
    
//this method takes a transaction and two integers for year and month and adds the transaction to the appropriate month of that year in the list of all years
    public void addTransaction(Integer year, Integer month, Transaction trans) {
        for (Year billy : years) {
            if (billy.getYearNumber() == year) {
                billy.getMonths().get(month).addTransaction(trans);
            }

        }

    }

    public void addCategory(String cat) {
        if (!this.categories.contains(cat)) {
            this.categories.add(cat);
        }

    }

    public void setCurrentYear(Integer yearNum) {
        for (Year year : years) {
            if (year.getYearNumber() == yearNum) {
                this.currentYear = year;

            }

        }

    }

    //printing programming states as strings is only done for debugging purposes. It prints a list of years.
    @Override
    public String toString() {
        String results = "";

        for (Year anno : years) {
            results = results + anno + ": ";

        }

        return results;

    }

    public Boolean getMonthWindow() {
        return this.monthWindow;
    }

    public void setMonthWindow(Boolean bool) {
        this.monthWindow = bool;
    }

    public Boolean getTransactionWindow() {
        return transactionWindow;
    }

    public void setTransactionWindow(Boolean transactionWindow) {
        this.transactionWindow = transactionWindow;
    }

    public Boolean getTransactionOptions() {
        return transactionOptions;
    }

    public void setTransactionOptions(Boolean transactionOptions) {
        this.transactionOptions = transactionOptions;
    }

    public Year getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(Year anotherYear) {
        this.currentYear = anotherYear;
    }

    public Boolean getCategoryWindow() {
        return categoryWindow;
    }

    public void setCategoryWindow(Boolean categoryWindow) {
        this.categoryWindow = categoryWindow;
    }

}
