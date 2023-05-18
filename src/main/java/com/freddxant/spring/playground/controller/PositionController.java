package com.freddxant.spring.playground.controller;

import com.freddxant.spring.playground.model.dto.ResponseDto;
import com.freddxant.spring.playground.service.PositionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PositionController {

    @Autowired
    PositionService positionService;

    @Operation(summary = "Find All Position")
    @GetMapping(value = "/position/findAllPosition")
    @CrossOrigin(value = "*")
    public ResponseEntity<?> findAllPosition() {
        ResponseDto responseDto = positionService.findAllPosition();
        return new ResponseEntity<>(responseDto, HttpStatus.resolve(responseDto.getCode()));
    }

}
