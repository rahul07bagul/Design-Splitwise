package com.example.designsplitwise.service;

import com.example.designsplitwise.dto.ExpenseSplits;
import com.example.designsplitwise.model.Expense;
import com.example.designsplitwise.model.Split;
import com.example.designsplitwise.model.SplitType;
import com.example.designsplitwise.model.User;
import com.example.designsplitwise.repository.SplitRepository;
import com.example.designsplitwise.strategy.SplitStrategy;
import com.example.designsplitwise.strategy.SplitStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SplitService {
    private final SplitRepository splitRepository;

    @Autowired
    public SplitService(SplitRepository splitRepository) {
        this.splitRepository = splitRepository;
    }

    public void createSplits(Expense expense, ExpenseSplits expenseSplits) {
        SplitType type = expense.getType();
        SplitStrategy splitStrategy = SplitStrategyFactory.getSplitStrategy(type);
        List<Split> splits = splitStrategy.calculateSplits(expense, expenseSplits);
        splitRepository.saveAll(splits);
    }

    public List<Split> getSplitsByExpenseId(String expenseId) {
        return splitRepository.findByExpenseId(expenseId);
    }
}
