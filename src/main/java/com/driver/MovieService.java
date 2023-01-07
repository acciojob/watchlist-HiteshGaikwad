//package com.driver;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@org.springframework.stereotype.Service
//public class MovieService {
//
//  @Autowired
//  public MovieRepository movieRepository;
//
//  //Add movie to service layer
// public void addMovie(Movie movie){
//
//     movieRepository.addMovieToDb(movie);
//  }
//
//  //Add director to service layer
// public void addDirector(Director director){
//
//    movieRepository.addDirectorToDb(director);
//  }
//
//  //Add movies and director to service layer
// public String addMovieDirectorPair(String movieName, String directorName){
//
//    String result=movieRepository.addMovieDirectorToDb(movieName,directorName);
//
//    return result;
//  }
//
//  //get movie by name
//  public Movie getMovieByName(String movieName){
//
//    Movie movie=movieRepository.getMovieByName(movieName);
//
//    return movie;
//  }
//
//  //get director by name
//  public Director getDirectorByName(String directorName){
//
//    Director director=movieRepository.getDirectorByName(directorName);
//
//    return director;
//  }
//
//  //Get list of movies from director name
//   public List<String> getMoviesByDirectorName(String directorName){
//
//    List<String> list=movieRepository.getMoviesByDirectorNameFromDb(directorName);
//    return list;
//  }
//
//  //Get list of all movies added
//    public List<String> findAllMovies() {
//
//      List<String> list = movieRepository.getMoviesFromDb();
//      return list;
//    }
//
//    //Delete director by director name
// public String deleteDirectorByName(String name){
//
//    String result=movieRepository.deleteFromDirectoryDb(name);
//    return result;
//  }
//
//  //Delete all directors and all movies from records
//  public String deleteAllDirectors(){
//    String result=movieRepository.deleteFromRecords();
//
//    return result;
//  }
//
//}



package com.driver;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie){
        movieRepository.saveMovie(movie);
    }

    public void addDirector(Director director){
        movieRepository.saveDirector(director);
    }

    public void createMovieDirectorPair(String movie, String director){
        movieRepository.saveMovieDirectorPair(movie, director);
    }

    public Movie findMovie(String movieName){
        return movieRepository.findMovie(movieName);
    }

    public Director findDirector(String directorName){
        return movieRepository.findDirector(directorName);
    }

    public List<String> findMoviesFromDirector(String director){
        return movieRepository.findMoviesFromDirector(director);
    }

    public List<String> findAllMovies(){
        return movieRepository.findAllMovies();
    }

    public void deleteDirector(String director){
        movieRepository.deleteDirector(director);
    }

    public void deleteAllDirectors(){
        movieRepository.deleteAllDirector();
    }

    public String getDirectorByMovie(String movieName){
       String director= movieRepository.getDirectorByMovieName(movieName);
        return director;
    }
}