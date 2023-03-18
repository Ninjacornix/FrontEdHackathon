package com.example.backend.mapper;

import com.example.backend.domain.Record;
import com.example.backend.domain.Threat;
import com.example.backend.domain.dto.RecordDto;
import com.example.backend.domain.dto.ThreatDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Mapper {

    public ThreatDto threatToThreatDto(Threat threat) {
        return ThreatDto.builder()
                .id(threat.getId())
                .name(threat.getName())
                .potentialImpact(round(threat.getPotentialImpact(),2))
                .source(threat.getSource())
                .severity(threat.getSeverity())
                .deviceType(threat.getDeviceType())
                .build();
    }

    public List<ThreatDto> threatsToThreatDtos(List<Threat> threats) {
        return threats.stream().map(this::threatToThreatDto).toList();
    }

    public RecordDto recordToRecordDto(Record record) {
        return RecordDto.builder()
                .id(record.getId())
                .timestamp(record.getTimestamp())
                .threats(threatsToThreatDtos(record.getThreats()))
                .build();
    }

    private static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
