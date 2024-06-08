package com.example.exception;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationException extends RuntimeException{
    private String message;
}
