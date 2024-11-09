package com.example.NeymoAPI.models.expense;

import com.example.NeymoAPI.models.users.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long expense_id;

    private String expense_name;
    private Double amount;
    private LocalDate date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ExpenseCategory category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Expense() {}

    public Expense(String expense_name, Double amount, ExpenseCategory category, LocalDate date, String description) {
        this.expense_name = expense_name;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    public Long getExpense_id() {return expense_id;}
    public void setExpense_id(Long expense_id) {this.expense_id = expense_id;}
    public String getExpense_name() {return expense_name;}
    public void setExpense_name(String expense_name) {this.expense_name = expense_name;}
    public Double getAmount() {return amount;}
    public void setAmount(Double amount) {this.amount = amount;}
    public ExpenseCategory getCategory() {return category;}
    public void setCategory(ExpenseCategory category) {this.category = category;}
    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
}
