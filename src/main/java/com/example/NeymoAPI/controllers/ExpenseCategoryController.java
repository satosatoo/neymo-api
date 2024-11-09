package com.example.NeymoAPI.controllers;

import com.example.NeymoAPI.models.expense.ExpenseCategory;
import com.example.NeymoAPI.repositories.ExpenseCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses/category")
public class ExpenseCategoryController {

    private final ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseCategoryController(ExpenseCategoryRepository expenseCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseCategory> getExpenseCategory(@PathVariable Integer id) {
        return ResponseEntity.ok(expenseCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense category with id " + id + " not found")));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseCategory>> getAllExpenseCategories() {
        return ResponseEntity.ok(expenseCategoryRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<ExpenseCategory> createExpenseCategory(@RequestBody ExpenseCategory expenseCategory) {
        return ResponseEntity.ok(expenseCategoryRepository.save(expenseCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpenseCategory(@PathVariable Integer id) {
        if (expenseCategoryRepository.existsById(id)) {
            expenseCategoryRepository.deleteById(id);
            return ResponseEntity.ok("Expense category successfully was deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
