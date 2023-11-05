package com.beta.replyservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessMessage {
    private final String message;
    private final List<Operation> rules = new ArrayList<>();
    // Add more rules here
    private final Map<Character, Operation> operationMap = Map.of(
            '1', Operation.REVERSE,
            '2', Operation.MD5
    );
    public ProcessMessage(String rules, String message) {
        if (rules.length() > 10) {
            throw new IllegalArgumentException("Invalid input");
        }
        if (message.length() > 100) {
            throw new IllegalArgumentException("Invalid input");
        }
        buildRules(rules);
        this.message = message;
    }

    private void buildRules(String rules) {
        // check if the rules are valid
        for (int i = 0; i < rules.length(); i++) {
            Operation op = operationMap.get(rules.charAt(i));
            if (op == null) {
                throw new IllegalArgumentException("Invalid input");
            }
            this.rules.add(op);
        }
    }

    private String processMessage() {
        String processedMessage = message;
        for (Operation op : rules) {
            processedMessage = op.perform(processedMessage);
        }
        return processedMessage;
    }

    public ReplyMessage getData() {
        String processedMessage = processMessage();
        return new ReplyMessage(processedMessage);
    }
}
