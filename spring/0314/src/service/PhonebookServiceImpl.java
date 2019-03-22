package service;

import java.util.List;

import dao.PhonebookDAO;
import vo.Phone;

public class PhonebookServiceImpl implements PhonebookService {
	private PhonebookDAO phonebookDAO;
	
	public void setPhonebookDAO(PhonebookDAO phonebookDAO) {
		this.phonebookDAO = phonebookDAO;
	}
	@Override
	public List<Phone> getList() {
		// TODO Auto-generated method stub
		return phonebookDAO.selectList();
	}
}
