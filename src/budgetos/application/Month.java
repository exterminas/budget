
package budgetos.application;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Michael
 */
public class Month implements Comparable<Month> {
    //This class represents the months of a year
    
    //Each month has an associated year, a name, and a list of transcations.
    private int year;
    private MonthName name;
    private ArrayList<Transaction> transactions;
    
    //Months can be created by year and name, the list of transactions is initially empty
    public Month(int year, MonthName name) {
        this.year = year;
        this.name = name;
        this.transactions = new ArrayList<Transaction>();
    }
    
    //A few standard setters and getters
 
    public int getYear() {
        return year;
    }
        
    public void setYear(Integer num) {
    this.year = num;
}

    public MonthName getName() {
        return name;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    
    //this method adds a transaction to the month's list of transactions
    public void addTransaction(Transaction aTransaction) {
        transactions.add(aTransaction);
    }

    //When comparing two months, check if they have the same name
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Month other = (Month) obj;
        if (this.name != other.name) {
            return false;
        }
        return true;
    }
    
    //String representations of each month by name
    public String toString() {
        HashMap<MonthName, String> monthMap = new HashMap<MonthName, String>();
        monthMap.put(MonthName.JANUARY, "January");
        monthMap.put(MonthName.FEBRUARY, "February");
        monthMap.put(MonthName.MARCH, "March");
        monthMap.put(MonthName.APRIL, "April");
        monthMap.put(MonthName.MAY, "May");
        monthMap.put(MonthName.JUNE, "June");
        monthMap.put(MonthName.JULY, "July");
        monthMap.put(MonthName.AUGUST, "August");
        monthMap.put(MonthName.SEPTEMBER, "September");
        monthMap.put(MonthName.OCTOBER, "October");
        monthMap.put(MonthName.NOVEMBER, "November");
        monthMap.put(MonthName.DECEMBER, "December");
        
        return monthMap.get(name);
    }
    
    //get numeric values for each month a year, staring with 1.
    public int getMonthNumber() {
        HashMap<MonthName, Integer> monthValueMap = new HashMap<MonthName, Integer>();
        monthValueMap.put(MonthName.JANUARY, 1);
        monthValueMap.put(MonthName.FEBRUARY, 2);
        monthValueMap.put(MonthName.MARCH, 3);
        monthValueMap.put(MonthName.APRIL, 4);
        monthValueMap.put(MonthName.MAY, 5);
        monthValueMap.put(MonthName.JUNE, 6);
        monthValueMap.put(MonthName.JULY, 7);
        monthValueMap.put(MonthName.AUGUST, 8);
        monthValueMap.put(MonthName.SEPTEMBER, 9);
        monthValueMap.put(MonthName.OCTOBER, 10);
        monthValueMap.put(MonthName.NOVEMBER, 11);
        monthValueMap.put(MonthName.DECEMBER, 12);
        
        return monthValueMap.get(this.getName());
    }
    
    
    //the numeric value is used to order months of a year
    @Override
    public int compareTo(Month o) {
        return this.getMonthNumber()-o.getMonthNumber();
        
        
    }

    
    
}
