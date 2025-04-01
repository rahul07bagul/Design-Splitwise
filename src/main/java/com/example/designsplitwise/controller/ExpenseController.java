package com.example.designsplitwise.controller;

import com.example.designsplitwise.dto.ExpenseRequest;
import com.example.designsplitwise.dto.ExpenseResponse;
import com.example.designsplitwise.model.Expense;
import com.example.designsplitwise.service.ExpenseService;
import com.example.designsplitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/splitwise")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final UserService userService;

    @Autowired
    public ExpenseController(ExpenseService expenseService, UserService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }

    @PostMapping("/create/expense")
    public ResponseEntity<?> createExpense(@RequestBody ExpenseRequest request) {
        Expense expense = new Expense();
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setName(request.getName());
        expense.setType(request.getSplitType());
        expense.setCreatedBy(userService.getUser(request.getCreatedById()));

        String expenseId = expenseService.createExpense(expense, request.getSplits());

        return new ResponseEntity<>(expenseId, HttpStatus.CREATED);
    }

    @PostMapping("/create/expense/{groupId}")
    public ResponseEntity<?> createExpense(@PathVariable String groupId, @RequestBody ExpenseRequest request) {
        Expense expense = new Expense();
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setName(request.getName());
        expense.setType(request.getSplitType());
        expense.setCreatedBy(userService.getUser(request.getCreatedById()));

        String expenseId = expenseService.createGroupExpense(expense, groupId);

        return new ResponseEntity<>(expenseId, HttpStatus.CREATED);
    }

    @GetMapping("/expense/{expenseId}")
    public ResponseEntity<?> getExpense(@PathVariable String expenseId) {
        ExpenseResponse response = expenseService.getExpense(expenseId);
        return ResponseEntity.ok(response);
    }
}
