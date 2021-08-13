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

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="employee")

public class Order {

	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int orderId;
	
	@Column(name="order_quantity", nullable=false)
	private int orderQty;
	
	@Id
	@ManyToOne(cascade= CascadeType.ALL)
	private Item itemId;
	
}
