package com.example.designsplitwise.service;

import com.example.designsplitwise.dto.ExpenseResponse;
import com.example.designsplitwise.dto.ExpenseSplits;
import com.example.designsplitwise.dto.SplitResponse;
import com.example.designsplitwise.model.Expense;
import com.example.designsplitwise.model.Group;
import com.example.designsplitwise.model.Split;
import com.example.designsplitwise.model.User;
import com.example.designsplitwise.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private SplitService splitService;
    private GroupService groupService;
    private final UserService userService;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, SplitService splitService, GroupService groupService, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.splitService = splitService;
        this.groupService = groupService;
        this.userService = userService;
    }

    public String createExpense(Expense expense, ExpenseSplits expenseSplits) {
        try{
            Expense savedExpense = expenseRepository.save(expense);
            List<User> users = userService.getUsers(expenseSplits.getUserIds());
            expenseSplits.setUsers(users);
            splitService.createSplits(savedExpense, expenseSplits);
            return savedExpense.getId();
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public String createGroupExpense(Expense expense, String groupId) {
        try{
            Group group = groupService.getGroup(groupId);
            expense.setGroup(group);
            Expense savedExpense = expenseRepository.save(expense);

            List<User> users = group.getGroupMembers();
            ExpenseSplits expenseSplits = new ExpenseSplits();
            expenseSplits.setUsers(users);

            splitService.createSplits(savedExpense, expenseSplits);
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
        response.setGroup(expense.getGroup());

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
