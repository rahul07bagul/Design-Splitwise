package com.example.designsplitwise.strategy;

import com.example.designsplitwise.dto.ExpenseSplits;
import com.example.designsplitwise.model.Expense;
import com.example.designsplitwise.model.Split;
import com.example.designsplitwise.model.User;
import jakarta.websocket.OnError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EqualSplitStrategy implements SplitStrategy {
    @Override
    public List<Split>  calculateSplits(Expense expense, ExpenseSplits expenseSplits, List<User> users){
        List<Split> splits = new ArrayList<>();
        int totalUsers = expenseSplits.getUserIds().size();
        double amountPerUser = expense.getAmount() / totalUsers;

        for(User user : users){
            Split split = new Split();
            split.setUser(user);
            split.setExpense(expense);
            split.setSplitAmount(amountPerUser);
            split.setOwe(!user.getId().equals(expense.getCreatedBy().getId()));
            splits.add(split);
        }

        return splits;
    }
}
