package com.example.NeymoAPI.dtos;

import com.example.NeymoAPI.models.investment.InvestmentType;
import org.springframework.lang.Nullable;

public class UpdateInvestmentDto {

    private String investment_name;
    private Long quantity;
    private InvestmentType type;

    public UpdateInvestmentDto(@Nullable String investment_name, @Nullable Long quantity, @Nullable InvestmentType type) {
        this.investment_name = investment_name;
        this.quantity = quantity;
        this.type = type;
    }

    public String getInvestment_name() {return investment_name;}
    public void setInvestment_name(String investment_name) {this.investment_name = investment_name;}
    public Long getQuantity() {return quantity;}
    public void setQuantity(Long quantity) {this.quantity = quantity;}
    public InvestmentType getType() {return type;}
    public void setType(InvestmentType type) {this.type = type;}
}
