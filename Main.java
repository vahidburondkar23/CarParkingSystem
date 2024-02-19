import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("****CLASSICO CAR PARKING****");
		CarParkingSystem system = new CarParkingSystem();
		app(system);
	}

	public static void app(CarParkingSystem system) {
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println("        [MAIN MENU]");
		System.out.println("Operations : [1] Add Car");
		System.out.println("	   : [2] Remove Car");
		System.out.println("	   : [3] Get Slot of Car");
		System.out.println("	   : [4] Exit App");
		System.out.println();
		Scanner sc = new Scanner(System.in);
		System.out.print("Choose Operation : ");
		int selection = 0;
		try {
			selection = sc.nextInt();
		} catch (Exception e) {
			System.out.println();
			System.out.println("Invalid Values!");
			app(system);
		}
		
		switch (selection) {
		case 1:
			system.addCar();
		case 2:
			system.removeCar();
		case 3:
			system.displaySlots();
		case 4:
			System.out.println();
			System.out.println("-----------------------------");
			System.out.println("The App is Closed");
			return;
		default:
			System.out.println();
			System.out.println("Invalid Values!");
			app(system);
		}
	}
}
