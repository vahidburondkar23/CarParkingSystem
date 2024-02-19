import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarParkingSystem {
	private List<String> slot = new ArrayList<String>(5);

	public CarParkingSystem() {
		super();
		for (int i = 0; i < 5; i++) {
			this.slot.add(null);
		}
	}

	public void addCar() {
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println("        [ADD CAR MENU]");
		System.out.println();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Car Details : ");
		String carNumber = new String();
		try {
			carNumber = sc.next();
		} catch (Exception e) {
			System.out.println();
			System.out.println("Invalid Car Number!");
			addCar();
		}
		if (carNumber == "-1") {
			Main.app(this);
		} else {
			addCarMessage(carNumber);
		}
	}

	public void addCarMessage(String carNumber) {
		for (int i = 0; i <= 4; i++) {
			if (this.slot.get(i) == null) {
				this.slot.set(i, carNumber);
				System.out.println();
				System.out.println(carNumber + " is succesfully added to Parking Slot : " + (i + 1));
				break;
			} else if (this.slot.get(i) != null && i == 4) {
				System.out.println();
				System.out.println("Parking Slot could not be assigned.");
				System.out.println("Parking is Full!");
				Main.app(this);
			} else {
				continue;
			}
		}
		Main.app(this);
	}

	public void removeCar() {
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println("        [REMOVE CAR MENU]");
		System.out.println();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Slot Number : ");

		int carNumber = 0;
		try {
			carNumber = sc.nextInt();
		} catch (Exception e) {
			System.out.println();
			System.out.println("Invalid Slot Number!");
			removeCar();
		}
		try {
			if (carNumber != -1) {
				removeCarMessage(carNumber - 1);
			} else {
				Main.app(this);
			}
		} catch (Exception e) {
			System.out.println();
			System.out.println("Invalid Slot Number!");
			removeCar();
		}
	}

	public void removeCarMessage(int i) throws Exception {
		if (this.slot.get(i) == null) {
			System.out.println();
			System.out.println("Car does not exist");
			Main.app(this);
		} else {
			this.slot.set(i, null);
			System.out.println(this.slot.get(i));
			System.out.println();
			System.out.println("Car sucessfully removed");
			Main.app(this);
		}
	}

	public void displaySlots() {
		System.out.println();
		for (int i = 0; i <= 4; i++) {
			System.out.println("Slot " + (i + 1) + " : " + this.slot.get(i));
		}
		Main.app(this);

	}
}
