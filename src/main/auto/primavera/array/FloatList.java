/*
 * Copyright (C) 2025 The PRIMAVERA Development Team
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
public class FloatList {

    /** The current size. */
    private volatile int size = 0;

    /** The actual data store. */
    private float[] array;

    /**
     * Create empty array.
     */
    public FloatList() {
        this(16);
    }

    /**
     * Create empty array with the specified array size.
     */
    public FloatList(int initialRawArraySize) {
        array = new float[initialRawArraySize];
    }

    /**
     * Add new value at last.
     * 
     * @param value A value to append.
     * @return Chainable API.
     */
    public FloatList add(float value) {
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
            float[] large = new float[Math.max(require, length + Math.min(length, 1024))];
            System.arraycopy(array, 0, large, 0, length);
            array = large;
        }
    }

    /**
     * Get the first element. If this array has no element, 0 will be returned.
     * 
     * @return A first element.
     */
    public float first() {
        return size == 0 ? 0f : array[0];
    }

    /**
     * Get the last element. If this array has no element, 0 will be returned.
     * 
     * @return A last element.
     */
    public float last() {
        return size == 0 ? 0f : array[size - 1];
    }

    /**
     * Get the value at the specified index. If out of bounded index is specified, 0 will be
     * returned.
     * 
     * @param index An index to get.
     * @return The indexed value.
     */
    public float get(int index) {
        return 0 <= index && index < size ? array[index] : 0f;
    }

    /**
     * Set the value at the specified index.
     * 
     * @param index An index to set.
     * @param value A value to set.
     * @return Chainable API.
     */
    public FloatList set(int index, float value) {
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
    public float increment(int index, float increment) {
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
    public float decrement(int index, float decrement) {
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
    public FloatList clear() {
        size = 0;
        return this;
    }

    /**
     * Expose low-level array.
     * 
     * @return Actual array.
     */
    public float[] asArray() {
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