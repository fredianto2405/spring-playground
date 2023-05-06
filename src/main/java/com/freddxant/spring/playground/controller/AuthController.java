package com.freddxant.spring.playground.controller;

import com.freddxant.spring.playground.config.JwtTokenUtil;
import com.freddxant.spring.playground.model.dto.AuthReqDto;
import com.freddxant.spring.playground.model.dto.AuthResDto;
import com.freddxant.spring.playground.model.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Operation(summary = "Authenticate")
    @PostMapping("/auth/login")
    @CrossOrigin(value = "*")
    public ResponseEntity<?> login(@RequestBody @Valid AuthReqDto authReqDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authReqDto.getEmail(),
                            authReqDto.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(user);

            AuthResDto authResDto = new AuthResDto();
            authResDto.setEmail(user.getEmail());
            authResDto.setAccessToken(accessToken);

            return ResponseEntity.ok().body(authResDto);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
