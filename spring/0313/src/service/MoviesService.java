package service;

import java.util.List;

import vo.Genre;
import vo.Movie;

public interface MoviesService {
	public List<Movie> getList();
	public boolean remove(int no);
	public List<Movie> search(String name);
	public List<Genre> getGenres();
	public boolean input(Movie movie);
	public Movie getMovie(int no);
}
