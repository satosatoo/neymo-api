package com.example.NeymoAPI.repositories;

import com.example.NeymoAPI.models.investment.InvestmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentTypeRepository extends JpaRepository<InvestmentType, Integer> {
    InvestmentType findByName(String name);
}
