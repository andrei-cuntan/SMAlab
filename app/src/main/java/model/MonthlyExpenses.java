package model;


import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class MonthlyExpenses {
    public String month;
    private float income, expenses;
    public MonthlyExpenses(){

    }
    public MonthlyExpenses(String month, float income, float expenses){
        this.month = month;
        this.income = income;
        this.expenses = expenses;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("income", income);
        result.put("expenses", expenses);
        result.put("month", month);
        return result;
    }
    public String getMonth(){
        return month;
    }
    public float getExpenses(){
        return expenses;
    }
    public float getIncome(){
        return income;
    }
}
