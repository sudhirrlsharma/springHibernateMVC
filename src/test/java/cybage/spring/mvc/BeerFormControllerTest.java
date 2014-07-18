package cybage.spring.mvc;

import junit.framework.Assert;

import org.jmock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import cybage.dao.BeerDao;
import cybage.spring.model.DomesticBeer;
import cybage.spring.validator.BeerValidator;

public class BeerFormControllerTest extends org.jmock.cglib.MockObjectTestCase {

	private BeerFormController classUnderTest;

	private Mock mockBeerDao;

	@Before
	public void setUp() {
		this.classUnderTest = new BeerFormController();
		this.mockBeerDao = this.mock(BeerDao.class);
		this.classUnderTest.setBeerDao((BeerDao) this.mockBeerDao.proxy());

	}


	@Test
	public void testSaveBeerWhenHasNoValidationError() throws Exception {
		String expectedViewName = "redirect:/myapp/listBeers";
		
		Mock mockValidator = this.mock(BeerValidator.class);
		Mock mockBeer = this.mock(DomesticBeer.class);
		Mock mockBindingResult = this.mock(BindingResult.class);
		Mock mockErrors = this.mock(Errors.class);
		this.classUnderTest.setValidator((BeerValidator) mockValidator.proxy());

		// dependency
		mockValidator.stubs().method("validate");
		mockBindingResult.stubs().method("hasErrors").will(this.returnValue(false));

		// expectaton
		this.mockBeerDao.expects(this.once()).method("saveBeer");
		DomesticBeer beer=(DomesticBeer) mockBeer.proxy();
		BindingResult br = (BindingResult) mockBindingResult.proxy();
		Errors error = (Errors) mockErrors.proxy();
		
		String actualView = classUnderTest.saveBeer(beer,br,error);
		Assert.assertEquals("View name is expected to be listBeer",expectedViewName, actualView);

	}

	
	@Test
	public void testSaveBeerWhenHasValidationError() throws Exception {
		String expectedViewName = "editBeer";
		Mock mockValidator = this.mock(BeerValidator.class);
		Mock mockBeer = this.mock(DomesticBeer.class);
		Mock mockBindingResult = this.mock(BindingResult.class);
		Mock mockErrors = this.mock(Errors.class);
		this.classUnderTest.setValidator((BeerValidator) mockValidator.proxy());

		// dependency
		mockValidator.stubs().method("validate");
		mockBindingResult.stubs().method("hasErrors").will(this.returnValue(true));

		// expectaton
		this.mockBeerDao.expects(this.never()).method("saveBeer");

		//actual mehtod call
		String actualView = classUnderTest.saveBeer((DomesticBeer) mockBeer.proxy(),(BindingResult) mockBindingResult.proxy(),(Errors) mockErrors.proxy());

		//assert
		Assert.assertEquals("View name is expected to be editBeer",expectedViewName, actualView);

	}
}
