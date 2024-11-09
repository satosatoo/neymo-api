package com.example.NeymoAPI.controllers;

import com.example.NeymoAPI.dtos.UpdateInvestmentDto;
import com.example.NeymoAPI.models.investment.Investment;
import com.example.NeymoAPI.services.InvestmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investment")
public class InvestmentController {

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Investment> getInvestment(Long id) {
        return ResponseEntity.ok(investmentService.findInvestment(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Investment>> getAllInvestments(Long id) {
        return ResponseEntity.ok(investmentService.findAllInvestments(id));
    }

    @GetMapping("/id/{id}/type/{type}")
    public ResponseEntity<List<Investment>> getAllInvestmentsByType(@PathVariable Long id, String type) {
        return ResponseEntity.ok(investmentService.findAllInvestmentsByType(id, type));
    }

    @PostMapping
    public ResponseEntity<Investment> createInvestment(@RequestBody Investment investment) {
        return ResponseEntity.ok(investmentService.createInvestment(investment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvestment(@PathVariable Long id) {
        boolean deleted = investmentService.deleteInvestment(id);
        if (deleted) {return ResponseEntity.ok("Investment with id " + id + " was deleted");}
        else { throw new EntityNotFoundException("Investment with id " + id + " not found"); }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Investment> updateInvestment(@PathVariable Long id, @RequestBody UpdateInvestmentDto investmentDto) {
        return ResponseEntity.ok(investmentService.updateInvestment(id, investmentDto));
    }
}
