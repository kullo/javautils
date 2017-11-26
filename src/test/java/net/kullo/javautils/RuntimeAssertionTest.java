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

import junit.framework.TestCase;

public class RuntimeAssertionTest extends TestCase {
    public void testRequire() throws Exception {
        {
            boolean didThrow = false;

            try {
                RuntimeAssertion.require(2 == 2);
            } catch (Exception e) {
                didThrow = true;
            }

            assertEquals(false, didThrow);
        }

        {
            boolean didThrowSomething = false;
            boolean didThrowAssertionError = false;

            try {
                RuntimeAssertion.require(2 == 8);
            } catch (AssertionError e) {
                didThrowSomething = true;
                didThrowAssertionError = true;
            } catch (Throwable e) {
                didThrowSomething = true;
            }

            assertEquals(true, didThrowSomething);
            assertEquals(true, didThrowAssertionError);
        }
    }

    public void testRequireWithMessage() throws Exception {
        boolean didThrow = false;
        String assertionText = "";

        try {
            RuntimeAssertion.require(1 == 2, "one must be two");
        } catch (Error e) {
            didThrow = true;
            assertionText = e.getMessage();
        }

        assertEquals(true, didThrow);
        assertEquals("one must be two", assertionText);
    }

    public void testFail() throws Exception {
        {
            boolean didThrow = false;

            try {
                RuntimeAssertion.fail();
            } catch (Error e) {
                didThrow = true;
            }

            assertEquals(true, didThrow);
        }

        {
            boolean didThrowSomething = false;
            boolean didThrowAssertionError = false;

            try {
                RuntimeAssertion.fail();
            } catch (AssertionError e) {
                didThrowSomething = true;
                didThrowAssertionError = true;
            } catch (Throwable e) {
                didThrowSomething = true;
            }

            assertEquals(true, didThrowSomething);
            assertEquals(true, didThrowAssertionError);
        }
    }

    public void testFailWithMessage() throws Exception {
        boolean didThrow = false;
        String assertionText = "";

        try {
            RuntimeAssertion.fail("dead code reached");
        } catch (Error e) {
            didThrow = true;
            assertionText = e.getMessage();
        }

        assertEquals(true, didThrow);
        assertEquals("dead code reached", assertionText);
    }
}
