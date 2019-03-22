package service;

import java.util.List;

import vo.Movie;

public interface MoviesService {
	public List<Movie> getList();
	public boolean remove(int no);
	public List<Movie> search(String name);
	public boolean input(Movie info);
}
