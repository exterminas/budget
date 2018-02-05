/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetos.application;

/**
 *
 * @author Michael
 */
public class Transaction {
    //This class represents a transaction of money
    
    //it stores a transaction value
    private double value;
    
    //an (optional) description of the transaction
    private String description;
    
    //and associated category for that transaction
    private String category;

    public Transaction(Double value, String description, String category) {
        this.value = value;
        this.description = description;
        this.category = category;
    }

    public Double getValue() {
        return value;
    }



    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        String result = "";
        
        result = result + value;
        return result;
    }

}
