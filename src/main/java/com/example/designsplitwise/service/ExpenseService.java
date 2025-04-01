package com.example.designsplitwise.service;

import com.example.designsplitwise.dto.ExpenseResponse;
import com.example.designsplitwise.dto.ExpenseSplits;
import com.example.designsplitwise.dto.SplitResponse;
import com.example.designsplitwise.model.Expense;
import com.example.designsplitwise.model.Split;
import com.example.designsplitwise.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private SplitService splitService;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, SplitService splitService) {
        this.expenseRepository = expenseRepository;
        this.splitService = splitService;
    }

    public String createExpense(Expense expense, ExpenseSplits splits) {
        try{
            Expense savedExpense = expenseRepository.save(expense);
            splitService.createSplits(savedExpense, splits);
            return savedExpense.getId();
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public ExpenseResponse getExpense(String expenseId){
        Expense expense = expenseRepository.getExpenseById(expenseId);
        if (expense == null) {
            return null;
        }

        List<Split> splits = splitService.getSplitsByExpenseId(expenseId);
        ExpenseResponse response = new ExpenseResponse();
        response.setId(expense.getId());
        response.setName(expense.getName());
        response.setDescription(expense.getDescription());
        response.setAmount(expense.getAmount());
        response.setSplitType(expense.getType());
        response.setCreatedBy(expense.getCreatedBy());

        List<SplitResponse> splitResponses = new ArrayList<>();
        for (Split split : splits) {
            SplitResponse splitResponse = new SplitResponse();
            splitResponse.setId(split.getId());
            splitResponse.setUser(split.getUser());
            splitResponse.setAmount(split.getSplitAmount());
            splitResponse.setOwing(split.isOwe());
            splitResponses.add(splitResponse);
        }

        response.setSplits(splitResponses);

        return response;
    }
}
