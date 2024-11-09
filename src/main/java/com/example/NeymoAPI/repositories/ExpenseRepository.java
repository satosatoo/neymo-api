package com.example.NeymoAPI.repositories;

import com.example.NeymoAPI.models.expense.Expense;
import com.example.NeymoAPI.models.expense.ExpenseCategory;
import com.example.NeymoAPI.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT e FROM Expense e WHERE e.user = :user")
    List<Expense> findAllByUser(User user);

    @Query("SELECT e FROM Expense e WHERE e.category = :category AND e.user = :user")
    List<Expense> findAllByUserAndCategory(User user, ExpenseCategory category);
}
