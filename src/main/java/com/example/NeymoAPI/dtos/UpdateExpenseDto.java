package com.example.NeymoAPI.dtos;

import com.example.NeymoAPI.models.expense.ExpenseCategory;
import org.springframework.lang.Nullable;

public class UpdateExpenseDto {

    private String expense_name;
    private Double amount;
    private String description;
    private ExpenseCategory category;

    public UpdateExpenseDto(@Nullable String expense_name, @Nullable Double amount, @Nullable ExpenseCategory category, @Nullable String description) {
        this.expense_name = expense_name;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public String getExpense_name() {return expense_name;}
    public void setExpense_name(String expense_name) {this.expense_name = expense_name;}
    public Double getAmount() {return amount;}
    public void setAmount(Double amount) {this.amount = amount;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public ExpenseCategory getCategory() {return category;}
    public void setCategory(ExpenseCategory category) {this.category = category;}
}
