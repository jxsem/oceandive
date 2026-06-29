package com.oceandive.backend.oceandata;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oceandive.backend.oceandata.openmeteo.OpenMeteoDTO;
import com.oceandive.backend.oceandata.openmeteo.OpenMeteoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/ocean")
@RequiredArgsConstructor
public class OceandataController {

    private final OpenMeteoService openMeteoService;

    @GetMapping("/marine")
    public ResponseEntity<OpenMeteoDTO> getMarineConditions(
            @RequestParam double latitude,
            @RequestParam double longitude) {
            OpenMeteoDTO result = openMeteoService.getMarineConditions(latitude, longitude);
            return ResponseEntity.ok(result);
    }
}
