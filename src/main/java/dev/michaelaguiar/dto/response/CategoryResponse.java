package dev.michaelaguiar.dto.response;

import dev.michaelaguiar.dto.request.CategoryRequest;
import dev.michaelaguiar.dto.request.StreamingRequest;
import dev.michaelaguiar.entity.Category;
import dev.michaelaguiar.entity.Streaming;
import lombok.Builder;

@Builder
public record CategoryResponse(Long id, String name) {
}
