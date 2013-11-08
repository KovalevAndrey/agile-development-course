package com.szhdanov.main;

import java.util.ArrayList;

public class BitArray {
    /**
     * Получение доступа к произвольному элементу по индексу
     * Создание битового массива из строки вида "0010001" произвольной длинны
     * Вывод в строку вида "01010"
     * Создание битового массива из массива целых (int) чисел
     * Вывод в массив целых чисел
     * Бинарные операции AND, OR, XOR двух массивов одинаковой длинны
     * Унарные операции NOT, LSHT (left shift), RSHT (right shift)
     * Возмжность конкатенации массивов
     * Получение подмассива из массива
     */
    private static final int NUM_OF_BIT = 32;
    private int size;
    private ArrayList<Integer> intValues = new ArrayList<Integer>();

    /**
     * Constructs new empty array
     */
    public BitArray() {
        size = 0;
    }

    /**
     * Constructs array with specified count and initialize every item with given value
     *
     * @param initialSize  - destination count
     * @param defaultValue - value for initialization
     */
    public BitArray(int initialSize, int defaultValue) {
        if (initialSize < 0) {
            throw new IllegalArgumentException("Initialize size cant be less 0");
        }
        initialSize--;
        int val = 0;
        if (defaultValue != 0) {
            val = (int) ~0l;
        }
        int intCount = initialSize / NUM_OF_BIT;
        int bitCount = initialSize % NUM_OF_BIT;
        if (initialSize >= (intCount + 1) * NUM_OF_BIT) {
            intCount++;
        }
        intValues = new ArrayList<Integer>(intCount + 1);
        for (int i = 0; i < intCount + 1; ++i) {
            intValues.add(new Integer(val));
        }
        size = initialSize + 1;
    }

    /**
     * Constructs array from binary string
     *
     * @param bitString - binary string, like "00100101"
     * @return new BitArray instance
     */
    public static BitArray fromString(String bitString) {
        if (bitString == null || bitString.length() == 0) {
            throw new IllegalArgumentException("Input string must be not null and must have non zero length");
        }
        BitArray result = new BitArray(bitString.length(), 0);
        int bitFromString = -1;
        for (int i = 0; i < bitString.length(); ++i) {
            bitFromString = Integer.valueOf(bitString.substring(i, i + 1));
            if (bitFromString > 1 || bitFromString < 0) {
                throw new IllegalArgumentException("Input string must contains 0 and 1 digits only");
            }
            result.set(i, bitFromString);
        }
        return result;
    }

