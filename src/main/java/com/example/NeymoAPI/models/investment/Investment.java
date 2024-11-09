package com.example.NeymoAPI.models.investment;

import com.example.NeymoAPI.models.users.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "investments")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long investment_id;

    private String investment_name;
    private Long quantity;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private InvestmentType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Investment() {}

    public Investment(String investment_name, Long quantity, InvestmentType type, LocalDate date, User user) {
        this.investment_name = investment_name;
        this.quantity = quantity;
        this.type = type;
        this.date = date;
        this.user = user;
    }

    public Long getInvestment_id() {return investment_id;}
    public void setInvestment_id(Long investment_id) {this.investment_id = investment_id;}
    public String getInvestment_name() {return investment_name;}
    public void setInvestment_name(String investment_name) {this.investment_name = investment_name;}
    public Long getQuantity() {return quantity;}
    public void setQuantity(Long quantity) {this.quantity = quantity;}
    public InvestmentType getType() {return type;}
    public void setType(InvestmentType type) {this.type = type;}
    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
}
