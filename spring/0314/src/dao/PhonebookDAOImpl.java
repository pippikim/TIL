package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.Phone;

public class PhonebookDAOImpl implements PhonebookDAO {
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	@Override
	public List<Phone> selectList() {
		// TODO Auto-generated method stub
		return session.selectList("phonebook.selectList");
	}
}
