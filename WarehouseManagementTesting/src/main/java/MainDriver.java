import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wm.enums.ItemType;
import com.wm.enums.OStatus;
import com.wm.enums.PStatus;
import com.wm.models.Item;
import com.wm.models.ItemTypes;
import com.wm.models.Money;
import com.wm.models.Order;
import com.wm.models.OrderStatus;
import com.wm.models.PaymentStatus;
import com.wm.models.User;
import com.wm.repository.CustOrderRepo;
import com.wm.repository.CustomerRepo;
import com.wm.repository.ItemRepo;
import com.wm.repository.ItemTypesRepo;
import com.wm.repository.MoneyRepo;
import com.wm.repository.OrderRepo;
import com.wm.repository.OrderStatusRepo;
import com.wm.repository.PaymentStatusRepo;
import com.wm.repository.UserRepo;

public class MainDriver {

	public static ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	public static CustomerRepo cRepo = appContext.getBean("customerRepo",CustomerRepo.class);
	public static CustOrderRepo coRepo = appContext.getBean("custOrderRepo",CustOrderRepo.class);
	public static ItemRepo iRepo = appContext.getBean("itemRepo",ItemRepo.class);
	public static ItemTypesRepo itRepo = appContext.getBean("itemTypesRepo",ItemTypesRepo.class);
	public static MoneyRepo mRepo = appContext.getBean("moneyRepo",MoneyRepo.class);
	public static OrderRepo oRepo = appContext.getBean("orderRepo",OrderRepo.class);
	public static OrderStatusRepo osRepo = appContext.getBean("orderStatusRepo",OrderStatusRepo.class);
	public static PaymentStatusRepo psRepo = appContext.getBean("paymentStatusRepo",PaymentStatusRepo.class);
	public static UserRepo uRepo = appContext.getBean("userRepo",UserRepo.class);
	
	public static void main(String[] args) {
		Money mr = new Money(0,0,0);
		mRepo.insert(mr);
		
		ItemTypes it1 = new ItemTypes(0,ItemType.HOMEGOODS);
		itRepo.insert(it1);
		ItemTypes it2 = new ItemTypes(1,ItemType.CLEANINGSUPPLIES);
		itRepo.insert(it2);
		ItemTypes it3 = new ItemTypes(2,ItemType.COOKING);
		itRepo.insert(it3);
		ItemTypes it4 = new ItemTypes(3,ItemType.INDUSTRY);
		itRepo.insert(it4);
		ItemTypes it5 = new ItemTypes(4,ItemType.SPORTINGEQUIPMENT);
		itRepo.insert(it5);
		ItemTypes it6 = new ItemTypes(5,ItemType.OTHER);
		itRepo.insert(it6);
		
		OrderStatus os1 = new OrderStatus(0,OStatus.FUFILLED);
		osRepo.insert(os1);
		OrderStatus os2 = new OrderStatus(1,OStatus.UNRESOLVED);
		osRepo.insert(os2);
		
		PaymentStatus ps1 = new PaymentStatus(0,PStatus.PAID);
		psRepo.insert(ps1);
		PaymentStatus ps2 = new PaymentStatus(1,PStatus.UNPAID);
		psRepo.insert(ps2);
		
		Item i = new Item(it1,"Sponge",1.5,5);
		iRepo.insert(i);
		
		User u = new User("Test", "User", "Tester", "test@mail.com", "password");
		uRepo.insert(u);
		
		Order o = new Order(i,3,u,os2);
		oRepo.insert(o);
	}
	
	
}
