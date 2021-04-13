package myhibernate.twodirection.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "owner")
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "owner_id")
	private long ownerId;

	@Column(name = "full_name")
	private String fullName;

	@OneToMany(mappedBy = "ownerId", cascade = CascadeType.ALL)
	List<Car> cars;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void AddCar(Car car) {
		if (cars == null) {
			cars = new ArrayList<Car>();
		}
		
		car.setOwnerId(this);
		cars.add(car);
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "Owner [ownerId=" + ownerId + ", fullName=" + fullName + "]";
	}

}
