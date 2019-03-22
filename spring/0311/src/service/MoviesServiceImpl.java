package service;

import java.util.List;

import dao.MoviesDAO;
import vo.Movie;

public class MoviesServiceImpl implements MoviesService {
	private MoviesDAO moviesDAO;
	
	public void setMoviesDAO(MoviesDAO moviesDAO) {
		this.moviesDAO = moviesDAO;
	}
	@Override
	public List<Movie> getList() {
		// TODO Auto-generated method stub
		return moviesDAO.selectList();
	}
	
	@Override
	public boolean remove(int no) {
		// TODO Auto-generated method stub
		if(moviesDAO.delete(no)>0) return true;
		return false;
	}
	
	@Override
	public List<Movie> search(String name) {
		// TODO Auto-generated method stub
		return moviesDAO.search(name);
	}
	
	@Override
	public boolean input(Movie info) {
		// TODO Auto-generated method stub
		
		if(moviesDAO.insert(info)>0) return true;
		
		return false;
	}
}
