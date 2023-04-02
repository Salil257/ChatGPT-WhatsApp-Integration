package com.chatopen.chatopen.controller;

import com.chatopen.chatopen.models.Change;
import com.chatopen.chatopen.models.WhatsappCallbackResponse;
import com.chatopen.chatopen.service.CallbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin()
@RequestMapping("/verify")
@Slf4j
public class CallBackController {

    @Autowired
    private CallbackService callbackService;

    @PostMapping
    ResponseEntity<String> saveResponse(@RequestBody WhatsappCallbackResponse response) throws IOException, InterruptedException {

        log.info(response.toString());
        return ResponseEntity.ok(callbackService.callToAi(response));
    }

    @GetMapping
    ResponseEntity<Integer> verify(@RequestParam("hub.mode") String mode,@RequestParam("hub.challenge") Integer challenge,
                                   @RequestParam("hub.verify_token") String token  ){


        log.info("successfully verified");
        return ResponseEntity.ok(challenge);
    }

    @GetMapping("/test")
    ResponseEntity<String> testing1(@RequestParam String name) throws IOException {

        return ResponseEntity.ok(name);
    }

}
