package jerceka.workhard.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
