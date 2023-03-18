package com.example.backend.controller;

import com.example.backend.result.DataResult;
import com.example.backend.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://example.com", maxAge = 3600)
public class RecordController {

    private final RecordService recordService;
//
//    @GetMapping("/scan")
//    public DataResult<?> scan() {
//        return recordService.scan();
//    }
}
