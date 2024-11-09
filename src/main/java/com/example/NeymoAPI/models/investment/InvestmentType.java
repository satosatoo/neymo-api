package com.example.NeymoAPI.models.investment;

import com.example.NeymoAPI.models.expense.Expense;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "investment_types")
public class InvestmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int type_id;

    private String name;

    @OneToMany(mappedBy = "type")
    private List<Investment> investments;

    public InvestmentType() {}

    public InvestmentType(String name) {
        this.name = name;
    }

    public int getType_id() {return type_id;}
    public void setType_id(int type_id) {this.type_id = type_id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public List<Investment> getInvestments() {return investments;}
    public void setInvestments(List<Investment> investments) {this.investments = investments;}
}
