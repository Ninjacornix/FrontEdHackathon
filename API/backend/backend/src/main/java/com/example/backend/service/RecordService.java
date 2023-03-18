package com.example.backend.service;

import com.example.backend.domain.Alert;
import com.example.backend.domain.Record;
import com.example.backend.domain.Threat;
import com.example.backend.domain.dto.RecordDto;
import com.example.backend.domain.dto.ThreatDto;
import com.example.backend.mapper.Mapper;
import com.example.backend.repository.AlertRepository;
import com.example.backend.repository.RecordRepository;
import com.example.backend.repository.ThreatRepository;
import com.example.backend.result.DataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final ThreatRepository threatRepository;
    private final RecordRepository recordRepository;
    private final Mapper mapper;
    private final EmailService emailService;
    private final AlertRepository alertRepository;

    public DataResult<RecordDto> scan() {
        int random = getRandomNumber(0, 10);
        List<Threat> threats = threatRepository.findRandom(random);
        Record record = Record.builder()
                .timestamp(new Date())
                .threats(threats)
                .build();

        recordRepository.save(record);

        checkAlerts(threats);

        RecordDto recordDto = mapper.recordToRecordDto(record);
        return new DataResult<>(true, "Successfully scanned", recordDto);
    }


    public DataResult<List<RecordDto>> getRecords() {
        List<Record> records = recordRepository.findAll();
        List<RecordDto> recordDtos = new ArrayList<>();
        for(Record r : records){
            recordDtos.add(mapper.recordToRecordDto(r));
        }
        return new DataResult<>(true, "Successfully retrieved records", recordDtos);
    }


    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private void checkAlerts(List<Threat> threats) {
        List<Alert> alerts = alertRepository.findAll();

        if(!alerts.isEmpty()) {
            for (Threat threat : threats) {
                for (Alert alert : alerts) {
                    if (threat.getName().equals(alert.getData()) || threat.getSource().equals(alert.getData())) {
                        emailService.sendAlert(alert);
                    }
                    else if (alert.getField().equals("potentialImpact")) {
                        if (threat.getPotentialImpact() > Float.parseFloat(alert.getData())) {
                            emailService.sendAlert(alert);
                        }
                    }
                }
            }
        }
    }
}

