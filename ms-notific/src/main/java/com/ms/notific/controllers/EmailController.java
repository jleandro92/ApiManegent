package com.ms.notific.controllers;

import com.ms.notific.dtos.EmailDto;
import com.ms.notific.models.EmailModel;
import com.ms.notific.services.EmailService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class EmailController {

    Logger logger = LogManager.getLogger(EmailController.class);

    @Autowired
    EmailService emailService;

    @PostMapping("email")
    public ResponseEntity<Object> processEmail(@RequestBody @Valid EmailDto emailDto) {
        try {
            emailService.sendEmail(emailDto.convertToEmailModel());
            return ResponseEntity.ok("E-mail processed successfully in the notification API.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing email in the notification API.");
        }
    }
    @GetMapping("/email")
    public ResponseEntity<Page<EmailModel>> getAllEmails(@PageableDefault(page = 0, size = 5, sort = "emailId", direction = Sort.Direction.DESC) Pageable pageable){
        logger.trace("TRACE");
        logger.debug("DEBUG");
        logger.info("INFO");
        logger.warn("WARN");
        logger.error("ERROR");
        logger.fatal("FATAL");
        return new ResponseEntity<>(emailService.findAll(pageable), HttpStatus.OK);
    }
    
    @GetMapping("/email/{emailId}")
    public ResponseEntity<Object> getOneEmail(@PathVariable(value="emailId") UUID emailId){
        Optional<EmailModel> emailModelOptional = emailService.findById(emailId);
        if(!emailModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(emailModelOptional.get());
        }
    }
    
}
