package com.samuel.passwordgen.password_generator.controller;

import com.samuel.passwordgen.password_generator.dto.request.PasswordRequest;
import com.samuel.passwordgen.password_generator.dto.response.PasswordResponse;
import com.samuel.passwordgen.password_generator.service.PasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Password Generator", description = "API to generate secure passwords based on user-defined rules")
public class PasswordController {
     @Autowired
    private final PasswordService service;


    @PostMapping("/generate")
    @Operation(
            summary = "Generate a password",
            description = "Generates a random password based on length and character type preferences.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PasswordRequest.class),
                            examples = @ExampleObject(value = """
                {
                  "length": 12,
                  "useUppercase": true,
                  "useNumbers": true,
                  "useSymbols": false
                }
                """)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Password generated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PasswordResponse.class),
                                    examples = @ExampleObject(value = """
                    {
                      "password": "aB3kL9dfH2qW"
                    }
                    """)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input parameters",
                            content = @Content(
                                    mediaType = "text/plain"
                            )
                    )
            }
    )
    public ResponseEntity<?> generatePassword(@RequestBody PasswordRequest request) {
        try {
            String pwd = service.generatePassword(
                    request.getLength(),
                    request.isUseUppercase(),
                    request.isUseNumbers(),
                    request.isUseSymbols()
            );
            return ResponseEntity.ok(new PasswordResponse(pwd));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}