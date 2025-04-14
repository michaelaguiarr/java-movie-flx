package dev.michaelaguiar.service;

import dev.michaelaguiar.dto.request.MovieRequest;
import dev.michaelaguiar.dto.response.MovieResponse;
import dev.michaelaguiar.entity.Category;
import dev.michaelaguiar.entity.Movie;
import dev.michaelaguiar.entity.Streaming;
import dev.michaelaguiar.mapper.MovieMapper;
import dev.michaelaguiar.repository.MovieRespository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRespository movieRespository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    @Transactional(readOnly = true)
    public List<MovieResponse> findAllMovies() {
        return movieRespository.findAll().stream().map(MovieMapper::toMovieResponse).toList();
    }

    @Transactional(readOnly = true)
    public MovieResponse findMovieById(Long id) {
        return movieRespository.findById(id).map(MovieMapper::toMovieResponse).orElse(null);
    }

    public List<MovieResponse> findByCategory(Long categoryId) {
        return movieRespository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()))
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
    }

    @Transactional
    public MovieResponse createMovie(@Valid MovieRequest request) {
        Movie movie = MovieMapper.toMovie(request);
        movie = movieRespository.save(movie);
        return MovieMapper.toMovieResponse(movie);
    }


    public MovieResponse updateMovie(@Valid MovieRequest request) {
        Movie updateMovie = MovieMapper.toMovie(request);
        Optional<Movie> optMovie = movieRespository.findById(updateMovie.getId());
        if (optMovie.isPresent()) {
            Movie movie = optMovie.get();
            movie.setName(updateMovie.getName());
            movie.setDescription(updateMovie.getDescription());
            movie.setRating(updateMovie.getRating());
            movie.setReleaseDate(updateMovie.getReleaseDate());

            movie.getCategories().clear();
            movie.getCategories().addAll(findCategories(updateMovie.getCategories()));

            movie.getServices().clear();
            movie.getServices().addAll(findServices(updateMovie.getServices()));

            movie = movieRespository.save(movie);
            return MovieMapper.toMovieResponse(movie);
        }
        return null;
    }

    public void deleteById(Long id) {
        movieRespository.deleteById(id);
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesList = new ArrayList<>();
        categories.forEach(category -> {
            Optional<Category> optCategory = categoryService.findByIdCategory(category.getId());
            optCategory.ifPresent(categoriesList::add);
        });
        return categoriesList;
    }

    private List<Streaming> findServices(List<Streaming> services) {
        List<Streaming> servicesList = new ArrayList<>();
        services.forEach(service -> {
            Optional<Streaming> optStreamService = streamingService.findById(service.getId());
            optStreamService.ifPresent(servicesList::add);
        });
        return servicesList;
    }
}
