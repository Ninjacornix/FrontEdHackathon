package com.example.backend.controller;

import com.example.backend.domain.dto.RecordDto;
import com.example.backend.result.DataResult;
import com.example.backend.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://example.com", maxAge = 3600)
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/scan")
    public ResponseEntity<DataResult<RecordDto>> scan() {
        return recordService.scan().intoResponseEntity();
    }

    @GetMapping("/records")
    public ResponseEntity<DataResult<List<RecordDto>>> getRecords() {
        return recordService.getRecords().intoResponseEntity();
    }

    @GetMapping("/threats/count")
    public ResponseEntity<DataResult<?>> getThreatsCount() {
        return recordService.getThreatsCount().intoResponseEntity();
    }

}
