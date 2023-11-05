package com.beta.replyservice;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class ReplyController {

	@GetMapping("/reply")
	public ReplyMessage replying() {
		return new ReplyMessage("Message is empty");
	}

	@GetMapping("/reply/{message}")
	public ReplyMessage replying(@PathVariable String message) {
		return new ReplyMessage(message);
	}

	@GetMapping("/v2/reply/{rule}-{message}")
	public ResponseEntity<ReplyMessage> replyingV2(@PathVariable String rule, @PathVariable String message) {
		try {
			ProcessMessage processedMessage = new ProcessMessage(rule, message);
			return ResponseEntity.ok(processedMessage.getData());
		} catch (IllegalArgumentException e) {
			ReplyMessage msg = new ReplyMessage(e.getMessage());
			return ResponseEntity.status(400).body(msg);
		}
	}
}