package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.Idol;

public class IdolsDAOImpl implements IdolsDAO{
	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<Idol> searchIdol(String name) {
		// TODO Auto-generated method stub
		return session.selectList("idols.searchIdol", "%"+name+"%");
	}
	
	@Override
	public int delete(int no) {
		// TODO Auto-generated method stub
		return session.delete("idols.delete", no);
	}
	
	@Override
	public int update(Idol idol) {
		// TODO Auto-generated method stub
		return session.update("idols.update", idol);
	}
	
	@Override
	public List<Idol> selectSearch(int groupNo) {
		// TODO Auto-generated method stub
		return session.selectList("idols.selectSearch",groupNo);
	}
	@Override
	public int deleteIdols(int groupNo) {
		// TODO Auto-generated method stub
		return session.delete("idols.deleteIdols", groupNo);
	}
	@Override
	public List<Idol> selectList() {
		// TODO Auto-generated method stub
		return session.selectList("idols.selectList");
	}
	
	@Override
	public Idol selectOne(int no) {
		// TODO Auto-generated method stub
		return session.selectOne("idols.selectOne", no);
	}
	@Override
	public int insert(Idol idol) {
		// TODO Auto-generated method stub
		return session.insert("idols.insert", idol);
	}
	
}