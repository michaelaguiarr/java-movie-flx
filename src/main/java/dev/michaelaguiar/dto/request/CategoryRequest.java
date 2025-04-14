package dev.michaelaguiar.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(@NotEmpty(message = "Nome da categoria é obrigatória.") String name) {
}
