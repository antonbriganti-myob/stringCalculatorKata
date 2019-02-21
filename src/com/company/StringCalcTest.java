package com.company;

import static org.junit.jupiter.api.Assertions.*;

class StringCalcTest {
    private StringCalc calc;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        calc = new StringCalc();
    }

    @org.junit.jupiter.api.Test
    void addReturns0WithEmptyString() {
        int result;
        // arrange

        //act
        try {
            result = calc.add("");
        }
        catch (Exception e){
            result = -1;
        }


       //assert
        assertEquals(result, 0);
    }

    @org.junit.jupiter.api.Test
    void addCalledWithNumberReturnsInputtedNumber() {
        int result;

        try {
            result = calc.add("1");
        }
        catch (Exception e){
            result = -1;
        }

        assertEquals(result, 1);
    }

    @org.junit.jupiter.api.Test
    void addPolymorphicCalledWithTwoNumbersReturnsSumOfNumbers() {
        int result = calc.add("1", "2");
        assertEquals(result, 3);
    }

    @org.junit.jupiter.api.Test
    void addCalledWithTwoNumbersReturnsSumOfNumbers() {
        int result;

        try {
            result = calc.add("1,2");
        }
        catch (Exception e){
            result = -1;
        }
        assertEquals(result, 3);
    }

    @org.junit.jupiter.api.Test
    void addCalledWithThreeNumbersReturnsSumOfAllNumbers() {
        int result;

        try {
            result = calc.add("1,2,3");
        }
        catch (Exception e){
            result = -1;
        }
        assertEquals(result, 6);
    }

    @org.junit.jupiter.api.Test
    void addCalledWithNewlineAsSeparators() {
        int result;

        try {
            result = calc.add("1\n2\n3");
        }
        catch (Exception e){
            result = -1;
        }
        assertEquals(result, 6);
    }

    @org.junit.jupiter.api.Test
    void addCalledWithNewDelimiters() {
        int result;

        try {
            result = calc.add("//;\n1;2");
        }
        catch (Exception e){
            result = -1;
        }
        assertEquals(result, 3);
    }

    @org.junit.jupiter.api.Test
    void addCalledWithNegativeNumbersThrowsException() {
        Exception thrown =
                assertThrows(Exception.class,
                        () -> calc.add("-1,2,-3"),
                        "Negatives not allowed");

        assertTrue(thrown.getMessage().contains("Negatives not allowed"));
    }

    @org.junit.jupiter.api.Test
    void addIgnoresAnIntEqualTo1000() {
        int result;

        try {
            result = calc.add("1000,1000,2");
        }
        catch (Exception e){
            result = -1;
        }
        assertEquals(result, 2);
    }

    @org.junit.jupiter.api.Test
    void addIgnoresAnIntGreaterThan1000() {
        int result;

        try {
            result = calc.add("1002,1003,2");
        }
        catch (Exception e){
            result = -1;
        }
        assertEquals(result, 2);
    }

    @org.junit.jupiter.api.Test
    void addAcceptsDelimitersOfAnySize() {
        int result;

        try {
            result = calc.add("//[;;;]\n1;;;2;;;3");
        }
        catch (Exception e){
            result = -1;
        }
        assertEquals(result, 6);
    }

    @org.junit.jupiter.api.Test
    void addAcceptsMultipleDelimiters() {
        int result;

        try {
            result = calc.add("//[;][#]\n1[#]2;;;3");
        }
        catch (Exception e){
            result = -1;
        }
        assertEquals(result, 6);
    }

    @org.junit.jupiter.api.Test
    void addAcceptsMultipleDelimitersWithVariousLengths() {
        int result;

        try {
            result = calc.add("//[;;;][#]\n1#2;;;3");
        }
        catch (Exception e){
            result = -1;
        }
        assertEquals(result, 6);
    }

    @org.junit.jupiter.api.Test
    void addAcceptsDelimitersWithNumbersInThem() {
        int result;

        try {
            result = calc.add("//[;1;][#]\n1#2;1;3");
        }
        catch (Exception e){
            result = -1;
        }
        assertEquals(result, 6);
    }

}