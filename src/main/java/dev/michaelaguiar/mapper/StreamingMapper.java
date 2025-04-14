package dev.michaelaguiar.mapper;

import dev.michaelaguiar.dto.request.StreamingRequest;
import dev.michaelaguiar.dto.response.StreamingResponse;
import dev.michaelaguiar.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest request) {
        return Streaming
                .builder()
                .name(request.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming entity) {
        return StreamingResponse
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
