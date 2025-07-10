package com.prorider.DTOs.email;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Email {
    @NotNull(message = "fromAddress is mandatory")
    private String fromAddress;
    @NotNull(message = "toAddress is mandatory")
    private String toAddress;
    @NotNull(message = "subject is mandatory")
    private String subject;
    @NotNull(message = "body is mandatory")
    private String body;
}
