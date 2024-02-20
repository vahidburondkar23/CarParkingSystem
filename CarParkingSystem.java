import java.util.Scanner;

public class CarParkingSystem {

	public static void addCar() {
		System.out.print("Enter Car Number: ");
		Scanner scanner = new Scanner(System.in);
		String carNumber = scanner.nextLine();
		Car car = new Car(carNumber);
		
	}

	
}
