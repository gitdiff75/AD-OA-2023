package com.beta.replyservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProcessMessageTest {
    @Test
    public void testCreateInstanceGivenInvalidOperation() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ProcessMessage("13", "kbzw9ru"));
        assertEquals("Invalid input", exception.getMessage());
    }
    @Test
    public void testProcessMessageGivenTwoDifferentOperations() {
        ProcessMessage pm = new ProcessMessage("12", "kbzw9ru");
        ReplyMessage reply = pm.getData();
        assertEquals("5a8973b3b1fafaeaadf10e195c6e1dd4", reply.getData());
    }
    @Test
    public void testProcessMessageGivenTwoReverseOperations() {
        ProcessMessage pm = new ProcessMessage("11", "kbzw9ru");
        ReplyMessage reply = pm.getData();
        assertEquals("kbzw9ru", reply.getData());
    }
}
