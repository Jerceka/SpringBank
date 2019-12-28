package jerceka.workhard.demo;

import java.util.Date;
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
	public void MakeAccount(Bank bank){
		repo.save(bank);
	}
	@Transactional
	public String checkGander(String name) {
		String gender = repo.findByName(name).get(0).getGender();
		if(gender.equals("Male")) {
			return "Mr.";
		}else {
			return "Ms.";
		}
	}
	public String greeting() {
		@SuppressWarnings("deprecation")
		int time = new Date().getHours();
		if(time < 12 && time > 5) {
			return "Good Morning";
		}else if(time >= 12 && time < 17) {
			return "Good Afternoon";
		}else {
			return "good Evening";
		}
	}
	@Transactional
	public int moneyWithname(String name) {
		int myMoney = repo.findByName(name).get(0).getMoney();
		return myMoney;
	}
	public void saveMoney(int m,String name) {
		Bank b = repo.findByName(name).get(0);
		b.setMoney(m);
		repo.save(b);	
	}
	@Transactional
	public void Delete(String name) {
		repo.deleteByName(name);
	}
}
