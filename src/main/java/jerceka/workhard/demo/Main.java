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
	@RequestMapping("/")
	public ModelAndView home(Model m) {
		List<Bank> a = bs.all();
		m.addAttribute("table",a);
		ModelAndView MV = new ModelAndView("index");
		return MV;
	}
	@RequestMapping("/login")
	public ModelAndView checkName(Model m,@RequestParam String loginName,
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
	public ModelAndView check(Model m,String loginName,String loginPassword) {
		String passUser = bs.getBankUserPassword(loginName);
		if(bs.checkNameAndPassword(loginName, loginPassword)) {
			if(passUser.equals(loginPassword)) {
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
	public void createNewAccount(Bank b) {
		bs.MakeAccount(b);
		System.out.println(b);
	}
	@RequestMapping("/create")
	public ModelAndView createPage() {
		ModelAndView MV = new ModelAndView("create");
		return MV;
	}
}
