package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

 //@RequestMapping("/movies")
public class MovieController {



    @Autowired
    public MovieService movieService;


    //Add movie
    @PostMapping("/movies/add_movie")
    public ResponseEntity<String> addMovie(@RequestBody() Movie movie){

        String response= movieService.addMovie(movie);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    //Add director
    @PostMapping("/movies/add_director")
    public ResponseEntity<String> addDirector(@RequestBody() Director director){

        String response= movieService.addDirector(director);

        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }

    //updating movie and director together
    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("name") String movieName, @RequestParam("name") String directorName){

        String response = movieService.addMovieDirectorPair(movieName,directorName);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //Get movie by name
    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName){

        Movie movie=movieService.getMovieByName(movieName);

        return new ResponseEntity<>(movie,HttpStatus.FOUND);
    }

    //Get director by name
    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName){

        Director director=movieService.getDirectorByName(directorName);

        return new ResponseEntity<>(director,HttpStatus.FOUND);
    }

    //Get list of movies from director name
    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String director){

        List<String> list= movieService.getMoviesByDirectorName(director);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    //get list of all movies added
    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){

        List<String> list= movieService.findAllMovies();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    //Delete director by director name
    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("name") String name){

        String response=movieService.deleteDirectorByName(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //Delete All directors and all movies from the records
    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){

        String response=movieService.deleteAllDirectors();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
