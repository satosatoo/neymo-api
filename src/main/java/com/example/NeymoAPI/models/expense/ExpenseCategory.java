package com.example.NeymoAPI.models.expense;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "expense_categories")
public class ExpenseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int category_id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Expense> expenses;

    public ExpenseCategory() {}

    public ExpenseCategory(String name) {
        this.name = name;
    }

    public int getCategory_id() {return category_id;}
    public void setCategory_id(int category_id) {this.category_id = category_id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public List<Expense> getExpenses() {return expenses;}
    public void setExpenses(List<Expense> expenses) {this.expenses = expenses;}
}
