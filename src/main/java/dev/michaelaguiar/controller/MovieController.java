package dev.michaelaguiar.controller;


import dev.michaelaguiar.dto.request.MovieRequest;
import dev.michaelaguiar.dto.response.MovieResponse;
import dev.michaelaguiar.mapper.MovieMapper;
import dev.michaelaguiar.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAllMovies() {
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findMovieById(@PathVariable Long id) {
        MovieResponse movieResponse = movieService.findMovieById(id);
        if (movieResponse != null) {
            return ResponseEntity.ok(movieResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findMoviesByCategoryId(@RequestParam Long category) {
        return ResponseEntity.ok(movieService.findByCategory(category));
    }

    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody MovieRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.createMovie(request));
    }

    @PutMapping
    public ResponseEntity<MovieResponse> updateMovie(@Valid @RequestBody MovieRequest request) {
        MovieResponse movieResponse = movieService.updateMovie(request);
        if (movieResponse != null) {
            return ResponseEntity.ok(movieResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
