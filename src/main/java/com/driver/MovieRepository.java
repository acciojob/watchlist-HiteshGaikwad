//package com.driver;
//
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//
//@org.springframework.stereotype.Repository
//
//public class MovieRepository {
//
//  HashMap<String, Movie> movieDb;
//  HashMap<String, Director> directorDb;
//    HashMap<String, List<String>> movieDirectorDb;
//
//    public MovieRepository() {
//        this.movieDb = new HashMap<String, Movie>();
//        this.directorDb = new HashMap<String, Director>();
//        this.movieDirectorDb =new HashMap<String, List<String>>();
//    }
//
//    //Add Movie to DB
//    public void addMovieToDb(Movie movie){
//        String key= movie.getName();
//        movieDb.put(key,movie);
//    }
//
//    //Add director to Db
//    public void addDirectorToDb(Director director){
//
//        String key=director.getName();
//        directorDb.put(key,director);
//
//    }
//
//    //Add Movie and Director to DB
//    public String addMovieDirectorToDb(String movieName, String directorName){
//
//        if(movieDb.containsKey(movieName) && directorDb.containsKey(directorName)) {
//            List<String> list =new ArrayList<>();
//
//            if (movieDirectorDb.containsKey(directorName)) {
//
//                list = movieDirectorDb.get(directorName);
//
//                list.add(movieName);
//
//                movieDirectorDb.put(directorName, list);
//            }
//        }
//        return "Successfully Added.";
//    }
//
//    //Get movie by in from movieDb
//    public Movie getMovieByName(String movieName){
//
//        return movieDb.get(movieName);
//    }
//
//    //Get director by  name
//    public Director getDirectorByName(String directorName){
//
//        return directorDb.get(directorName);
//    }
//
//    //get list of the movies by director name from movie-director DB
//    public List<String> getMoviesByDirectorNameFromDb(String directorName){
//
//        List<String> list=new ArrayList<>();
//
//        if(movieDirectorDb.containsKey(directorName)) {
//
//            list = movieDirectorDb.get(directorName);
//        }
//            return list;
//    }
//
//    //Get list of all movies added to DB
//    public List<String> getMoviesFromDb(){
//        List<String> list=new ArrayList<>();
//        for(String names: movieDb.keySet()){
//            list.add(names);
//        }
//        return list;
//    }
//
//    //Delete director by director name from db
//    public String deleteFromDirectoryDb(String name){
//
////        List<String> list=new ArrayList<>();
////
////        if(movieDirectorDb.containsKey(name)) {
////
////            list=movieDirectorDb.get(name);
////
////            for(String movie: list){
////                if(movieDb.containsKey(movie)){
////                    movieDb.remove(movie);
////                }
////            }
////            movieDirectorDb.remove(name);
////        }
////        if(directorDb.containsKey(name)){
////            directorDb.remove(name);
////        }
//        if(movieDirectorDb.containsKey(name)){
//            movieDirectorDb.remove(name);
//        }
//        return "Successfully Deleted";
//    }
//
//    //Delete all directors and all movies which are in pair
//    public String deleteFromRecords(){
//
//        HashSet<String> moviesSet=new HashSet<>();
//
//        directorDb=new HashMap<>();
//
//        for(String director: movieDirectorDb.keySet()){
//
//            for(String name: movieDirectorDb.get(director)){
//                moviesSet.add(name);
//            }
//        }
//        for(String movie: moviesSet){
//            if(movieDb.containsKey(movie)){
//                movieDb.remove(movie);
//            }
//        }
//        movieDirectorDb=new HashMap<>();
//        return "Successfully Deleted";
//    }
//
//}


package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    //Pair is : DirectorName, List of Movie Names


    //Initialization is very important :

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public void saveMovie(Movie movie){
        movieMap.put(movie.getName(), movie);
    }

    public void saveDirector(Director director){
        directorMap.put(director.getName(), director);
    }

    public void saveMovieDirectorPair(String movie, String director){

        //1. Add the movie into Datbase ---> WRONG bcz I dont have te movie object

        if(movieMap.containsKey(movie)&&directorMap.containsKey(director)){

            List<String> currentMoviesByDirector = new ArrayList<>();

            if(directorMovieMapping.containsKey(director))
                currentMoviesByDirector = directorMovieMapping.get(director);

            currentMoviesByDirector.add(movie);

            directorMovieMapping.put(director,currentMoviesByDirector);

        }

    }

    public Movie findMovie(String movie){
        return movieMap.get(movie);
    }

    public Director findDirector(String director){
        return directorMap.get(director);
    }

    public List<String> findMoviesFromDirector(String director){
        List<String> moviesList = new ArrayList<String>();
        if(directorMovieMapping.containsKey(director)) moviesList = directorMovieMapping.get(director);
        return moviesList;
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director){

        List<String> movies = new ArrayList<String>();
        if(directorMovieMapping.containsKey(director)){
            //1. Find the movie names by director from the pair
            movies = directorMovieMapping.get(director);

            //2. Deleting all the movies from movieDb by using movieName
            for(String movie: movies){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }
            }

            //3. Deleteing the pair
            directorMovieMapping.remove(director);
        }

        //4. Delete the director from directorDb.
        if(directorMap.containsKey(director)){
            directorMap.remove(director);
        }
    }

    public void deleteAllDirector(){

        HashSet<String> moviesSet = new HashSet<String>();

        //Deleting the director's map
        directorMap = new HashMap<>();

        //Finding out all the movies by all the directors combined
        for(String director: directorMovieMapping.keySet()){

            //Iterating in the list of movies by a director.
            for(String movie: directorMovieMapping.get(director)){
                moviesSet.add(movie);
            }
        }

        //Deleting the movie from the movieDb.
        for(String movie: moviesSet){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }
        //clearing the pair.
        directorMovieMapping = new HashMap<>();
    }
}