/*
 * Copyright 2014–2017 The javautils contributors (see NOTICE.txt)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
