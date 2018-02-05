/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.application;

import java.util.ArrayList;

/**
 *
 * @author Michael
 */
public class Year {
    //this class is used to represent years
    
    //each year has a number and a list of month objects.
    private ArrayList<Month> months;
    private int yearNumber;
    
    public Year(int yearNumber) {
       this.yearNumber = yearNumber; 
       months = new ArrayList<Month>();
       MonthName[] monthNames = new MonthName[]{MonthName.JANUARY, MonthName.FEBRUARY, MonthName.MARCH, MonthName.APRIL, MonthName.MAY, MonthName.JUNE, MonthName.JULY, MonthName.AUGUST, MonthName.SEPTEMBER, MonthName.OCTOBER, MonthName.NOVEMBER, MonthName.DECEMBER};
       
       for (MonthName name : monthNames) {
           months.add(new Month(yearNumber, name));
       }
        
    }
    
    public Month getMonth(int num) {
        
        
        
        return months.get(num);
    }
    
    public int getYearNumber() {
        return yearNumber;
    }
    
    public String toString() {
        return "" + this.yearNumber;
    
    }
    
    public ArrayList<Month> getMonths() {
        return months;
    }
    
}
