package dev.michaelaguiar.service;

import dev.michaelaguiar.dto.request.StreamingRequest;
import dev.michaelaguiar.dto.response.StreamingResponse;
import dev.michaelaguiar.entity.Streaming;
import dev.michaelaguiar.mapper.StreamingMapper;
import dev.michaelaguiar.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository streamingRepository;

    @Transactional
    public StreamingResponse saveStreaming(StreamingRequest request) {
        Streaming streaming = StreamingMapper.toStreaming(request);
        streaming = streamingRepository.save(streaming);
        return StreamingMapper.toStreamingResponse(streaming);
    }

    @Transactional(readOnly = true)
    public List<StreamingResponse> findAllStreamings() {
        return streamingRepository.findAll().stream().map(StreamingMapper::toStreamingResponse).toList();
    }

    @Transactional(readOnly = true)
    public StreamingResponse findStreamingById(Long id) {
        return streamingRepository.findById(id)
                .map(StreamingMapper::toStreamingResponse)
                .orElse(null);
    }

    public Optional<Streaming> findById(Long id) {
        return streamingRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        streamingRepository.deleteById(id);
    }

}
