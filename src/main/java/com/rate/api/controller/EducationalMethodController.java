package com.rate.api.controller;

import com.rate.api.model.EducationalMethodType;
import com.rate.api.service.EducationalMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("educational-method")
@RestController
public class EducationalMethodController {

    private final EducationalMethodService educationalMethodService;


    @GetMapping("types")
    @PreAuthorize("hasAuthority('LECTURER')")
    public ResponseEntity<List<EducationalMethodType>> getEducationalMethodTypes() {
        return ResponseEntity.status(HttpStatus.OK).body(
                educationalMethodService.getEducationalMethodTypes()
        );
    }
}
