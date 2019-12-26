package jerceka.workhard.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {
	@Autowired
	private Repo repo;
	public List<Bank> all(){
		return repo.findAll();
	}
	@Transactional
	public String getBankUserPassword(String name) {
		String BankUserPassword = repo.findByName(name).get(0).getPassword();
		return BankUserPassword;
	}
	@Transactional
	public boolean checkName(String name) {
		return repo.existsByName(name);
	}
	@Transactional
	public boolean checkNameAndPassword(String name,String password) {
		boolean checkName = repo.existsByName(name);
		boolean checkPassword = repo.existsBypassword(password);
		return checkName && checkPassword;
	}
}
