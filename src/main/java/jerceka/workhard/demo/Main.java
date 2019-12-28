package jerceka.workhard.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Main {
	@Autowired
	private BankService bs;
	private String name;
	@RequestMapping("/")
	public ModelAndView home(Model m) {
		List<Bank> a = bs.all();
		m.addAttribute("table",a);
		ModelAndView MV = new ModelAndView("index");
		return MV;
	}
	@RequestMapping("/login")
	public ModelAndView checkName(Model m,
							@RequestParam String loginName,
							@RequestParam String loginPassword) {
		if(bs.checkName(loginName)) {
			return check(m,loginName,loginPassword);
		}else {
			List<Bank> a = bs.all();
			m.addAttribute("table",a);
			ModelAndView MV = new ModelAndView("wrong");
			return MV;
		}
		
	}
	public ModelAndView check(Model m,
						String loginName,
						String loginPassword) {
		String passUser = bs.getBankUserPassword(loginName);
		if(bs.checkNameAndPassword(loginName, loginPassword)) {
			if(passUser.equals(loginPassword)) {
				m.addAttribute("test",loginName);
				m.addAttribute("gender",bs.checkGander(loginName));
				m.addAttribute("greeting",bs.greeting());
				m.addAttribute("Balance",bs.moneyWithname(loginName));
				this.name = loginName;
				ModelAndView MV = new ModelAndView("right");
				return MV;
			}else {
				List<Bank> a = bs.all();
				m.addAttribute("table",a);
				ModelAndView MV = new ModelAndView("wrong");
				return MV;
			}
		}else {
			List<Bank> a = bs.all();
			m.addAttribute("table",a);
			ModelAndView MV = new ModelAndView("wrong");
			return MV;
		}
	}
	@RequestMapping("/Make")
	public ModelAndView createNewAccount(Model m,Bank b,
						@RequestParam String name) {
		bs.MakeAccount(b);
		//should create account before call another things
		this.name = name;
		m.addAttribute("test",name);
		m.addAttribute("gender",bs.checkGander(name));
		m.addAttribute("greeting",bs.greeting());
		m.addAttribute("Balance",bs.moneyWithname(name));
		ModelAndView MV = new ModelAndView("right");
		return MV;
	}
	@RequestMapping("/create")
	public ModelAndView createPage() {
		ModelAndView MV = new ModelAndView("create");
		return MV;
	}
	@RequestMapping("/Add")
	public ModelAndView addMoney(Model m,@RequestParam int moneyAdded) {
		int myBalance = bs.moneyWithname(name);
		int total = myBalance + moneyAdded;
		bs.saveMoney(total, name);
		m.addAttribute("test",name);
		m.addAttribute("gender",bs.checkGander(name));
		m.addAttribute("greeting",bs.greeting());
		m.addAttribute("Balance",bs.moneyWithname(name));
		ModelAndView MV = new ModelAndView("right");
		return  MV;
	}
	@RequestMapping("/Delete")
	public ModelAndView delete(Model m) {
		bs.Delete(name);
		List<Bank> a = bs.all();
		m.addAttribute("table",a);
		ModelAndView MV = new ModelAndView("index");
		return MV;
	}
	@RequestMapping("/withdraw")
	public ModelAndView withdraw(Model m,@RequestParam int moneyWithdraw) {
		int myBalance = bs.moneyWithname(name);
		int total = myBalance - moneyWithdraw;
		if(total < 0 ) {
			m.addAttribute("test",name);
			m.addAttribute("gender",bs.checkGander(name));
			m.addAttribute("greeting",bs.greeting());
			m.addAttribute("Balance",bs.moneyWithname(name));
			ModelAndView MV = new ModelAndView("right");
			return  MV;
		}
		bs.saveMoney(total, name);
		m.addAttribute("test",name);
		m.addAttribute("gender",bs.checkGander(name));
		m.addAttribute("greeting",bs.greeting());
		m.addAttribute("Balance",bs.moneyWithname(name));
		ModelAndView MV = new ModelAndView("right");
		return  MV;
	}
	@RequestMapping("/transfer")
	public ModelAndView trasnfarMoney(Model m,@RequestParam String transferPerson,
								@RequestParam int transferMoney) {
		int myBalance = bs.moneyWithname(name);
		int total = myBalance - transferMoney;
		int myBalanceSecond = bs.moneyWithname(transferPerson);
		int totalScond = transferMoney + myBalanceSecond;
		if(bs.checkName(transferPerson)&& total >= 0) {
			bs.saveMoney(totalScond, transferPerson);
			bs.saveMoney(total, name);
			m.addAttribute("test",name);
			m.addAttribute("gender",bs.checkGander(name));
			m.addAttribute("greeting",bs.greeting());
			m.addAttribute("Balance",bs.moneyWithname(name));
			ModelAndView MV = new ModelAndView("right");
			return MV;
		}
		m.addAttribute("test",name);
		m.addAttribute("gender",bs.checkGander(name));
		m.addAttribute("greeting",bs.greeting());
		m.addAttribute("Balance",bs.moneyWithname(name));
		ModelAndView MV = new ModelAndView("right");
		return MV;
		
	}
}
