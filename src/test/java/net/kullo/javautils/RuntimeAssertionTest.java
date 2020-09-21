/*
 * Copyright 2015–2017 Kullo GmbH
 *
 * This source code is licensed under the 3-clause BSD license. See LICENSE.txt
 * in the root directory of this source tree for details.
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
