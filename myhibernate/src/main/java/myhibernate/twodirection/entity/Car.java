package myhibernate.twodirection.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="car")
public class Car {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="car_id")
	private long carId;
	
	@Column(name="brand")
	private String brand;
	
	@ManyToOne(cascade= { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name="owner_id")
	private Owner ownerId;
	
	public Car() {	
	}
	
	public Car(String brand) {
		super();
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Owner getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Owner ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", brand=" + brand + "]";
	}
	
}
