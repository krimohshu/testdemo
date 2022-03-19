package com.aryeet.demo.taxcalc.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {
    CC_MISMATCH(100, "Taxable currency code mismatched.");

    private final int code;
    private final String description;

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
    }
