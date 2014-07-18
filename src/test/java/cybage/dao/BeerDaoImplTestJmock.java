package cybage.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SessionFactory;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate3.HibernateTemplate;

import cybage.spring.model.Beer;
import cybage.spring.model.DomesticBeer;


@RunWith(JMock.class)
public class BeerDaoImplTestJmock {

	Mockery context ;
	private BeerDaoImpl classUnderTest;
	private SessionFactory sessionFactory;
	private HibernateTemplate hibernateTemplate;

	@Before
	public void setup(){
		context = new JUnit4Mockery();
		this.sessionFactory = context.mock(SessionFactory.class);
		this.hibernateTemplate = context.mock(HibernateTemplate.class);
		classUnderTest=new BeerDaoImpl(){
			protected HibernateTemplate createHibernateTemplate(SessionFactory sessionFactory) {
				return hibernateTemplate;
			}
		};
		
		classUnderTest.setSessionFactory(sessionFactory);
	}
	


	@Test
	public void testGetListBeer() {
		final String query = "From DomesticBeer";
		
		// expectations
        context.checking(new Expectations() {{
            oneOf (hibernateTemplate).find(query);
        }});
        
        //execution
		classUnderTest.getListBeer();

	}

	@Test
	public void testGetBeer() {
		
		
	final Long query = Long.parseLong("123");
		
		// expectations
        context.checking(new Expectations() {{
            oneOf (hibernateTemplate).get(DomesticBeer.class, query);
        }});
        
        //execution
		classUnderTest.getBeer(query);

	}

//	@Test
//	public void testSaveBeer() {
//		Beer beer = new DomesticBeer();
//		beer.setBrand("international");
//		beer.setPrice(new BigDecimal(1000.50));
//		classUnderTest.saveBeer(beer);
//	}

}
