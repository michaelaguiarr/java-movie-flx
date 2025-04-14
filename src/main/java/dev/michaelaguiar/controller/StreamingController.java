package dev.michaelaguiar.controller;

import dev.michaelaguiar.dto.request.StreamingRequest;
import dev.michaelaguiar.dto.response.StreamingResponse;
import dev.michaelaguiar.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;

    @PostMapping
    public ResponseEntity<StreamingResponse> saveStreaming(@RequestBody StreamingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(streamingService.saveStreaming(request));
    }

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> findAllStreamings() {
        return ResponseEntity.ok(streamingService.findAllStreamings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> findStreamingById(@PathVariable Long id) {
        StreamingResponse streamingResponse = streamingService.findStreamingById(id);
        if (streamingResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(streamingResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            streamingService.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
