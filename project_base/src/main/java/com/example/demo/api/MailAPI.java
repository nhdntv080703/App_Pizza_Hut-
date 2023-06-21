package com.example.demo.api;

import com.example.demo.email.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/mail")
public class MailAPI {

    @Autowired
    private MailService mailService;

    @PostMapping
    public ResponseEntity<?> sentMail(@RequestParam (required = false)String to,
                                      @RequestParam (required = false)String text){
        return ResponseEntity.ok(mailService.sendMail(to, text));
    }
}
