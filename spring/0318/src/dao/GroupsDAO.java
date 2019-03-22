package dao;

import java.util.List;

import vo.Group;

public interface GroupsDAO {
	public List<Group> selectList();
	public int insert(Group group);
	public int delete(int no);
	public int update(Group group);
	public Group select(int no);
}
