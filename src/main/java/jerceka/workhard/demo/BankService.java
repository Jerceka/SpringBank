package jerceka.workhard.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {
	@Autowired
	private Repo repo;
	public List<Bank> all(){
		return repo.findAll();
	}

}
