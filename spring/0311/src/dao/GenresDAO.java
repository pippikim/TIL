package dao;

import java.util.List;

import vo.Genre;

public interface GenresDAO {
	
	public List<Genre> selectList();	
	public int insert(String name);
	public int update(Genre genre);
	public int delete(String name);
}
