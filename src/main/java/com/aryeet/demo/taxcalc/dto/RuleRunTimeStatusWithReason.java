package com.aryeet.demo.taxcalc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RuleRunTimeStatusWithReason {
    private Enum userDataInputRule;
    private String reason;
    private Boolean isFailed;

    public  RuleRunTimeStatusWithReason(String reason){
        this.reason = reason;
    }
}
