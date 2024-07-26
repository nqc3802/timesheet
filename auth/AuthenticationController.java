package com.example.timesheet.auth;

import org.springframework.web.bind.annotation.RestController;

import com.example.timesheet.email.EmailService;
import com.example.timesheet.user.User;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User request) {
        // emailService.sendEmail(request.getEmail(), "Welcome to Timesheet", "You have successfully registered to Timesheet");
        // return ResponseEntity.ok(authenticationService.register(request));
        try {
            emailService.sendEmail(request.getEmail(), "Welcome to Timesheet", "You have successfully registered to Timesheet");
            AuthenticationResponse response = authenticationService.register(request);
            return ResponseEntity.ok(response);
        } catch (MailException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Gửi email thất bại, vui lòng thử lại sau");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Đã có lỗi xảy ra, vui lòng thử lại sau");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
