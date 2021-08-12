package com.wm.repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.wm.models.PaymentStatus;

@Repository("paymentStatusRepo")
@Transactional
public class PaymentStatusRepo {
	
	private SessionFactory sesFact;
	
	@Autowired
	public PaymentStatusRepo(SessionFactory ses) {
		this.sesFact = ses;
	}
	public void insert (PaymentStatus payment){
		sesFact.getCurrentSession().save(payment);
	}
	
	public void update (PaymentStatus payment) {
		sesFact.getCurrentSession().update(payment);
	}
	
	public PaymentStatus selectPaymentStatusById (int id) {
		return sesFact.getCurrentSession().get(PaymentStatus.class, id);
	}

}