package com.beta.replyservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationTest {
    @Test
    public void testReverse() {
        Operation op = Operation.REVERSE;
        assertEquals("ur9wzbk", op.perform("kbzw9ru"));
    }

    @Test
    public void testReverseTwice() {
        Operation op = Operation.REVERSE;
        String result = op.perform("kbzw9ru");
        assertEquals("kbzw9ru", op.perform(result));
    }

    @Test
    public void testMd5() {
        Operation op = Operation.MD5;
        assertEquals("0fafeaae780954464c1b29f765861fad", op.perform("kbzw9ru"));
    }

    @Test
    public void testMd5Twice() {
        Operation op = Operation.MD5;
        String result = op.perform("kbzw9ru");
        assertEquals("e8501e64cf0a9fa45e3c25aa9e77ffd5", op.perform(result));
    }

    @Test
    public void testGetMd5Rule() {
        Operation op = Operation.MD5;
        assertEquals("2", op.getRule());
    }

    @Test
    public void testGetReverseRule() {
        Operation op = Operation.REVERSE;
        assertEquals("1", op.getRule());
    }
}
