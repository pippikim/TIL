package service;

import java.util.List;

import dao.GenresDAO;
import vo.Genre;

public class GenresServiceImpl implements GenresService{
	
	private GenresDAO genresDAO;
	
	public void setGenresDAO(GenresDAO genresDAO) {
		this.genresDAO = genresDAO;
	}
	
	@Override
	public List<Genre> getList() {
		// TODO Auto-generated method stub
		return genresDAO.selectList();
	}
	
	@Override
	public boolean register(String name) {
		// TODO Auto-generated method stub
		if(genresDAO.insert(name)>0) return true;
		
		return false;
	}

	 @Override
	public boolean update(Genre genre) {
		// TODO Auto-generated method stub
		 if(genresDAO.update(genre)>0) return true;
		return false;
	}
	 
	@Override
	public boolean remove(String name) {
		// TODO Auto-generated method stub
		if(genresDAO.delete(name)>0) return true;
		return false;
	} 
	 
}
