/*
 * Copyright 2014–2016 The javautils contributors (see NOTICE.txt)
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

public class RuntimeAssertion {
    private RuntimeAssertion() {}

    public static void require(boolean expression) {
        require(expression, "");
    }

    public static void require(boolean expression, final String message) {
        if (!expression) {
            throw new AssertionError(message);
        }
    }

    public static void fail() {
        fail("");
    }

    public static void fail(final String message) {
        throw new AssertionError(message);
    }
}
