package service;

import java.util.List;

import vo.Group;

public interface GroupsService {
	public List<Group> getList();
	public boolean register(Group group);
	public boolean remove(int no);
	public boolean update(Group group);
	public Group getGroup(int no);
	
}
