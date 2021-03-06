/*
 * Copyright 2015–2017 Kullo GmbH
 *
 * This source code is licensed under the 3-clause BSD license. See LICENSE.txt
 * in the root directory of this source tree for details.
 */
package net.kullo.javautils;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class InsertionsortTest extends TestCase {
    final Comparator<Integer> INT_COMPARATOR = new Comparator<Integer>() {
        @Override
        public int compare(Integer lhs, Integer rhs) {
            if (lhs < rhs) return -1;
            if (lhs == rhs) return 0;
            return 1;
        }
    };

    public void testSortEmpty() throws Exception {
        List<Integer> empty = new ArrayList<>();
        Insertionsort.sort(empty, INT_COMPARATOR);
        assertEquals(empty.size(), 0);
        assertEquals(empty, new ArrayList<Integer>());
    }

    public void testSortSingle() throws Exception {
        List<Integer> single = new ArrayList<>(Arrays.asList(1));
        Insertionsort.sort(single, INT_COMPARATOR);
        assertEquals(single.size(), 1);
        assertEquals(single.get(0), new Integer(1));
    }

    public void testSortSorted() throws Exception {
        List<Integer> single = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Insertionsort.sort(single, INT_COMPARATOR);
        assertEquals(single.size(), 5);
        assertEquals(single.get(0), new Integer(1));
        assertEquals(single.get(1), new Integer(2));
        assertEquals(single.get(2), new Integer(3));
        assertEquals(single.get(3), new Integer(4));
        assertEquals(single.get(4), new Integer(5));
    }

    public void testSortReverse() throws Exception {
        List<Integer> single = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
        Insertionsort.sort(single, INT_COMPARATOR);
        assertEquals(single.size(), 5);
        assertEquals(single.get(0), new Integer(1));
        assertEquals(single.get(1), new Integer(2));
        assertEquals(single.get(2), new Integer(3));
        assertEquals(single.get(3), new Integer(4));
        assertEquals(single.get(4), new Integer(5));
    }

    public void testSortRandom() throws Exception {
        List<Integer> single = new ArrayList<>(Arrays.asList(4, 2, 5, 1, 3));
        Insertionsort.sort(single, INT_COMPARATOR);
        assertEquals(single.size(), 5);
        assertEquals(single.get(0), new Integer(1));
        assertEquals(single.get(1), new Integer(2));
        assertEquals(single.get(2), new Integer(3));
        assertEquals(single.get(3), new Integer(4));
        assertEquals(single.get(4), new Integer(5));
    }


    public void testSortEquals() throws Exception {
        List<Integer> single = new ArrayList<>(Arrays.asList(4, 4, 4, 4, 4));
        Insertionsort.sort(single, INT_COMPARATOR);
        assertEquals(single.size(), 5);
        assertEquals(single.get(0), new Integer(4));
        assertEquals(single.get(1), new Integer(4));
        assertEquals(single.get(2), new Integer(4));
        assertEquals(single.get(3), new Integer(4));
        assertEquals(single.get(4), new Integer(4));
    }

    public void testSortLong() throws Exception {
        List<Integer> single = new ArrayList<>(Arrays.asList(4, 2, 5, 0, 12, 11, 7, 8, 10, 12));
        Insertionsort.sort(single, INT_COMPARATOR);
        assertEquals(single.size(), 10);
        assertEquals(single.get(0), new Integer(0));
        assertEquals(single.get(1), new Integer(2));
        assertEquals(single.get(2), new Integer(4));
        assertEquals(single.get(3), new Integer(5));
        assertEquals(single.get(4), new Integer(7));
        assertEquals(single.get(5), new Integer(8));
        assertEquals(single.get(6), new Integer(10));
        assertEquals(single.get(7), new Integer(11));
        assertEquals(single.get(8), new Integer(12));
        assertEquals(single.get(9), new Integer(12));
    }

    public void testSortNegativeShort() throws Exception {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 0, -1));
        Insertionsort.sort(list, INT_COMPARATOR);
        assertEquals(list.size(), 3);
        assertEquals(list.get(0), new Integer(-1));
        assertEquals(list.get(1), new Integer(0));
        assertEquals(list.get(2), new Integer(1));
    }

    public void testSortNegativeLong() throws Exception {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 0, -1, -9, -150, 150, 9));
        Insertionsort.sort(list, INT_COMPARATOR);
        assertEquals(list.size(), 7);
        assertEquals(list.get(0), new Integer(-150));
        assertEquals(list.get(1), new Integer(-9));
        assertEquals(list.get(2), new Integer(-1));
        assertEquals(list.get(3), new Integer(0));
        assertEquals(list.get(4), new Integer(1));
        assertEquals(list.get(5), new Integer(9));
        assertEquals(list.get(6), new Integer(150));
    }
}
