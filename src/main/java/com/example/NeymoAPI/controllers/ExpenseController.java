package com.example.NeymoAPI.controllers;

import com.example.NeymoAPI.dtos.UpdateExpenseDto;
import com.example.NeymoAPI.models.expense.Expense;
import com.example.NeymoAPI.services.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.findExpense(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Expense>> getAllExpenses(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.findAllExpenses(id));
    }

    @GetMapping("/all/{id}/category/{category}")
    public ResponseEntity<List<Expense>> getAllExpensesByCategory(@PathVariable Long id, @PathVariable String category) {
        return ResponseEntity.ok(expenseService.findAllExpensesByCategory(id, category));
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.createExpense(expense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        boolean deleted = expenseService.deleteExpense(id);
        if (deleted) {return ResponseEntity.ok("Expense with id " + id + " was deleted");}
        else {return ResponseEntity.ok("Expense with id " + id + " not found");}
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody UpdateExpenseDto expenseDto) {
        return ResponseEntity.ok(expenseService.updateExpense(id, expenseDto));
    }
}
