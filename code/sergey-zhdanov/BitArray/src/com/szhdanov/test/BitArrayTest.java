package com.szhdanov.test;

import com.szhdanov.main.BitArray;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BitArrayTest {

    private boolean canTestResourceIntensive = false;

    @Test
    public void defaultConstructor() throws Exception {
        BitArray array = new BitArray();
        assertTrue("length must be 0", array.length() == 0);
    }

    @Test
    public void constructorInitCount() throws Exception {
        int count = 100;
        BitArray array = new BitArray(count, 0);
        assertTrue("wrong array size", array.length() == count);
    }

    @Test
    public void constructorInitZero() throws  Exception {
        int count = 100;
        BitArray array = new BitArray(count, 0);
        for (int i = 0; i < count; ++i) {
            assertTrue("0 check, wrong value for " + i, array.get(i) == 0);
        }
    }

    @Test
    public void constructorInitOne() throws  Exception {
        int count = 100;
        BitArray array = new BitArray(count, 1);
        for (int i = 0; i < count; ++i) {
            assertTrue("1 check, wrong value for " + i, array.get(i) == 1);
        }
    }

    @Test
    public void addNormal() throws Exception {
        BitArray array = new BitArray();
        int count = 100;
        for (int i = 0; i < count; ++i) {
            array.add(i % 2);
        }
        int resultBit = 0;
        for (int i = 0; i < count; ++i) {
            if (i % 2 != 0) {
                resultBit = 1;
            } else {
                resultBit = 0;
            }
            assertEquals(array.get(i), resultBit);
        }
    }

    @Test
    public void addNonZero() throws Exception {
        BitArray array = new BitArray();
        array.add(-1);
        assertTrue("test for set -1 value", array.get(array.length() - 1) == 1);
    }

    // too resource intensive. Uncomment if need.
    @Test
    public void maxRangeValue() throws Exception {
        if(canTestResourceIntensive) {
            BitArray array = new BitArray(Integer.MAX_VALUE, 0);
            assertTrue("check length", array.length() == Integer.MAX_VALUE);
        }

    }
    @Test
    public void maxRange() throws Exception {
        if(canTestResourceIntensive) {
            BitArray array = new BitArray(Integer.MAX_VALUE, 0);
            assertTrue("wrong value of MAX_VALUE index", array.get(Integer.MAX_VALUE - 1) == 0);
        }
    }

    @Test
    public  void constructorExceptionRangeBig() throws Exception {
        BitArray array = null;
        try {
            array = new BitArray(Integer.MAX_VALUE + 1, 0);
            fail("array not null");
        } catch (Exception ex) {
            //do nothing
        }
    }

    @Test
    public void constructorExceptionalRangeNeg() {
        BitArray array = null;
        try {
            array = new BitArray(-1, 0);
            fail("array not null");
        } catch (Exception ex) {
            //do nothing
        }
    }

   @Test
   public void setNormal() throws Exception {
       BitArray array = new BitArray(10, 0);
       int prevVal = array.get(5);
       array.set(5, 1);
       int curVal = array.get(5);
       assertTrue("not set", prevVal != curVal);
    }

   @Test
   public void setNormalValue() throws Exception {
       BitArray array = new BitArray(10, 0);
       array.set(9, 1);
       assertTrue("not set", array.get(9) == 1);
   }

    @Test
    public void setExceptionalRange() throws Exception {
        BitArray array = new BitArray(10, 0);
        try {
            array.set(10, 0);
            fail("set with -1");
        } catch (ArrayIndexOutOfBoundsException ex) {
            //do nothing
        }
    }

    @Test
    public void setExceptinalRangeNeg() throws Exception {
        BitArray array = new BitArray(10, 0);
        try {
            array.set(-1, 0);
            fail("set with -1");
        } catch (ArrayIndexOutOfBoundsException ex) {
            //do nothing
        }
    }

    @Test
    public void getExceptinalRange() throws Exception {
        BitArray array = new BitArray(10, 0);
        try {
            array.get(10);
            fail("get with -1");
        } catch (ArrayIndexOutOfBoundsException ex) {
            //do nothing
        }
    }

    @Test
    public void getExceptinalRangeNeg() throws Exception {
        BitArray array = new BitArray(10, 0);
        try {
            array.get(-1);
            fail("set with -1");
        } catch (ArrayIndexOutOfBoundsException ex) {
            //do nothing
        }
    }

    @Test
    public void fromStringNormalLen() throws Exception {
        String testString = "0001001010100101111000101000010010010100110101001010100101001011101001111000110";
        BitArray array = BitArray.fromString(testString);
        assertTrue("different length", array.length() == testString.length());
    }

    @Test
    public void fromStringNormal() throws Exception {
        String testString = "0001001010100101111000101000010010010100110101001010100101001011101001111000110";
        BitArray array = BitArray.fromString(testString);

        int valOfString = -1;
        for (int i = 0; i < testString.length(); ++i) {
            valOfString = Integer.valueOf(testString.substring(i, i + 1));
            assertTrue("wrong value for i: " + i + " given: " + array.get(i) + "; must: " + valOfString, valOfString == array.get(i));
        }
    }

    @Test
    public void fromStringExceptionalNull() throws Exception {
        String testString = null;
        BitArray bitArray = null;
        try {
            bitArray = BitArray.fromString(testString);
            fail("must be null");
        } catch (IllegalArgumentException ex) {

        }
    }

    @Test
    public void fromStringExceptionalNotDigit() throws Exception {
        String testString = null;
        BitArray bitArray = null;
        testString = "001001askdfjsl001";
        try {
            bitArray = BitArray.fromString(testString);
            fail("must be null");
        } catch (IllegalArgumentException ex) {

        }
    }

    @Test
    public void fromStringExceptionalNotOnlyOneAndZero() throws Exception {
        String testString = null;
        BitArray bitArray = null;
        testString = "00100195468309451";
        try {
            bitArray = BitArray.fromString(testString);
            fail("must be a null");
        } catch (IllegalArgumentException ex) {

        }
    }

    @Test
    public void toBitStringEmpty() throws Exception {
        BitArray array = new BitArray();
        assertTrue("check for empty", array.toBitString().equalsIgnoreCase(""));
    }

    @Test
    public void toBitString() throws Exception {
        String testString = "01100101010100101010111010100101010";
        BitArray array = BitArray.fromString(testString);
        assertTrue("check for testString", array.toBitString().equalsIgnoreCase(testString));
    }


    private void assertBySet(BitArray array, ArrayList<Integer> indexes,int expectedValue) throws Exception {
        for(int i = 0; i < indexes.size(); ++i) {
            int ind = indexes.get(i).intValue();
            assertTrue("check for ind: " + ind, array.get(ind) == expectedValue);
        }
    }

    @Test
    public void fromIntArrayLen() throws Exception {
        int[] testArray = {0, 1, 2};
        BitArray array = BitArray.fromArray(testArray);
        assertTrue("len check", array.length() == testArray.length * 32);
    }

    @Test
    public void fromIntArrayValsZeros() throws Exception {
        int[] testArray = {0, 1, 2};
        BitArray array = BitArray.fromArray(testArray);
        int count = testArray.length * 32;
        ArrayList<Integer> zeroIndexes = new ArrayList<Integer>(count);
        //generate zero indexes
        for(int i = 0; i < count; ++i) {
            zeroIndexes.add(new Integer(i));
        }
        zeroIndexes.remove(32);
        zeroIndexes.remove(65 - 1); // because we deleted 32 already

        assertBySet(array, zeroIndexes, 0);
    }

    @Test
    public void fromIntArrayValsOnes() throws Exception {
        int[] testArray = {0, 1, 2};
        BitArray array = BitArray.fromArray(testArray);
        //generate one indexes
        ArrayList<Integer> ones = new ArrayList<Integer>();
        ones.add(32);
        ones.add(65);
        assertBySet(array, ones, 1);
    }

    @Test
    public void fromIntArrayExceptional() throws Exception {
        BitArray array = null;
        try {
            array = BitArray.fromArray(null);
            fail("must be a null");
        } catch (IllegalArgumentException ex) {

        }
        try {
            int[] testArray = {};
            array = BitArray.fromArray(testArray);
            fail("must be a null");
        } catch (IllegalArgumentException ex) {

        }
    }

    @Test
    public void toIntArray() throws Exception {
        int[] testArray = {1, 11, 111};
        BitArray array = BitArray.fromArray(testArray);
        int[] outputArray = array.toIntArray();
        for (int i = 0; i < testArray.length; ++i) {
            assertTrue("check i: " + i, outputArray[i] == testArray[i]);
        }
    }

    @Test
    public void and() throws Exception {
        String testString1 = "000000000100000000";
        String testString2 = "111111111111111111";
        String resultStr = "000000000100000000";
        BitArray array1 = BitArray.fromString(testString1);
        BitArray array2 = BitArray.fromString(testString2);
        BitArray resultArray = array1.and(array2);
        assertTrue("result check", resultArray.toBitString().equalsIgnoreCase(resultStr));
    }

    @Test
    public void andUnchangedFirst() throws Exception {
        String testString1 = "000000000100000000";
        String testString2 = "111111111111111111";
        String resultStr = "000000000100000000";
        BitArray array1 = BitArray.fromString(testString1);
        BitArray array2 = BitArray.fromString(testString2);
        BitArray resultArray = array1.and(array2);
        assertTrue("unchanged 1 check", array1.toBitString().equalsIgnoreCase(testString1));
    }

    @Test
    public void andUnchangedSecond() throws Exception {
        String testString1 = "000000000100000000";
        String testString2 = "111111111111111111";
        String resultStr = "000000000100000000";
        BitArray array1 = BitArray.fromString(testString1);
        BitArray array2 = BitArray.fromString(testString2);
        BitArray resultArray = array1.and(array2);
        assertTrue("unchanged 2 check", array2.toBitString().equalsIgnoreCase(testString2));
    }

    @Test
    public void andExceptional() throws Exception {
        String testString1 = "0000000000000100000000";
        String testString2 = "111111111111111111";
        BitArray array1 = BitArray.fromString(testString1);
        BitArray array2 = BitArray.fromString(testString2);
        BitArray resultArray = null;
        try {
            resultArray = array1.and(array2);
            fail("must be a null");
        } catch (IllegalArgumentException ex) {

        }
    }

    @Test
    public void or() throws Exception {
        String testString1 = "000000010100000000";
        String testString2 = "111111111111111111";
        String resultStr = "111111111111111111";
        BitArray array1 = BitArray.fromString(testString1);
        BitArray array2 = BitArray.fromString(testString2);
        BitArray resultArray = array1.or(array2);
        assertTrue("result check", resultArray.toBitString().equalsIgnoreCase(resultStr));
    }

    @Test
    public void orUnchangedFirst() throws Exception {
        String testString1 = "000000010100000000";
        String testString2 = "111111111111111111";
        String resultStr = "111111111111111111";
        BitArray array1 = BitArray.fromString(testString1);
        BitArray array2 = BitArray.fromString(testString2);
        BitArray resultArray = array1.or(array2);
        assertTrue("unchanged 1 check", array1.toBitString().equalsIgnoreCase(testString1));
    }

    @Test
    public void orUnchangedSecond() throws Exception {
        String testString1 = "000000010100000000";
        String testString2 = "111111111111111111";
        String resultStr = "111111111111111111";
        BitArray array1 = BitArray.fromString(testString1);
        BitArray array2 = BitArray.fromString(testString2);
        BitArray resultArray = array1.or(array2);
        assertTrue("unchanged 2 check", array2.toBitString().equalsIgnoreCase(testString2));
    }

    @Test
    public void orExceptional() throws Exception {
        String testString1 = "0000000000000100000000";
        String testString2 = "111111111111111111";
        BitArray array1 = BitArray.fromString(testString1);
        BitArray array2 = BitArray.fromString(testString2);
        BitArray resultArray = null;
        try {
            resultArray = array1.or(array2);
            fail("must be a null");
        } catch (IllegalArgumentException ex) {

        }
    }

    @Test
    public void xor() throws Exception {
        String testString1 = "000000010100000000";
        String testString2 = "111111111111111111";
        String resultStr = "111111101011111111";
        BitArray array1 = BitArray.fromString(testString1);
        BitArray array2 = BitArray.fromString(testString2);
        BitArray resultArray = array1.xor(array2);
        assertTrue("result check", resultArray.toBitString().equalsIgnoreCase(resultStr));
    }

    @Test
    public void xorUnchangedFirst() throws Exception {
        String testString1 = "000000010100000000";
        String testString2 = "111111111111111111";
        String resultStr = "111111101011111111";
        BitArray array1 = BitArray.fromString(testString1);
        BitArray array2 = BitArray.fromString(testString2);
        BitArray resultArray = array1.xor(array2);
        assertTrue("unchanged 1 check", array1.toBitString().equalsIgnoreCase(testString1));
    }

    @Test
    public void xorUnchangedSecond() throws Exception {
        String testString1 = "000000010100000000";
        String testString2 = "111111111111111111";
        String resultStr = "111111101011111111";
        BitArray array1 = BitArray.fromString(testString1);
        BitArray array2 = BitArray.fromString(testString2);
        BitArray resultArray = array1.xor(array2);
        assertTrue("unchanged 2 check", array2.toBitString().equalsIgnoreCase(testString2));
    }

    @Test
    public void xorExceptional() throws Exception {
        String testString1 = "0000000000000100000000";
        String testString2 = "111111111111111111";
        BitArray array1 = BitArray.fromString(testString1);
        BitArray array2 = BitArray.fromString(testString2);
        BitArray resultArray = null;
        try {
            resultArray = array1.xor(array2);
            fail("must be a null");
        } catch (IllegalArgumentException ex) {

        }
    }

    @Test
    public void not() throws Exception {
        String testString = "000000000000111111111111";
        String resultString = "111111111111000000000000";
        BitArray array = BitArray.fromString(testString);
        BitArray resultArray = array.not();
        assertTrue("check result", resultArray.toBitString().equalsIgnoreCase(resultString));
    }

    @Test
    public void lshft() throws Exception {
        String testString = "1111111111111000000000000";
        String resultString1 = "0001111111111111000000000";

        BitArray array = BitArray.fromString(testString);
        BitArray resultArray = array.lshft(3);
        assertTrue("check result 1", resultArray.toBitString().equalsIgnoreCase(resultString1));
    }

    @Test
    public void lshftMaxShifht() throws Exception {
        String testString = "1111111111111000000000000";
        String resultString2 = "0000000000000000000000000";

        BitArray array = BitArray.fromString(testString);
        BitArray resultArray = array.lshft(3);
        resultArray = array.lshft(25);
        assertTrue("check result 2", resultArray.toBitString().equalsIgnoreCase(resultString2));
    }

    @Test
    public void lshftExceptional() throws Exception {
        BitArray array = new BitArray();
        BitArray resultArray = null;
        try {
            resultArray = array.lshft(3);
            fail("must be a null");
        } catch (Exception ex) {

        }
    }

    @Test
    public void lshftExceptionalNeg() throws Exception {
        BitArray resultArray = null;

        BitArray array = new BitArray(10, 0);
        try {
            resultArray = array.lshft(-3);
            fail("must be a null");
        } catch (Exception ex) {

        }
    }

    @Test
    public void rshft() throws Exception {
        String testString = "0000000000001111111111111";
        String resultString1 = "0001111111111111000000000";

        BitArray array = BitArray.fromString(testString);
        BitArray resultArray = array.rshft(9);
        assertTrue("check result 1", resultArray.toBitString().equalsIgnoreCase(resultString1));
    }

    @Test
    public void rshftMaxShift() throws Exception {
        String testString = "0000000000001111111111111";
        String resultString2 = "0000000000000000000000000";

        BitArray array = BitArray.fromString(testString);
        BitArray resultArray = array.rshft(25);
        assertTrue("check result 2", resultArray.toBitString().equalsIgnoreCase(resultString2));
    }

    @Test
    public void rshftExceptionalEmpty() throws Exception {
        BitArray array = new BitArray();
        BitArray resultArray = null;
        try {
            resultArray = array.rshft(3);
            fail("must be a null");
        } catch (Exception ex) {

        }
    }

    @Test
    public void rshftExceptionalNeg() throws Exception {
        BitArray array = new BitArray();
        BitArray resultArray = null;
        array = new BitArray(10, 0);
        try {
            resultArray = array.rshft(-3);
            fail("must be a null");
        } catch (Exception ex) {

        }
    }

    @Test
    public void append() throws Exception {
        String testString1 = "00000000000000";
        String testString2 = "11111111111111";
        String resultString = "0000000000000011111111111111";
        BitArray array1 = BitArray.fromString(testString1);
        BitArray array2 = BitArray.fromString(testString2);
        BitArray result = array1.append(array2);
        assertTrue("check result", result.toBitString().equalsIgnoreCase(resultString));
    }

    @Test
    public void subArrayCount() throws Exception {
        String testString = "00001110000000";
        BitArray array = BitArray.fromString(testString);
        BitArray resultArray = array.subArray(4, 3);
        assertTrue("length check", resultArray.length() == 3);
    }

    @Test
    public void subArrayValue() throws Exception {
        String testString = "00001110000000";
        BitArray array = BitArray.fromString(testString);
        BitArray resultArray = array.subArray(4, 3);
        for (int i = 0; i < resultArray.length(); ++i) {
            assertTrue("check i: " + i, resultArray.get(i) == 1);
        }
    }

    @Test
    public void subArrayExceptionalNeg() throws Exception {
        String testString = "00001110000000";
        BitArray array = BitArray.fromString(testString);
        BitArray resultArray = null;
        try {
            resultArray = array.subArray(-1, 3);
            fail("must be a null");
        } catch (Exception ex) {

        }
    }

    @Test
    public void subArrayExceptionalWrongInd() throws Exception {
        String testString = "00001110000000";
        BitArray array = BitArray.fromString(testString);
        BitArray resultArray = null;

        try {
            resultArray = array.subArray(1000, 3);
            fail("must be a null");
        } catch (Exception ex) {

        }
    }

    @Test
    public void subArrayExceptionalWrongLen() throws Exception {
        String testString = "00001110000000";
        BitArray array = BitArray.fromString(testString);
        BitArray resultArray = null;
        try {
            resultArray = array.subArray(1, 0);
            fail("must be a null");
        } catch (Exception ex) {

        }
    }

    @Test
    public void cloneMethod() throws Exception {
        BitArray array = new BitArray();
        array.add(1);
        array.add(1);
        BitArray cloneArray = array.clone();
        cloneArray.set(1, 0);
        assertTrue("check changed", cloneArray.get(1) != array.get(1));
    }
}
