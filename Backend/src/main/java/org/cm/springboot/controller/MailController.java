package org.cm.springboot.controller;

import org.cm.springboot.controller.resquest.MailRequest;
import org.cm.springboot.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MailController {
    @Autowired
    MailService mailService;

    @PostMapping("/send-mail")
    public void sendMail(@RequestBody MailRequest request) {
        mailService.sendMail(request.getTo(), request.getSubject(), request.getContent());
    }
}
