package com.example.NeymoAPI.services;

import com.example.NeymoAPI.dtos.UpdateExpenseDto;
import com.example.NeymoAPI.models.expense.Expense;
import com.example.NeymoAPI.models.expense.ExpenseCategory;
import com.example.NeymoAPI.models.users.User;
import com.example.NeymoAPI.repositories.ExpenseCategoryRepository;
import com.example.NeymoAPI.repositories.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final UserService userService;

    public ExpenseService(ExpenseRepository expenseRepository, ExpenseCategoryRepository expenseCategoryRepository, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.userService = userService;
    }

    public Expense findExpense(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense with id " + id + " not found"));
    }

    public List<Expense> findAllExpenses(Long id) {
        User user = userService.findUserById(id);
        return expenseRepository.findAllByUser(user);
    }

    public List<Expense> findAllExpensesByCategory(Long id, String category) {
        User user = userService.findUserById(id);
        ExpenseCategory expenseCategory = expenseCategoryRepository.findByName(category);
        return expenseRepository.findAllByUserAndCategory(user, expenseCategory);
    }

    public Expense createExpense(Expense expense) {
        // User user = new User(); // need to change
        // expense.setUser(user);
        return expenseRepository.save(expense);
    }

    public boolean deleteExpense(Long id) {
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            return true;
        } else { return false; }
    }

    public Expense updateExpense(Long id, UpdateExpenseDto expenseDto) {

        Expense existingExpense = expenseRepository.findById(id).orElse(null);

        if (existingExpense != null) {
            if (expenseDto.getExpense_name() != null || !expenseDto.getExpense_name().equals(existingExpense.getExpense_name())) {
                existingExpense.setExpense_name(expenseDto.getExpense_name());
            }
            if (expenseDto.getAmount() != null || !expenseDto.getAmount().equals(existingExpense.getAmount()) || expenseDto.getAmount() <= 0) {
                existingExpense.setAmount(expenseDto.getAmount());
            }
            if (expenseDto.getCategory() != null || !expenseDto.getCategory().equals(existingExpense.getCategory())) {
                existingExpense.setCategory(expenseDto.getCategory());
            }
            existingExpense.setDescription(expenseDto.getDescription());
        } else {
            throw new EntityNotFoundException("Expense with id " + id + " not found");
        }

        return expenseRepository.save(existingExpense);
    }
}
