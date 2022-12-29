package com.driver;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class MovieService {

  @Autowired
  public MovieRepository movieRepository;

  //Add movie to service layer
 public String addMovie(Movie movie){

    String result= movieRepository.addMovieToDb(movie);

    return result;
  }

  //Add director to service layer
 public String addDirector(Director director){

    String result=movieRepository.addDirectorToDb(director);

    return result;
  }

  //Add movies and director to service layer
 public String addMovieDirectorPair(String movieName, String directorName){

    String result=movieRepository.addMovieDirectorToDb(movieName,directorName);

    return result;
  }

  //get movie by name
  public Movie getMovieByName(String movieName){

    Movie movie=movieRepository.getMovieByName(movieName);

    return movie;
  }

  //get director by name
  public Director getDirectorByName(String directorName){

    Director director=movieRepository.getDirectorByName(directorName);

    return director;
  }

  //Get list of movies from director name
   public List<String> getMoviesByDirectorName(String directorName){

    List<String> list=movieRepository.getMoviesByDirectorNameFromDb(directorName);
    return list;
  }

  //Get list of all movies added
    public List<String> findAllMovies() {

      List<String> list = movieRepository.getMoviesFromDb();
      return list;
    }

    //Delete director by director name
 public String deleteDirectorByName(String name){

    String result=movieRepository.deleteFromDirectoryDb(name);
    return result;
  }

  //Delete all directors and all movies from records
  public String deleteAllDirectors(){
    String result=movieRepository.deleteFromRecords();

    return result;
  }

}
