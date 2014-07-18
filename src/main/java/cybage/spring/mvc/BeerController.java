package cybage.spring.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cybage.dao.BeerDao;
import cybage.spring.model.Beer;

@Controller
@RequestMapping("/listBeers")
public class BeerController {

	protected final Log logger = LogFactory.getLog(this.getClass());

	private BeerDao beerDao;

	@RequestMapping(method = RequestMethod.GET)
	public String getBeers(Model model,HttpServletRequest request) {
		String viewName="listBeer";

		List<Beer> lst = this.beerDao.getListBeer();
		for(Beer b : lst){
			System.out.println(b.toString());
		}
		model.addAttribute("allBeer",lst);
		this.logger.info("returning view with " + viewName);
		return viewName;

	}

	@Autowired
	public void setBeerDao(final BeerDao beerDao) {
		this.beerDao = beerDao;
	}

	
	@RequestMapping(value="/exception")
    public String throwNullPointer() {
        throw new RuntimeException("This is my custom exception should be handled by Error page");
    }

}