    /**
     * Construct new BitArray by using given int array
     */
    public static BitArray fromArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Input array should be not null and should have non zero len");
        }
        BitArray result = new BitArray();
        result.intValues = new ArrayList<Integer>(array.length);
        for (int i = 0; i < array.length; ++i) {
            result.intValues.add(new Integer(array[i]));
        }
        result.size = array.length * NUM_OF_BIT;
        return result;
    }

    /**
     * Appends specified value to array
     *
     * @param value - value which will be appended
     */
    public void add(int value) {
        int intIndex = size / NUM_OF_BIT;
        int bitIndex = size % NUM_OF_BIT;
        if (size >= (intIndex + 1) * NUM_OF_BIT) {
            intIndex++;
        }
        if (intIndex >= intValues.size()) {
            intValues.add(new Integer(0));
        }
        size++;
        set(size - 1, value);
    }

    /**
     * Returns bit value by index
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("trying to get with index: " + index + " when size: " + size);
        }
        int intIndex = index / NUM_OF_BIT;
        int bitIndex = NUM_OF_BIT - 1 - index % NUM_OF_BIT;
        if (index >= (intIndex + 1) * NUM_OF_BIT) {
            intIndex++;
        }
        long mask = 1l << bitIndex;
        int result = 0;
        if ((intValues.get(intIndex).intValue() & mask) > 0) {
            result = 1;
        }
        return result;
    }

    /**
     * Set bit value for specified position
     *
     * @param index - bit's position which will be set
     * @param value - new value for bit
     */
    public void set(int index, int value) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("trying to set with index: " + index + " when size: " + size);
        }
        int intIndex = index / NUM_OF_BIT;
        int bitIndex = NUM_OF_BIT - 1 - index % NUM_OF_BIT;
        if (index >= (intIndex + 1) * NUM_OF_BIT) {
            intIndex++;
        }
        int result = 0;
        long mask = 1l << bitIndex;
        if (value == 0) {
            mask = ~mask;
            result = intValues.get(intIndex).intValue() & ((int) mask);
        } else {
            result = intValues.get(intIndex).intValue() | ((int) mask);
        }
        intValues.set(intIndex, result);
    }

    /**
     * Return binary representation of current instance
     */
    public String toBitString() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length(); ++i) {
            buffer.append(get(i));
        }
        return buffer.toString();
    }

    /**
     * Returns current instance boxed to int array
     */
    public int[] toIntArray() {
        int[] result = new int[intValues.size()];
        for (int i = 0; i < intValues.size(); ++i) {
            result[i] = intValues.get(i).intValue();
        }
        return result;
    }

    /**
     * Binary AND operation. Given arguments will not be changed.
     *
     * @return this & arg
     */
    public BitArray and(BitArray arg) {
        if (this.length() != arg.length()) {
            throw new IllegalArgumentException("Arguments have defferent len");
        }
        BitArray result = new BitArray(this.length(), 0);
        for (int i = 0; i < intValues.size(); ++i) {
            result.intValues.set(i, intValues.get(i).intValue() & arg.intValues.get(i).intValue());
        }
        return result;
    }

    /**
     * Binary OR operation. Given arguments will not be changed.
     *
     * @return this | arg
     */
    public BitArray or(BitArray arg) {
        if (this.length() != arg.length()) {
            throw new IllegalArgumentException("Arguments have different len");
        }
        BitArray result = new BitArray(this.length(), 0);
        for (int i = 0; i < intValues.size(); ++i) {
            result.intValues.set(i, intValues.get(i).intValue() | arg.intValues.get(i).intValue());
        }
        return result;
    }

    /**
     * Binary XOR operation. Given arguments will not be changed.
     *
     * @return this ^ arg
     */
    public BitArray xor(BitArray arg) {
        if (this.length() != arg.length()) {
            throw new IllegalArgumentException("Arguments have different len");
        }
        BitArray result = new BitArray(this.length(), 0);
        for (int i = 0; i < intValues.size(); ++i) {
            result.intValues.set(i, intValues.get(i).intValue() ^ arg.intValues.get(i).intValue());
        }
        return result;
    }

    /**
     * Unary NOT operation. Given argument will not be changed.
     *
     * @return ~this
     */
    public BitArray not() {
        BitArray result = new BitArray(this.length(), 0);
        for (int i = 0; i < intValues.size(); ++i) {
            result.intValues.set(i, ~intValues.get(i).intValue());
        }
        return result;
    }

    /**
     * Left shift. Current instance will not be changed
     *
     * @return this << shiftLenght
     */
    public BitArray lshft(int shiftLenght) {
        if (shiftLenght <= 0) {
            throw new IllegalArgumentException("Shift len must be >= 0");
        }
        String bitString = toBitString();
        StringBuffer buffer = new StringBuffer(bitString);
        for (int i = 0; i < shiftLenght; ++i) {
            buffer.append(0);
        }
        BitArray result = BitArray.fromString(buffer.substring(shiftLenght));
        return result;
    }

    /**
     * Right shift. Current instance will not be changed
     *
     * @return this >> shiftLenght
     */
    public BitArray rshft(int shiftLenght) {
        if (shiftLenght <= 0) {
            throw new IllegalArgumentException("Shift len must be >= 0");
        }
        String bitString = toBitString();
        StringBuffer buffer = new StringBuffer(bitString);
        for (int i = 0; i < shiftLenght; ++i) {
            buffer.insert(0, 0);
        }
        BitArray result = BitArray.fromString(buffer.substring(0, length()));
        return result;
    }

    /**
     * Concatenates array
     */
    public BitArray append(BitArray arrayToAppend) {
        BitArray result = new BitArray(length() + arrayToAppend.length(), 0);
        int j = 0;
        for (int i = 0; i < length(); ++i, ++j) {
            result.set(j, get(i));
        }
        for (int i = 0; i < arrayToAppend.length(); ++i, ++j) {
            result.set(j, arrayToAppend.get(i));
        }
        return result;
    }

    /**
     * Eject new array from current instance.
     */
    public BitArray subArray(int startIndex, int elementsCount) {
        if (elementsCount <= 0) {
            throw new IllegalArgumentException("Elements count must be > 0");
        }
        BitArray result = new BitArray(elementsCount, 0);
        for (int i = 0; i < elementsCount; ++i) {
            result.set(i, get(startIndex + i));
        }
        return result;
    }

    /**
     * Return count of bit in current instance.
     */
    public int length() {
        return size;
    }

    /**
     * Returns clone of current instance
     */
    @Override
    public BitArray clone() {
        return BitArray.fromArray(toIntArray());
    }
}
