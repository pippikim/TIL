package service;

import java.util.List;

import vo.Genre;

public interface GenresService {
	public List<Genre> getList();
	public boolean register(String name);
	public boolean update(Genre genre);
	public boolean remove(String name);
}
