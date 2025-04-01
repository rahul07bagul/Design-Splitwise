package com.example.designsplitwise.repository;

import com.example.designsplitwise.model.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, String> {
    Expense getExpenseById(String id);
}
