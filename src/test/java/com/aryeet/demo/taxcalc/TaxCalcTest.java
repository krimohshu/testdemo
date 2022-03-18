package com.aryeet.demo.taxcalc;

import com.aryeet.demo.taxcalc.exceptions.IncomeValueIsNullException;
import com.aryeet.demo.taxcalc.exceptions.TaxAmountCurrenyCodeMismatchException;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestName;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.assertj.core.api.Assertions.*;


public class TaxCalcTest {

    @Rule
    public TestName testName = new TestName();

    private TaxCalc taxCalc;

    @Before
    public void setup() {
        taxCalc = new TaxCalc(10);
        System.out.println("Start " + testName.getMethodName());
    }

    @Category(Priorty1Tests.class)
    @Test
    public void canCalculateTaxDueWithSingleIncomeInput() throws Exception {
        Integer first = taxCalc.netAmount(new TaxCalc.Pair<>(40, "GBP"))
                .first;
        assertEquals(36, first.intValue());

    }

    @Category(Priorty1Tests.class)
    @Test
    public void canCalculateTaxWithCaseInsensitiveCCode() throws Exception {
        Integer first = taxCalc.netAmount(new TaxCalc.Pair<>(40, "gbp"), new TaxCalc.Pair<>(50, "Gbp"), new TaxCalc.Pair<>(60, "GBP"))
                .first;
        assertEquals(135, first.intValue());

    }

    @Category(Priorty0Tests.class)
    @Test(expected = TaxAmountCurrenyCodeMismatchException.class)
    public void cannotCalculateTaxDueToMismatchCurrencyCode() throws Exception {
        Integer first = taxCalc.netAmount(new TaxCalc.Pair<>(40, "GBP"), new TaxCalc.Pair<>(50, "USD"), new TaxCalc.Pair<>(60, "GBP"))
                .first;
    }

    @Category(Priorty0Tests.class)
    @Test
    public void canCalculateTax() throws Exception {
        Integer first = new TaxCalc(10)
                .netAmount(new TaxCalc.Pair<>(40, "GBP"), new TaxCalc.Pair<>(50, "GBP"), new TaxCalc.Pair<>(60, "GBP"))
                .first;
        assertEquals(135, first.intValue());
    }


    @Category(WIPTests.class)
    @Test(timeout = 500)
    public void canCalculateTaxWithManyTaxableIncomeInputs() throws Exception {
        List<TaxCalc.Pair<Integer, String>> rest = new LinkedList<>();
        int totalVal = 40;
        for (int i = 10; i <= 1000; i++) {
            rest.add(new TaxCalc.Pair<>(i, "GBP"));
            totalVal = totalVal + i;
        }
        List<TaxCalc.Pair<Integer, String>> first = new TaxCalc(10)
                .netAmount(new TaxCalc.Pair<>(40, "GBP"), rest);

        Integer sum  = first.stream().map( x-> x.first).reduce(0, Integer::sum);
    }
}