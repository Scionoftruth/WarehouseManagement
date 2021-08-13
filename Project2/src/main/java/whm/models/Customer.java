package whm.models;

import java.util.Random;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="customer")
public class Customer {

	@Id
	@Column(name="cust_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int custId;
	
	@Column(name="cust_firstname", nullable=false)
	private String firstName;
	
	@Column(name="cust_lastName", nullable=false)
	private String lastName;
	
	@Column(name="cust_address", nullable=false)
	private String address;
	
	@Column(name="cust_city", nullable=false)
	private String city;
	
	@Column(name="cust_state", nullable=false)
	private String state;
	
	@Column(name="cust_zipcode", nullable=false)
	private int zipCode;
	
	@Column(name="cust_email", nullable=false)
	private String email;

	public Customer(String firstName, String lastName, String address, String city, String state, int zipCode,
			String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.email = email;
	}
	
	
	
	
	
}
