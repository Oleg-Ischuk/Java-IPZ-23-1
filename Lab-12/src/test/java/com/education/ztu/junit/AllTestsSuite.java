package com.education.ztu.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ProductJUnitTest.class,
        ProductRulesTest.class,
        ProductParameterizedTest.class
})
public class AllTestsSuite {
}
