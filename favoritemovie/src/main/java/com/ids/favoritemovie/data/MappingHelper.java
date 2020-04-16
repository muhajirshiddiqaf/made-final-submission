package com.ids.favoritemovie.data;

import android.database.Cursor;

import com.ids.favoritemovie.model.Movie;
import com.ids.favoritemovie.model.TvShow;

import java.util.ArrayList;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 04,April,2020.
 * Email : infinitydsolution@gmail.com
 * Company : Infinity Digital Solution
 */
public class MappingHelper {
    public static ArrayList<Movie> mapCursorToArrayList(Cursor MoviesCursor) {
        ArrayList<Movie> MoviesList = new ArrayList<>();

        while (MoviesCursor.moveToNext()) {
            Double popularity = MoviesCursor.getDouble(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.popularity));
            Integer voteCount = MoviesCursor.getInt(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.vote_count));
            String posterPath = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.poster_path));
            Integer id = MoviesCursor.getInt(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.id));
            String backdropPath = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.backdrop_path));
            String originalLanguage = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.original_language));
            String originalTitle = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.original_title));
            String title = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.title));
            Double voteAverage = MoviesCursor.getDouble(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.vote_average));
            String overview = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.overview));
            String releaseDate = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.release_date));
            Boolean favorite = true;

            Movie movie = new Movie();
            movie.setPopularity(popularity);
            movie.setVoteCount(voteCount);
            movie.setPosterPath(posterPath);
            movie.setId(id);
            movie.setBackdropPath(backdropPath);
            movie.setOriginalLanguage(originalLanguage);
            movie.setOriginalTitle(originalTitle);
            movie.setTitle(title);
            movie.setVoteAverage(voteAverage);
            movie.setOverview(overview);
            movie.setReleaseDate(releaseDate);
            movie.setFavorite(favorite);
            MoviesList.add(movie);
        }

        return MoviesList;
    }

    public static Movie mapCursorToObject(Cursor MoviesCursor) {
        MoviesCursor.moveToFirst();
        Double popularity = MoviesCursor.getDouble(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.popularity));
        Integer voteCount = MoviesCursor.getInt(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.vote_count));
        String posterPath = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.poster_path));
        Integer id = MoviesCursor.getInt(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.id));
        String backdropPath = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.backdrop_path));
        String originalLanguage = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.original_language));
        String originalTitle = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.original_title));
        String title = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.title));
        Double voteAverage = MoviesCursor.getDouble(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.vote_average));
        String overview = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.overview));
        String releaseDate = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.release_date));
        Boolean favorite = true;

        Movie movie = new Movie();
        movie.setPopularity(popularity);
        movie.setVoteCount(voteCount);
        movie.setPosterPath(posterPath);
        movie.setId(id);
        movie.setBackdropPath(backdropPath);
        movie.setOriginalLanguage(originalLanguage);
        movie.setOriginalTitle(originalTitle);
        movie.setTitle(title);
        movie.setVoteAverage(voteAverage);
        movie.setOverview(overview);
        movie.setReleaseDate(releaseDate);
        movie.setFavorite(favorite);
        return movie;
    }

    public static ArrayList<TvShow> mapCursorTvToArrayList(Cursor TvCursor) {
        ArrayList<TvShow> tvShows = new ArrayList<>();

        while (TvCursor.moveToNext()) {
            String originalName = TvCursor.getString(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.original_name));
            String name = TvCursor.getString(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.name));
            Double popularity = TvCursor.getDouble(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.popularity));
            Integer voteCount = TvCursor.getInt(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.vote_count));
            String firstAirDate = TvCursor.getString(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.first_air_date));
            String backdropPath = TvCursor.getString(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.backdrop_path));
            String originalLanguage = TvCursor.getString(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.original_language));
            Integer id = TvCursor.getInt(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.id));
            Double voteAverage = TvCursor.getDouble(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.vote_average));
            String overview = TvCursor.getString(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.overview));
            String posterPath = TvCursor.getString(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.poster_path));
            Boolean favorite = true;

            TvShow tvShow = new TvShow();
            tvShow.setOriginalName(originalName);
            tvShow.setName(name);
            tvShow.setPopularity(popularity);
            tvShow.setVoteCount(voteCount);
            tvShow.setFirstAirDate(firstAirDate);
            tvShow.setBackdropPath(backdropPath);
            tvShow.setOriginalLanguage(originalLanguage);
            tvShow.setId(id);
            tvShow.setVoteAverage(voteAverage);
            tvShow.setOverview(overview);
            tvShow.setPosterPath(posterPath);
            tvShow.setFavorite(favorite);
            tvShows.add(tvShow);
        }

        return tvShows;
    }

    public static TvShow mapCursorTvToObject(Cursor TvCursor) {
        TvCursor.moveToFirst();
        String originalName = TvCursor.getString(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.original_name));
        String name = TvCursor.getString(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.name));
        Double popularity = TvCursor.getDouble(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.popularity));
        Integer voteCount = TvCursor.getInt(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.vote_count));
        String firstAirDate = TvCursor.getString(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.first_air_date));
        String backdropPath = TvCursor.getString(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.backdrop_path));
        String originalLanguage = TvCursor.getString(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.original_language));
        Integer id = TvCursor.getInt(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.id));
        Double voteAverage = TvCursor.getDouble(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.vote_average));
        String overview = TvCursor.getString(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.overview));
        String posterPath = TvCursor.getString(TvCursor.getColumnIndexOrThrow(DatabaseContract.TvShowColumns.poster_path));
        Boolean favorite = true;

        TvShow tvShow = new TvShow();
        tvShow.setOriginalName(originalName);
        tvShow.setName(name);
        tvShow.setPopularity(popularity);
        tvShow.setVoteCount(voteCount);
        tvShow.setFirstAirDate(firstAirDate);
        tvShow.setBackdropPath(backdropPath);
        tvShow.setOriginalLanguage(originalLanguage);
        tvShow.setId(id);
        tvShow.setVoteAverage(voteAverage);
        tvShow.setOverview(overview);
        tvShow.setPosterPath(posterPath);
        tvShow.setFavorite(favorite);
        return tvShow;
    }

}
