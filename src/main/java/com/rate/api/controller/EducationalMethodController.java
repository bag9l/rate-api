package com.rate.api.controller;

import com.rate.api.dto.NewComment;
import com.rate.api.model.EducationalMethodType;
import com.rate.api.service.EducationalMethodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("educational-methods")
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

    @PostMapping("{id}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<Void> addCommentToEducationalMethod(@AuthenticationPrincipal UserDetails student,
                                                              @PathVariable("id") String id,
                                                              @Valid @RequestBody NewComment comment) {
        educationalMethodService.addCommentToEducationalMethod(id, comment, student.getUsername());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
