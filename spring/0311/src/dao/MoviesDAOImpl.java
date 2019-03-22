package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.Movie;

public class MoviesDAOImpl implements MoviesDAO {
	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<Movie> selectList() {
		// TODO Auto-generated method stub
		return session.selectList("movies.selectList");
	}
	
	@Override
	public int delete(int no) {
		// TODO Auto-generated method stub
		return session.delete("movies.delete", no);
	}
	
	@Override
	public List<Movie> search(String name) {
		// TODO Auto-generated method stub
		return session.selectList("movies.search", "%"+name+"%");
	}
	
	@Override
	public int insert(Movie info) {
		// TODO Auto-generated method stub
		return session.insert("movies.insert", info);
	}
}
