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
            boolean didThrow = false;
            boolean didThrowAssertionError = false;

            try {
                RuntimeAssertion.require(2 == 8);
            } catch (AssertionError e) {
                didThrow = true;
                didThrowAssertionError = true;
            } catch (Exception e) {
                didThrow = true;
            }

            assertEquals(true, didThrow);
            assertEquals(true, didThrowAssertionError);
        }
    }
}
