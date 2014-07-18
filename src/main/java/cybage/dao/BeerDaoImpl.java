package cybage.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cybage.spring.model.Beer;
import cybage.spring.model.DomesticBeer;

@Repository
@Transactional(readOnly = true)
public class BeerDaoImpl implements BeerDao{
	HibernateTemplate hibernateTamplate;
	
//	/**
//	 * @param sessionFactory the sessionFactory to set
//	 */
//	@Autowired
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.hibernateTamplate = new HibernateTemplate(sessionFactory);
//	}

	
	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTamplate = createHibernateTemplate(sessionFactory);
	}

	protected HibernateTemplate createHibernateTemplate(SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}
	
	public Beer getBeer(Long id) {
		return (Beer)this.hibernateTamplate.get(DomesticBeer.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Beer> getListBeer() {
		return this.hibernateTamplate.find("From DomesticBeer");
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = {RuntimeException.class})
	public void saveBeer(Beer object) {
		this.hibernateTamplate.saveOrUpdate(object);
//		if(true){
//			throw new RuntimeException("Transaction should rollback");
//		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteBeer(Integer beerId) {
		this.hibernateTamplate.getSessionFactory().getCurrentSession().getTransaction();
		this.hibernateTamplate.delete(this.hibernateTamplate.load(DomesticBeer.class, beerId));
		// 
	}

}
