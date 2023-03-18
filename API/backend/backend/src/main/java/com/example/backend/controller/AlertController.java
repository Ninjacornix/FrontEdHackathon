package com.example.backend.controller;

import com.example.backend.requests.CreateAlertRequest;
import com.example.backend.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/alert")
public class AlertController {

    private final AlertService alertService;


    @DeleteMapping
    public void deleteAlert(@RequestParam Long id) {
        alertService.deleteAlert(id);
    }

    @PostMapping
    public void addAlert(CreateAlertRequest request) {
        alertService.addAlert(request);
    }

}
