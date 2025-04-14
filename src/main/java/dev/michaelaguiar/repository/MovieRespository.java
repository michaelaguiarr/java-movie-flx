package dev.michaelaguiar.repository;

import dev.michaelaguiar.entity.Category;
import dev.michaelaguiar.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRespository extends JpaRepository<Movie, Long> {

    List<Movie> findMovieByCategories(List<Category> categories);
}
