package com.example.designsplitwise.repository;

import com.example.designsplitwise.model.Split;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SplitRepository extends CrudRepository<Split, String>  {
    List<Split> findByExpenseId(String expenseId);
}
