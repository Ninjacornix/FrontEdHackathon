package com.example.backend.service;

import com.example.backend.domain.Alert;
import com.example.backend.repository.AlertRepository;
import com.example.backend.request.CreateAlertRequest;
import com.example.backend.result.ActionResult;
import com.example.backend.result.DataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;

    public ActionResult addAlert(CreateAlertRequest request) {
        Alert alert = Alert
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .data(request.getData())
                .field(request.getField())
                .build();
        alertRepository.save(alert);
        return new ActionResult(true, "Alert added successfully");
    }

    public ActionResult deleteAlert(Long id) {
        alertRepository.deleteById(id);
        return new ActionResult(true, "Alert deleted successfully");
    }

    public DataResult<List<Alert>> getAlerts() {
        return new DataResult<>(true, "Alerts found successfully", alertRepository.findAll());
    }
}
