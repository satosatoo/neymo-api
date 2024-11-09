package com.example.NeymoAPI.repositories;

import com.example.NeymoAPI.models.expense.Expense;
import com.example.NeymoAPI.models.expense.ExpenseCategory;
import com.example.NeymoAPI.models.investment.Investment;
import com.example.NeymoAPI.models.investment.InvestmentType;
import com.example.NeymoAPI.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    @Query("SELECT i FROM Investment i WHERE i.user = :user")
    List<Investment> findAllByUser(User user);

    @Query("SELECT i FROM Investment i WHERE i.type = :type AND i.user = :user")
    List<Investment> findAllByUserAndType(User user, InvestmentType type);
}
