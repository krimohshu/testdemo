package com.aryeet.demo.taxcalc;

import com.aryeet.demo.taxcalc.dto.RuleRunTimeStatusWithReason;
import com.aryeet.demo.taxcalc.dto.UserDataInputRule;
import com.aryeet.demo.taxcalc.exceptions.ErrorCode;
import com.aryeet.demo.taxcalc.exceptions.IncomeValueIsNullException;
import com.aryeet.demo.taxcalc.exceptions.TaxAmountCurrenyCodeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TaxCalc {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaxCalc.class);

    private int percent;
    private RuleRunTimeStatusWithReason ruleRunTimeStatusWithReason;


    TaxCalc(int percent) {
        this.percent = percent;
    }

    List<Pair<Integer, String>> netAmount(Pair<Integer, String> first, List<Pair<Integer, String>> pairs) {

        List<Pair<Integer, String>> pairsToReturn = new LinkedList<>();

        pairs.forEach(restOfIncome -> pairsToReturn.add(netAmount(first, restOfIncome)));
        return pairsToReturn;
    }

    Pair<Integer, String> netAmount(Pair<Integer, String> first, Pair<Integer, String>... rest) {

        List<Pair<Integer, String>> pairs = Arrays.asList(rest);
        Pair<Integer, String> total = first;


        checkNullOrEmptyUserInput(first, pairs);

        if (rest.length == 0) {
            return new Pair<>(total.first - getTaxAmount(first, total).first, first.second);
        }
        ruleRunTimeStatusWithReason = isCurrencyCodeMatchingForFirstAndRest(pairs, total);
        if (ruleRunTimeStatusWithReason.getIsFailed()) {
            throw new TaxAmountCurrenyCodeMismatchException(ruleRunTimeStatusWithReason.getReason(), ErrorCode.CC_MISMATCH);
        }


        for (Pair<Integer, String> next : pairs) {
            total = new Pair<>(total.first + next.first, next.second);
        }

        Pair<Integer, String> tax = getTaxAmount(first, total);

        if (total.second.equalsIgnoreCase(tax.second)) {
            return new Pair<>(total.first - tax.first, first.second);
        } else {
            ruleRunTimeStatusWithReason.setReason("Tax and C");
            throw new TaxAmountCurrenyCodeMismatchException(new RuleRunTimeStatusWithReason("Tax currencyCode mismatch with total currnet code").getReason(),
                    ErrorCode.CC_MISMATCH);
        }
    }

    private void checkNullOrEmptyUserInput(Pair<Integer, String> first, List<Pair<Integer, String>> pairs) {
        if (!(first != null && first.first != null && first.second != null && !first.second.isEmpty())) {
            throw new IncomeValueIsNullException("First Income not valid, Income cannot be null and cccode cannot be null or empty");

        }
        if (pairs.stream().anyMatch(x -> x == null)) {
            throw new IncomeValueIsNullException("pair Income object not valid, Income cannot be null ");
        }
        List<Integer> allPairIncome = pairs.stream().map(pairIncome -> pairIncome.first).collect(Collectors.toList());
        if (pairs.stream().map(pairIncome -> pairIncome.first).anyMatch(x -> x == null)) {
            throw new IncomeValueIsNullException("pair Income not valid, Income cannot be null ");
        }
        if (pairs.stream().map(pairIncome -> pairIncome.second).anyMatch(x -> x == null)) {
            throw new IncomeValueIsNullException("pair Income currenycode not valid, Income cannot be Empty ");
        }

        if (pairs.stream().map(pairIncome -> pairIncome.second).anyMatch(x -> x.isEmpty())) {
            throw new IncomeValueIsNullException("pair Income currenycode not valid, Income cannot be Empty ");
        }
    }

    private Pair<Integer, String> getTaxAmount(Pair<Integer, String> first, Pair<Integer, String> total) {
        Double amount = total.first * (percent / 100d);
        Pair<Integer, String> tax = new Pair<>(amount.intValue(), first.second);
        return tax;
    }

    private RuleRunTimeStatusWithReason isCurrencyCodeMatchingForFirstAndRest(List<Pair<Integer, String>> pairs, Pair<Integer, String> total) {
        boolean failedToMatchCurrencyCode = true;
        String reason = "";
        for (Pair<Integer, String> next : pairs) {
            if (next.second.equalsIgnoreCase(total.second)) {
                failedToMatchCurrencyCode = false;
                reason = "Currency Code Matched - Matching checked with case sensitivity";
            } else {
                reason = "Currency Code MIS-Match Error. " +
                        "First income CC-Code = " + total.second + " mismatched Other_Input_CC_CODE = " + next.second;
                failedToMatchCurrencyCode = true;
                break;
            }
        }
        return new RuleRunTimeStatusWithReason(UserDataInputRule.CC_MATCH_RULE, reason, failedToMatchCurrencyCode);
    }

    static class Pair<A, B> {
        final A first;
        final B second;

        Pair(A first, B second) {

            if(first !=null && second !=null) {
                this.first = first;
                this.second = second;
            }
            else{
                throw new IncomeValueIsNullException(" Income not valid, Income cannot be null and cccode cannot be null");
            }
        }

    }
}