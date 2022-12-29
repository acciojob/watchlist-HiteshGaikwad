package com.driver;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@org.springframework.stereotype.Repository

public class MovieRepository {

  HashMap<String, Movie> movieDb;
  HashMap<String, Director> directorDb;
    HashMap<String, List<String>> movieDirectorDb;

    public MovieRepository() {
        this.movieDb = new HashMap<String, Movie>();
        this.directorDb = new HashMap<String, Director>();
        this.movieDirectorDb =new HashMap<String, List<String>>();
    }

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

        if(movieDb.containsKey(movieName) && directorDb.containsKey(directorName)) {
            List<String> list =new ArrayList<>();

            if (movieDirectorDb.containsKey(directorName)) {

                list = movieDirectorDb.get(directorName);

                list.add(movieName);

                movieDirectorDb.put(directorName, list);
            }
        }
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
    public List<String> getMoviesByDirectorNameFromDb(String directorName){

        List<String> list=new ArrayList<>();

        if(movieDirectorDb.containsKey(directorName)) {

            list = movieDirectorDb.get(directorName);
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

        List<String> list=new ArrayList<>();

        if(movieDirectorDb.containsKey(name)) {

            list=movieDirectorDb.get(name);

            for(String movie: list){
                if(movieDb.containsKey(movie)){
                    movieDb.remove(movie);
                }
            }
            movieDirectorDb.remove(name);
        }
        if(directorDb.containsKey(name)){
            directorDb.remove(name);
        }
        return "Successfully Deleted";
    }

    //Delete all directors and all movies which are in pair
    public String deleteFromRecords(){

        HashSet<String> moviesSet=new HashSet<>();

        directorDb=new HashMap<>();

        for(String director: movieDirectorDb.keySet()){

            for(String name: movieDirectorDb.get(director)){
                moviesSet.add(name);
            }
        }
        for(String movie: moviesSet){
            if(movieDb.containsKey(movie)){
                movieDb.remove(movie);
            }
        }
        movieDirectorDb=new HashMap<>();
        return "Successfully Deleted";
    }

}
