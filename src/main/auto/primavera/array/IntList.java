/*
 * Copyright (C) 2023 The PRIMAVERA Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package primavera.array;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.processing.Generated;


/**
 * {@link ArrayList} like data structure for numeric primitive type.
 */
@Generated("SpecializedCodeGenerator")
public class IntList {

    /** The current size. */
    private volatile int size = 0;

    /** The actual data store. */
    private int[] array;

    /**
     * Create empty array.
     */
    public IntList() {
        this(16);
    }

    /**
     * Create empty array with the specified array size.
     */
    public IntList(int initialRawArraySize) {
        array = new int[initialRawArraySize];
    }

    /**
     * Add new value at last.
     * 
     * @param value A value to append.
     * @return Chainable API.
     */
    public IntList add(int value) {
        if (array.length <= size) {
            widenBaseArary(size + 1);
        }

        array[size++] = value;
        return this;
    }

    /**
     * Widen the base array's capacity.
     */
    private synchronized void widenBaseArary(int require) {
        int length = array.length;
        if (length <= require) {
            int[] large = new int[Math.max(require, length + Math.min(length, 1024))];
            System.arraycopy(array, 0, large, 0, length);
            array = large;
        }
    }

    /**
     * Get the first element. If this array has no element, 0 will be returned.
     * 
     * @return A first element.
     */
    public int first() {
        return size == 0 ? 0 : array[0];
    }

    /**
     * Get the last element. If this array has no element, 0 will be returned.
     * 
     * @return A last element.
     */
    public int last() {
        return size == 0 ? 0 : array[size - 1];
    }

    /**
     * Get the value at the specified index. If out of bounded index is specified, 0 will be
     * returned.
     * 
     * @param index An index to get.
     * @return The indexed value.
     */
    public int get(int index) {
        return 0 <= index && index < size ? array[index] : 0;
    }

    /**
     * Set the value at the specified index.
     * 
     * @param index An index to set.
     * @param value A value to set.
     * @return Chainable API.
     */
    public IntList set(int index, int value) {
        ensureSize(index);
        array[index] = value;
        return this;
    }

    /**
     * Increment the value at the specified index.
     * 
     * @param index An index to set.
     * @param increment A value to increment.
     * @return An updated value.
     */
    public int increment(int index, int increment) {
        ensureSize(index);
        return array[index] += increment;
    }

    /**
     * Decrement the value at the specified index.
     * 
     * @param index An index to set.
     * @param decrement A value to decrement.
     * @return An updated value.
     */
    public int decrement(int index, int decrement) {
        ensureSize(index);
        return array[index] -= decrement;
    }

    /**
     * Ensure size and base array' size.
     * 
     * @param index
     */
    private void ensureSize(int index) {
        if (size <= index) {
            size = index + 1;
            if (array.length <= index) {
                widenBaseArary(size);
            }
        } else if (index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    /**
     * Get the number of elemenets.
     * 
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Check whether this array is empty or not.
     * 
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Check whether this array is empty or not.
     * 
     * @return
     */
    public boolean isNotEmpty() {
        return size != 0;
    }

    /**
     * Clear all elements.
     * 
     * @return Chainable API.
     */
    public IntList clear() {
        size = 0;
        return this;
    }

    /**
     * Expose low-level array.
     * 
     * @return Actual array.
     */
    public int[] asArray() {
        return array;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "PrefixArray[Size: " + size + " Items: " + Arrays.toString(array) + "]";
    }
}