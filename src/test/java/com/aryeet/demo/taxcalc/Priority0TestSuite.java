package com.aryeet.demo.taxcalc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(Priorty0Tests.class)
@Suite.SuiteClasses({
        TaxCalcTest.class,
        TaxCalcIncomeNullTest.class
})
public class Priority0TestSuite {
}