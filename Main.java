import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		new Car();
		System.out.print("Choose Operation : ");
		Scanner scanner = new Scanner(System.in);
		int operation = scanner.nextInt();
		
		switch (operation) {
		case 1:
			CarParkingSystem.addCar();
		}
	}
}
