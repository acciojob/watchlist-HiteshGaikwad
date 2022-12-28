package com.driver;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@org.springframework.stereotype.Repository

public class MovieRepository {

  HashMap<String, Movie> movieDb=new HashMap<>();
  HashMap<String, Director> directorDb=new HashMap<>();
    HashMap<Movie, Director> movieDirectorDb=new HashMap<>();

   //Add Movie to DB
    public String addMovieToDb(Movie movie){
        String key= movie.getName();
        movieDb.put(key,movie);

        return "Successfully Added.";
    }

    //Add director to Db
    public String addDirectorToDb(Director director){

        String key=director.getName();
        directorDb.put(key,director);

        return "Successfully Added.";
    }

    //Add Movie and Director to DB
    public String addMovieDirectorToDb(String movieName, String directorName){

        Movie movie=movieDb.get(movieName);
        Director director= directorDb.get(directorName);
        movieDirectorDb.put(movie,director);

        return "Successfully Added.";
    }

    //Get movie by in from movieDb
    public Movie getMovieByName(String movieName){

        return movieDb.get(movieName);
    }

    //Get director by  name
    public Director getDirectorByName(String directorName){

        return directorDb.get(directorName);
    }

    //get list of the movies by director name from movie-director DB
    public List<String> getMoviesByDirectorName(String directorName){

        List<String> list=new ArrayList<String>();
        for(Movie movie: movieDirectorDb.keySet()){
           Director director= movieDirectorDb.get(movie);
            if(director.getName().equals(directorName)){
                list.add(movie.getName());
            }
        }
        return list;
    }

    //Get list of all movies added to DB
    public List<String> getMoviesFromDb(){
        List<String> list=new ArrayList<>();
        for(String names: movieDb.keySet()){
            list.add(names);
        }
        return list;
    }

    //Delete director by director name from db
    public String deleteFromDirectoryDb(String name){

        directorDb.remove(name);
        return "Successfully Deleted";
    }

    //Delete all directors and all movies which are in pair
    public String deleteFromRecords(){

        for(Movie movie: movieDirectorDb.keySet()){
            Director director=movieDirectorDb.get(movie);
            movieDb.remove(movie.getName());
            directorDb.remove(director.getName());
        }
        return "Successfully Deleted";
    }

}
