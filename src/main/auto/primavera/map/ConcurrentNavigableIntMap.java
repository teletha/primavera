/*
 * Copyright (C) 2025 The PRIMAVERA Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package primavera.map;

import java.util.Comparator;
import java.util.NavigableMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;

import javax.annotation.processing.Generated;


/**
 * Sepcialized {@link ConcurrentMap} and {@link NavigableMap} interface for primitive key.
 */
@Generated("SpecializedCodeGenerator")
public interface ConcurrentNavigableIntMap<V>
        extends ConcurrentNavigableMap<Integer, V>, ConcurrentIntMap<V>, NavigableIntMap<V> {

    /**
     * {@inheritDoc}
     */
    @Override
    default ConcurrentNavigableIntMap<V> subMap(Integer fromKey, boolean fromInclusive, Integer toKey, boolean toInclusive) {
        return subMap((int) fromKey, fromInclusive, (int) toKey, toInclusive);
    }

    /**
     * Returns a view of the portion of this map whose keys range from {@code fromKey} to
     * {@code toKey}. If {@code fromKey} and {@code toKey} are equal, the returned map is empty
     * unless {@code fromInclusive} and {@code toInclusive} are both true. The returned map is
     * backed by this map, so changes in the returned map are reflected in this map, and vice-versa.
     * The returned map supports all optional map operations that this map supports.
     * <p>
     * The returned map will throw an {@code IllegalArgumentException} on an attempt to insert a key
     * outside of its range, or to construct a submap either of whose endpoints lie outside its
     * range.
     *
     * @param fromKey low endpoint of the keys in the returned map
     * @param fromInclusive {@code true} if the low endpoint is to be included in the returned view
     * @param toKey high endpoint of the keys in the returned map
     * @param toInclusive {@code true} if the high endpoint is to be included in the returned view
     * @return a view of the portion of this map whose keys range from {@code fromKey} to
     *         {@code toKey}
     * @throws ClassCastException if {@code fromKey} and {@code toKey} cannot be compared to one
     *             another using this map's comparator (or, if the map has no comparator, using
     *             natural ordering). Implementations may, but are not required to, throw this
     *             exception if {@code fromKey} or {@code toKey} cannot be compared to keys
     *             currently in the map.
     * @throws NullPointerException if {@code fromKey} or {@code toKey} is null and this map does
     *             not permit null keys
     * @throws IllegalArgumentException if {@code fromKey} is greater than {@code toKey}; or if this
     *             map itself has a restricted range, and {@code fromKey} or {@code toKey} lies
     *             outside the bounds of the range
     */
    @Override
    ConcurrentNavigableIntMap<V> subMap(int fromKey, boolean fromInclusive, int toKey, boolean toInclusive);

    /**
     * {@inheritDoc}
     */
    @Override
    default ConcurrentNavigableIntMap<V> headMap(Integer toKey, boolean inclusive) {
        return headMap((int) toKey, inclusive);
    }

    /**
     * Returns a view of the portion of this map whose keys are less than (or equal to, if
     * {@code inclusive} is true) {@code toKey}. The returned map is backed by this map, so changes
     * in the returned map are reflected in this map, and vice-versa. The returned map supports all
     * optional map operations that this map supports.
     * <p>
     * The returned map will throw an {@code IllegalArgumentException} on an attempt to insert a key
     * outside its range.
     *
     * @param toKey high endpoint of the keys in the returned map
     * @param inclusive {@code true} if the high endpoint is to be included in the returned view
     * @return a view of the portion of this map whose keys are less than (or equal to, if
     *         {@code inclusive} is true) {@code toKey}
     * @throws ClassCastException if {@code toKey} is not compatible with this map's comparator (or,
     *             if the map has no comparator, if {@code toKey} does not implement
     *             {@link Comparable}). Implementations may, but are not required to, throw this
     *             exception if {@code toKey} cannot be compared to keys currently in the map.
     * @throws NullPointerException if {@code toKey} is null and this map does not permit null keys
     * @throws IllegalArgumentException if this map itself has a restricted range, and {@code toKey}
     *             lies outside the bounds of the range
     */
    @Override
    ConcurrentNavigableIntMap<V> headMap(int toKey, boolean inclusive);

    /**
     * {@inheritDoc}
     */
    @Override
    default ConcurrentNavigableIntMap<V> tailMap(Integer fromKey, boolean inclusive) {
        return tailMap((int) fromKey, inclusive);
    }

    /**
     * Returns a view of the portion of this map whose keys are greater than (or equal to, if
     * {@code inclusive} is true) {@code fromKey}. The returned map is backed by this map, so
     * changes in the returned map are reflected in this map, and vice-versa. The returned map
     * supports all optional map operations that this map supports.
     * <p>
     * The returned map will throw an {@code IllegalArgumentException} on an attempt to insert a key
     * outside its range.
     *
     * @param fromKey low endpoint of the keys in the returned map
     * @param inclusive {@code true} if the low endpoint is to be included in the returned view
     * @return a view of the portion of this map whose keys are greater than (or equal to, if
     *         {@code inclusive} is true) {@code fromKey}
     * @throws ClassCastException if {@code fromKey} is not compatible with this map's comparator
     *             (or, if the map has no comparator, if {@code fromKey} does not implement
     *             {@link Comparable}). Implementations may, but are not required to, throw this
     *             exception if {@code fromKey} cannot be compared to keys currently in the map.
     * @throws NullPointerException if {@code fromKey} is null and this map does not permit null
     *             keys
     * @throws IllegalArgumentException if this map itself has a restricted range, and
     *             {@code fromKey} lies outside the bounds of the range
     */
    @Override
    ConcurrentNavigableIntMap<V> tailMap(int fromKey, boolean inclusive);

    /**
     * {@inheritDoc}
     */
    @Override
    default ConcurrentNavigableIntMap<V> subMap(Integer fromKey, Integer toKey) {
        return subMap((int) fromKey, (int) toKey);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Equivalent to {@code subMap(fromKey, true, toKey, false)}.
     *
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    ConcurrentNavigableIntMap<V> subMap(int fromKey, int toKey);

    /**
     * {@inheritDoc}
     */
    @Override
    default ConcurrentNavigableIntMap<V> headMap(Integer toKey) {
        return headMap((int) toKey);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Equivalent to {@code headMap(toKey, false)}.
     *
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    ConcurrentNavigableIntMap<V> headMap(int toKey);

    /**
     * {@inheritDoc}
     */
    @Override
    default ConcurrentNavigableIntMap<V> tailMap(Integer fromKey) {
        return tailMap((int) fromKey);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Equivalent to {@code tailMap(fromKey, true)}.
     *
     * @throws ClassCastException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    ConcurrentNavigableIntMap<V> tailMap(int fromKey);

    /**
     * Returns a reverse order view of the mappings contained in this map. The descending map is
     * backed by this map, so changes to the map are reflected in the descending map, and
     * vice-versa.
     * <p>
     * The returned map has an ordering equivalent to
     * {@link java.util.Collections#reverseOrder(Comparator)
     * Collections.reverseOrder}{@code (comparator())}. The expression
     * {@code m.descendingMap().descendingMap()} returns a view of {@code m} essentially equivalent
     * to {@code m}.
     *
     * @return a reverse order view of this map
     */
    @Override
    ConcurrentNavigableIntMap<V> descendingMap();
}