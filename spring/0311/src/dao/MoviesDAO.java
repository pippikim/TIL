package dao;

import java.util.List;


import vo.Movie;

public interface MoviesDAO {
	public List<Movie> selectList();
	public int delete(int no);
	public List<Movie> search(String name);
	public int insert(Movie info);
}
