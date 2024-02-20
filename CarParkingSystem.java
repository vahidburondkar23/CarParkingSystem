import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarParkingSystem {

	private static List<Object> list = new ArrayList<Object>(5);
	
	public static void operations() {
		System.out.print("Choose Operation : ");
		Scanner scanner = new Scanner(System.in);
		switch (scanner.nextInt()) {
		case 1:
			addCar();
		case 2:
			removeCar();
		case 3: 
			viewSlots();
		case 4:
			System.out.println("App is Closed");
			return;
		default:
			System.out.println("Invalid Input");
			operations();
		}
	}

	public static void initialize() {
		for (int i = 0; i <= 4; i++) {
			list.add(null);
		}
	}

	public static void addCar() {
		System.out.print("Enter Car Number: ");
		Scanner scanner = new Scanner(System.in);
		String carNumber = scanner.nextLine();

		Car car = new Car();
		car.setCarNumber(carNumber);

		for (int i = 0; i <= 4; i++) {
			if (list.get(i) == null) {
				list.set(i, car);
				System.out.println("Car Sucessfully Added");
				break;
			} else if (list.get(i) != null && i == 4) {
				System.out.println("Parking is full!");
				operations();
			} else {
				continue;
			}
		}
		operations();
	}
	
	private static void removeCar() {
		System.out.print("Enter Slot Number : ");
		Scanner scanner = new Scanner(System.in);
		int slotNumber = scanner.nextInt();
		
		if(slotNumber <= 4 && slotNumber >= 0) {
			if(list.get(slotNumber) == null) {
				System.out.println("Car does not exist");
				operations();
			}else {
				list.set(slotNumber, null);
				System.out.println("Car Sucessfully Removed");
				operations();
			}
		}else {
			System.out.println("Invalid Slot Number");
			removeCar();
		}
		
	}
	
	private static void viewSlots() {
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Slot " + (i+1) + " : " + list.get(i));
		}
		operations();
	}
}