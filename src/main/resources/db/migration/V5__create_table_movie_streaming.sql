CREATE TABLE movie_streaming (
                                      movie_id INTEGER,
                                      service_id INTEGER,
                                      CONSTRAINT fk_movie_streaming_movie FOREIGN KEY(movie_id) REFERENCES movie(id),
                                      CONSTRAINT fk_movie_streaming_stream_service FOREIGN KEY(service_id) REFERENCES streaming(id)
);