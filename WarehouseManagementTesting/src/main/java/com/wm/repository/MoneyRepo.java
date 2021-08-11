package com.wm.repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.wm.models.Money;


@Repository("moneyRepo")
@Transactional
public class MoneyRepo {
	
	private SessionFactory sesFact;
	
	@Autowired
	public MoneyRepo(SessionFactory ses) {
		this.sesFact = ses;
	}
	public void insert (Money money){
		sesFact.getCurrentSession().save(money);
	}
	
	public void update (Money money) {
		sesFact.getCurrentSession().update(money);
	}
	
	public Money selectMoneyById (int id) {
		return sesFact.getCurrentSession().get(Money.class, id);
	}

}
