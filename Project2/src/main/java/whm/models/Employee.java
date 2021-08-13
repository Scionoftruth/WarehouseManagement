package whm.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import whm.enums.RoleEnum;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="employee")

public class Employee {
	
	@Id
	@Column(name="emp_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empId;

	@Column(name="emp_firstname", nullable=false)
	private String firstName;
	
	@Column(name="emp_lastname", nullable=false)
	private String lastName;
	
	@Column(name="emp_email", nullable=false)
	private String email;
	
	@Column(name="emp_password", nullable=false)
	private String password;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private RoleEnum role;
	
	
	
}
