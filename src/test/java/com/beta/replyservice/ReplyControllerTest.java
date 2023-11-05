package com.beta.replyservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ReplyControllerTest {

    @Autowired
    private ReplyController replyController;

    @Test
    void getReply() {
        ReplyMessage replyMessage = replyController.replying("kbzw9ru");
        assertEquals("kbzw9ru", replyMessage.getData());
    }

    @Test
    void getEmptyReply() {
        ReplyMessage replyMessage = replyController.replying();
        assertEquals("Message is empty", replyMessage.getData());
    }

    @Test
    void getReplyV2WithRule1() throws NoSuchAlgorithmException {
        ResponseEntity<ReplyMessage> replyMessageResponseEntity = replyController.replyingV2("1", "kbzw9ru");
        ReplyMessage replyMessage = replyMessageResponseEntity.getBody();
        assertEquals("ur9wzbk", replyMessage.getData());
    }

    @Test
    void getReplyV2WithRule2() throws NoSuchAlgorithmException {
        ResponseEntity<ReplyMessage> replyMessageResponseEntity = replyController.replyingV2("2", "kbzw9ru");
        ReplyMessage replyMessage = replyMessageResponseEntity.getBody();
        assertEquals("0fafeaae780954464c1b29f765861fad", replyMessage.getData());
    }

    @Test
    void getReplyV2WithRule1And1() throws NoSuchAlgorithmException {
        ResponseEntity<ReplyMessage> replyMessageResponseEntity = replyController.replyingV2("11", "kbzw9ru");
        ReplyMessage replyMessage = replyMessageResponseEntity.getBody();
        assertEquals("kbzw9ru", replyMessage.getData());
    }

    @Test
    void getReplyV2WithRule1And2() throws NoSuchAlgorithmException {
        ResponseEntity<ReplyMessage> replyMessageResponseEntity = replyController.replyingV2("12", "kbzw9ru");
        ReplyMessage replyMessage = replyMessageResponseEntity.getBody();
        assertEquals("5a8973b3b1fafaeaadf10e195c6e1dd4", replyMessage.getData());
    }

    @Test
    void getReplyV2WithRule2And2() throws NoSuchAlgorithmException {
        ResponseEntity<ReplyMessage> replyMessageResponseEntity = replyController.replyingV2("22", "kbzw9ru");
        ReplyMessage replyMessage = replyMessageResponseEntity.getBody();
        assertEquals("e8501e64cf0a9fa45e3c25aa9e77ffd5", replyMessage.getData());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "13", "!", " ", "\t", "\n", "\u0001", "12121212121212121212"
    })
    void getReplyV2ShouldThrowErrorGivenInvalidRule(String rule) throws NoSuchAlgorithmException {
        ResponseEntity<ReplyMessage> replyMessageResponseEntity = replyController.replyingV2(rule, "kbzw9ru");
        ReplyMessage replyMessage = replyMessageResponseEntity.getBody();
        assertEquals("Invalid input", replyMessage.getData());
    }

    @Test
    void getReplyV2ShouldThrowErrorGivenInvalidMessageLength() throws NoSuchAlgorithmException {
        ResponseEntity<ReplyMessage> replyMessageResponseEntity = replyController.replyingV2("1", "k".repeat(101));
        ReplyMessage replyMessage = replyMessageResponseEntity.getBody();
        assertEquals("Invalid input", replyMessage.getData());
    }
}