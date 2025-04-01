package com.example.designsplitwise.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ExpenseSplits {
    private List<String> userIds;
    private Map<String, Double> percentages;
    private Map<String, Double> exactAmounts;
}
