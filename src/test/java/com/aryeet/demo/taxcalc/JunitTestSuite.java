package com.aryeet.demo.taxcalc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        TaxCalcTest.class,
        TaxCalcIncomeNullTest.class
})

public class JunitTestSuite {
}  