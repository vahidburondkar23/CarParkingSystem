import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CarParkingSystem {

	private static List<List<Object>> list = new LinkedList<List<Object>>();
	static int[] totalduration = new int[3];
	static String start = "12:00";
	static String end = "18:00";

	public static void operations() throws Exception {
		System.out.println("-> MAIN MENU ");
		System.out.println("[1] Add Car");
		System.out.println("[2] Remove Car");
		System.out.println("[3] View Slot Schedule");
		System.out.println("[4] Exit App");
		System.out.println();
		System.out.println("Choose Operation : ");

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
		for (int i = 0; i <= 2; i++) {
			list.add(new ArrayList<Object>());
			totalduration[i] = 360;
		}

	}

	public static void addCar() throws Exception {
		System.out.println();
		System.out.println("-> ADD CAR ");
		System.out.println("! Enter Car Licence Number in CAPS");
		System.out.println("! Time format is HH:MM (24 hours clock)");
		System.out.println("! Type -1 in any field to return to Main Menu");
		System.out.println();
		System.out.print("Enter Car Number: ");
		Scanner scanner = new Scanner(System.in);
		String carNumber = scanner.nextLine();
		System.out.print("Enter In-Time (HH:MM): ");
		String inTime = scanner.nextLine();
		System.out.print("Enter Out-Time (HH:MM): ");
		String outTime = scanner.nextLine();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date in = sdf.parse(inTime);
		Date out = sdf.parse(outTime);
		Date startTime = sdf.parse(start);
		Date endTime = sdf.parse(end);

		Car car = new Car();
		car.setCarNumber(carNumber);
		car.setInTime(inTime);
		car.setOutTime(outTime);
		car.setSlotDuration((int) (out.getTime() - in.getTime()) / 60000);

		for (int i = 0; i < list.size(); i++) {
			if (startTime.getTime() > in.getTime() || endTime.getTime() < out.getTime()) {
				slotNotAvailabale();
				operations();
			}

			if (car.getSlotDuration() > totalduration[i] && i < list.size()) {
				continue;
			} else if (car.getSlotDuration() > totalduration[i] && i == list.size()) {
				slotNotAvailabale();
				operations();
			} else {
				if (list.get(i).size() == 0) {
					list.get(i).add(car);
					totalduration[i] -= car.getSlotDuration();
					carAdded();
					operations();
				} else if (list.get(i).size() > 0 && i != list.size()) {
					if (list.get(i).size() == 1) {
						Date firstCarInTime = sdf.parse(((Car) list.get(i).get(0)).getInTime());
						Date firstCarOutTime = sdf.parse(((Car) list.get(i).get(0)).getOutTime());
						if (firstCarInTime.getTime() > out.getTime()) {
							list.get(i).add(0, car);
							totalduration[i] -= car.getSlotDuration();
							carAdded();
							operations();
						} else if (firstCarOutTime.getTime() < in.getTime()) {
							list.get(i).add(car);
							totalduration[i] -= car.getSlotDuration();
							carAdded();
							operations();
						}
					}else {
						for (int j = 0; j < list.get(i).size(); j++) {
							if(j == 0) {
								Date firstCarInTime = sdf.parse(((Car) list.get(i).get(0)).getInTime());
								Date firstCarOutTime = sdf.parse(((Car) list.get(i).get(0)).getOutTime());
								if (firstCarInTime.getTime() > out.getTime()) {
									list.get(i).add(0, car);
									totalduration[i] -= car.getSlotDuration();
									carAdded();
									operations();
								} else if (firstCarOutTime.getTime() < in.getTime()) {
									list.get(i).add(car);
									totalduration[i] -= car.getSlotDuration();
									carAdded();
									operations();
								}
							}else if(j == list.get(i).size()-1) {
								Date firstCarInTime = sdf.parse(((Car) list.get(i).get(j)).getInTime());
								Date firstCarOutTime = sdf.parse(((Car) list.get(i).get(j)).getOutTime());
								if (firstCarOutTime.getTime() < in.getTime()) {
									list.get(i).add(car);
									totalduration[i] -= car.getSlotDuration();
									carAdded();
									operations();
								}
							}else {
								//Date firstCarInTime = sdf.parse(((Car) list.get(i).get(j-1)).getInTime());
								Date firstCarOutTime = sdf.parse(((Car) list.get(i).get(j-1)).getOutTime());
								Date secondCarInTime = sdf.parse(((Car) list.get(i).get(j)).getInTime());
								//Date secondCarOutTime = sdf.parse(((Car) list.get(i).get(j)).getOutTime());
								if(in.getTime() > firstCarOutTime.getTime() && out.getTime() < secondCarInTime.getTime()) {
									list.get(i).add(j, car);
									totalduration[i] -= car.getSlotDuration();
									carAdded();
									operations();
								}
							}
						}
					}
				}else{
					slotNotAvailabale();
					operations();
				}
			}
		}
		slotNotAvailabale();
		operations();
	}

	private static void removeCar() throws Exception {
		System.out.print("Enter Car Number : ");
		Scanner scanner = new Scanner(System.in);
		String carNumber = scanner.nextLine();
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).size(); j++) {
				if(carNumber == ((Car) list.get(i).get(j)).getCarNumber()) {
					list.get(i).remove(j);
					System.out.println("Car Removed");
					operations();
				}else {
					System.out.println("Car Does Not Exist!");
					operations();
				}
			}
		}
		

	}

	private static void viewSlots() throws Exception {
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Cars Scheduled at Slot "+ i + " : ");
			System.out.println();
			for (int j = 0; j < list.get(i).size(); j++) {
				System.out.print(((Car) list.get(i).get(j)).getCarNumber());
				System.out.print(" || " + ((Car) list.get(i).get(j)).getInTime());
				System.out.println(" || " + ((Car) list.get(i).get(j)).getOutTime());
			}
		}
		operations();
	}
	
	public static void carAdded() {
		System.out.println();
		System.out.println("[Car is Added]");
		System.out.println();
		return;
	}
	
	public static void slotNotAvailabale() {
		System.out.println();
		System.out.println("[Slot not available for selected time]");
		System.out.println();
		return;
	}
}
