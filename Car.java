import java.util.ArrayList;
import java.util.List;

public class Car {
	private static List<Car> list = new ArrayList<Car>(5);
	private String carNumber;
	
	public Car() {
		for (int i = 0; i <= 4; i++) {
			list.add(i, null);
		}
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Car(String carNumber) {
		this.carNumber = carNumber;
		for(int i=0;i<=4;i++) {
			if(list.get(i) == null) {
				list.set(i,this);
				System.out.println("Car Sucessfully Added");
				break;
			}else if(list.get(i) != null && i == 4) {
				System.out.println("Parking is full!");
			}else {
				continue;
			}
		}
	}
}
