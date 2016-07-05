package cybage.spring.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cybage.dao.BeerDao;
import cybage.spring.model.Beer;
import cybage.spring.model.DomesticBeer;
import cybage.spring.validator.BeerValidator;

@Controller
@RequestMapping("/editBeers")
public class BeerFormController {
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	private BeerDao beerDao;
	
	
	@Autowired
	public void setBeerDao(final BeerDao beerDao) {
		this.beerDao = beerDao;
	}

	
	private BeerValidator validator;

	/**
	 * @param validator the validator to set
	 */
	@Autowired
	protected void setValidator(BeerValidator validator) {
		this.validator = validator;
	}

	@RequestMapping(method = RequestMethod.GET, value="/{beerId}")
	public String getBeer(@PathVariable String beerId, Model model) {
		long id = Long.parseLong(beerId);
		final Beer beer = this.beerDao.getBeer(id);
		model.addAttribute("beer", beer);
		return "editBeer";
	}

	@RequestMapping(value="/new",method = RequestMethod.GET)
	public String newBeer(Model model) {
		final Beer beer = new DomesticBeer();
		model.addAttribute("beer", beer);
		return "editBeer";

	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), false));
		binder.setValidator(this.validator);
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String saveBeer(@ModelAttribute("beer") @Valid DomesticBeer beer, Errors errors) throws Exception {
		
		if (errors.getErrorCount()>0){
			return "editBeer";
		}
		this.beerDao.saveBeer(beer);
		return "redirect:/myapp/listBeers";
	}
}
