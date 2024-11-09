package com.example.NeymoAPI.services;

import com.example.NeymoAPI.dtos.UpdateInvestmentDto;
import com.example.NeymoAPI.models.investment.Investment;
import com.example.NeymoAPI.models.investment.InvestmentType;
import com.example.NeymoAPI.models.users.User;
import com.example.NeymoAPI.repositories.InvestmentRepository;
import com.example.NeymoAPI.repositories.InvestmentTypeRepository;
import com.example.NeymoAPI.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final UserService userService;
    private final InvestmentTypeRepository investmentTypeRepository;

    public InvestmentService(InvestmentRepository investmentRepository, UserService userService, InvestmentTypeRepository investmentTypeRepository) {
        this.investmentRepository = investmentRepository;
        this.userService = userService;
        this.investmentTypeRepository = investmentTypeRepository;
    }

    public Investment findInvestment(Long id) {
        return investmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Investment with id " + id + " not found"));
    }

    public List<Investment> findAllInvestments(Long id) {
        User user = userService.findUserById(id);
        return investmentRepository.findAllByUser(user);
    }

    public List<Investment> findAllInvestmentsByType(Long id, String type) {
        User user = userService.findUserById(id);
        InvestmentType investmentType = investmentTypeRepository.findByName(type);
        return investmentRepository.findAllByUserAndType(user, investmentType);
    }

    public Investment createInvestment(Investment investment) {
        // ADD USER
        return investmentRepository.save(investment);
    }

    public boolean deleteInvestment(Long id) {
        if (investmentRepository.existsById(id)) {
            investmentRepository.deleteById(id);
            return true;
        } else { return false; }
    }

    public Investment updateInvestment(Long id, UpdateInvestmentDto investmentDto) {
        Investment investment = investmentRepository.findById(id).orElse(null);

        if (investment != null) {
            if (investmentDto.getInvestment_name() != null && !Objects.equals(investmentDto.getInvestment_name(), investment.getInvestment_name())) {
                investment.setInvestment_name(investmentDto.getInvestment_name());
            }
            if (investmentDto.getQuantity() != null && !Objects.equals(investmentDto.getQuantity(), investment.getQuantity())) {
                investment.setQuantity(investmentDto.getQuantity());
            }
            if (investmentDto.getType() != null && !Objects.equals(investmentDto.getType(), investment.getType())) {
                investment.setType(investmentDto.getType());
            }
        } else {
            throw new EntityNotFoundException("Investment with id " + id + " not found");
        }

        return investmentRepository.save(investment);
    }
}
