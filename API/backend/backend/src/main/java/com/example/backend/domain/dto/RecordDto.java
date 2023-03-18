package com.example.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RecordDto {
    private Long id;
    private Date timestamp;
    private List<ThreatDto> threats;
    private Float potentialImpact;
}
