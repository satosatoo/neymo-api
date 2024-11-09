package com.example.NeymoAPI.controllers;

import com.example.NeymoAPI.models.investment.InvestmentType;
import com.example.NeymoAPI.repositories.InvestmentTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investments")
public class InvestmentTypeController {

    private final InvestmentTypeRepository investmentTypeRepository;

    public InvestmentTypeController(InvestmentTypeRepository investmentTypeRepository) {
        this.investmentTypeRepository = investmentTypeRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestmentType> getInvestmentType(@PathVariable Integer id) {
        return ResponseEntity.ok(investmentTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Investment type with id " + id + " not found")));
    }

    @GetMapping
    public ResponseEntity<List<InvestmentType>> getAllInvestmentTypes() {
        return ResponseEntity.ok(investmentTypeRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<InvestmentType> createInvestmentType(@RequestBody InvestmentType investmentType) {
        return ResponseEntity.ok(investmentTypeRepository.save(investmentType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvestmentType(@PathVariable Integer id) {
        if (investmentTypeRepository.existsById(id)) {
            investmentTypeRepository.deleteById(id);
            return ResponseEntity.ok("Investment type successfully was deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
