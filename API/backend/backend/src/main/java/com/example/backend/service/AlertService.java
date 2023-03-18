package com.example.backend.service;

import com.example.backend.domain.Alert;
import com.example.backend.repository.AlertRepository;
import com.example.backend.repository.MemberRepository;
import com.example.backend.requests.CreateAlertRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;
    private final MemberRepository memberRepository;

    public void addAlert(CreateAlertRequest request) {
        Alert alert = Alert
                .builder()
                .data(request.getData())
                .field(request.getField())
                .build();
        alertRepository.save(alert);
    }

    public void deleteAlert(Long id) {
        alertRepository.deleteById(id);
    }
}
