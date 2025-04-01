package com.example.designsplitwise.dto;

import com.example.designsplitwise.model.SplitType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseRequest {
    private String name;
    private String description;
    private Double amount;
    private SplitType splitType;
    private String createdById;
    private ExpenseSplits splits;

    public Double getAmount() {
        return this.amount;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public SplitType getSplitType() {
        return this.splitType;
    }

    public String getCreatedById() {
        return this.createdById;
    }

    public ExpenseSplits getSplits() {
        return this.splits;
    }
}
