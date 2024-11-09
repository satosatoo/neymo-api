package com.example.NeymoAPI.repositories;

import com.example.NeymoAPI.models.expense.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Integer> {
    ExpenseCategory findByName(String name);
}
