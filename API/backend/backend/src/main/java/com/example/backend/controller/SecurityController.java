package com.example.backend.controller;

import com.example.backend.request.RegisterMemberRequest;
import com.example.backend.result.ActionResult;
import com.example.backend.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
public class SecurityController {


    private final SecurityService securityService;

    @PostMapping("/register/member")
    public ResponseEntity<ActionResult> registerMember(@RequestBody RegisterMemberRequest request) {
        return securityService.register(request).intoResponseEntity();
    }

    @GetMapping("/register/confirmEmail")
    @Transactional
    public ResponseEntity<ActionResult> confirmEmail(@PathParam("token") String token) {
        return securityService.confirmEmail(token).intoResponseEntity();
    }

    @GetMapping("/login")
    public ResponseEntity<ActionResult> login(@PathParam("code") String code) {
        return securityService.login(code).intoResponseEntity();
    }

    @PostMapping("/loginCode")
    public ResponseEntity<ActionResult> sendCode(@PathParam("email") String email) {
        return securityService.sendCode(email).intoResponseEntity();
    }
}
