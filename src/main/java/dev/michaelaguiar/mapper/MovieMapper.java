package dev.michaelaguiar.mapper;

import dev.michaelaguiar.dto.request.MovieRequest;
import dev.michaelaguiar.dto.response.CategoryResponse;
import dev.michaelaguiar.dto.response.MovieResponse;
import dev.michaelaguiar.dto.response.StreamingResponse;
import dev.michaelaguiar.entity.Category;
import dev.michaelaguiar.entity.Movie;
import dev.michaelaguiar.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static MovieResponse toMovieResponse(Movie movie) {
        List<CategoryResponse> categories = movie.getCategories()
                .stream()
                .map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build())
                .toList();

        List<StreamingResponse> services = movie.getServices()
                .stream()
                .map(streamService -> StreamingResponse.builder()
                        .id(streamService.getId())
                        .name(streamService.getName())
                        .build())
                .toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getName())
                .description(movie.getDescription())
                .rating(movie.getRating())
                .releaseDate(movie.getReleaseDate())
                .categories(categories)
                .services(services)
                .build();
    }

    public static Movie toMovie(MovieRequest request) {
        List<Category> categories = request.categories()
                .stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> services = request.services()
                .stream()
                .map(streamServiceId -> Streaming.builder().id(streamServiceId).build())
                .toList();

        return Movie.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .rating(request.rating())
                .releaseDate(request.releaseDate())
                .categories(categories)
                .services(services)
                .build();
    }

}