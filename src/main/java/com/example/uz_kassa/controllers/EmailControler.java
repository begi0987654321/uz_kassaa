package com.example.uz_kassa.controllers;

import com.example.uz_kassa.payload.ReqSignUp;
import com.example.uz_kassa.services.impl.EmailServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailControler {

    private final EmailServiceImpl emailServiceimpl;

    public EmailControler(EmailServiceImpl emailServiceimpl) {
        this.emailServiceimpl = emailServiceimpl;
    }

    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@RequestBody ReqSignUp reqSignUp) {
        emailServiceimpl.sendEmail(reqSignUp.getEmail(), "test subject",
                "Test");
        return ResponseEntity.ok("Succes");
    }

}
