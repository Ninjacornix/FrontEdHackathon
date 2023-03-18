package com.example.backend.mapper;

import com.example.backend.domain.Record;
import com.example.backend.domain.Threat;
import com.example.backend.domain.dto.RecordDto;
import com.example.backend.domain.dto.ThreatDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    public ThreatDto threatToThreatDto(Threat threat) {
        return ThreatDto.builder()
                .id(threat.getId())
                .name(threat.getName())
                .potentialImpact(threat.getPotentialImpact())
                .source(threat.getSource())
                .severity(threat.getSeverity())
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
}
