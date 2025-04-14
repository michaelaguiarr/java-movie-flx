package dev.michaelaguiar.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(@NotEmpty(message = "Email é obrigatório.")
                           String email,
                           @NotEmpty(message = "Password é obrigatória.")
                           String password) {
}