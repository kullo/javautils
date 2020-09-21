/*
 * Copyright 2015–2017 Kullo GmbH
 *
 * This source code is licensed under the 3-clause BSD license. See LICENSE.txt
 * in the root directory of this source tree for details.
 */
package net.kullo.javautils;

import java.util.Comparator;
import java.util.List;

public class Insertionsort {

    private Insertionsort() {}

    public static <G> void sort(List<G> list, Comparator<G> comparator) {
        for (int i = 1; i < list.size(); i++) {
            G currentValue = list.get(i);
            int j = i;
            while (j > 0 && comparator.compare(list.get(j - 1), currentValue) > 0) {
                list.set(j, list.get(j - 1));
                j--;
            }
            list.set(j, currentValue);
        }
    }
}
