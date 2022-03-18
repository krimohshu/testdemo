package com.aryeet.demo.taxcalc;

import com.aryeet.demo.taxcalc.exceptions.IncomeValueIsNullException;
import com.aryeet.demo.taxcalc.exceptions.TaxAmountCurrenyCodeMismatchException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;


public class TaxCalcIncomeNullTest {

    @Rule
    public TestName testName = new TestName();

    private TaxCalc taxCalc;

    @Before
    public void setup() {
        taxCalc = new TaxCalc(10);
        System.out.println("Start " + testName.getMethodName());
    }


    @Test(expected= IncomeValueIsNullException.class)
    public void canHandleNullInNetIncomeInput() throws Exception {
        //SoftAssertions softly = new SoftAssertions();
        taxCalc.netAmount(null);
        // softly.assertAll();
    }
    @Test(expected= IncomeValueIsNullException.class)
    public void canHandleNullInNetIncomeMoneyInput() throws Exception {
        //SoftAssertions softly = new SoftAssertions();
        taxCalc.netAmount(new TaxCalc.Pair<>(null, "gbp"));
        // softly.assertAll();
    }
    @Test(expected= IncomeValueIsNullException.class)
    public void canHandleNullInNetIncomeCurrencyInput() throws Exception {
        //SoftAssertions softly = new SoftAssertions();
        taxCalc.netAmount(new TaxCalc.Pair<>(40, null));
        // softly.assertAll();
    }
    @Test(expected= IncomeValueIsNullException.class)
    public void canHandleEmptyInNetIncomeCurrencyInput() throws Exception {
        //SoftAssertions softly = new SoftAssertions();
        taxCalc.netAmount(new TaxCalc.Pair<>(40, ""));
        // softly.assertAll();
    }

    @Test(expected= IncomeValueIsNullException.class)
    public void canHandleNullInNetIncomeMoneyInputForPair() throws Exception {
        Integer first = new TaxCalc(10)
                .netAmount(new TaxCalc.Pair<>(40, "GBP"), new TaxCalc.Pair<>(null, "GBP"))
                .first;
    }

    @Test(expected= IncomeValueIsNullException.class)
    public void canHandleNullInNetIncomeCurrencyInputForPair() throws Exception {
        Integer first = taxCalc
                .netAmount(new TaxCalc.Pair<>(40, "GBP"), new TaxCalc.Pair<>(50, null))
                .first;
    }

    @Test(expected= IncomeValueIsNullException.class)
    public void canHandleNullInNetIncomeInputForPairList() throws Exception {
        Integer first = taxCalc
                .netAmount(new TaxCalc.Pair<>(40, "GBP"),new TaxCalc.Pair<>(50, "GBP"), new TaxCalc.Pair<>(null, "GBP"))
                .first;
    }

    @Test(expected= IncomeValueIsNullException.class)
    public void canHandleNullInNetIncomeValInputForPairList() throws Exception {
        Integer first = taxCalc
                .netAmount(new TaxCalc.Pair<>(40, "GBP"),new TaxCalc.Pair<>(50, "GBP"), new TaxCalc.Pair<>(60, null))
                .first;
    }
    @Test(expected= IncomeValueIsNullException.class)
    public void canHandleEmptyInNetIncomeCurrencyInputForPairList() throws Exception {
        Integer first = taxCalc
                .netAmount(new TaxCalc.Pair<>(40, "GBP"),new TaxCalc.Pair<>(50, "GBP"), new TaxCalc.Pair<>(60, ""))
                .first;
    }
}