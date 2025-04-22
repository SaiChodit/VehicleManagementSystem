import java.util.ArrayList;
import java.util.Scanner;

public class VehicleManagementSystem {

    // Abstract Vehicle class
    abstract static class Vehicle {
        protected String id;
        protected String brand;
        protected String model;
        protected int year;

        public Vehicle(String id, String brand, String model, int year) {
            this.id = id;
            this.brand = brand;
            this.model = model;
            this.year = year;
        }

        public abstract String getType();

        public void displayInfo() {
            System.out.println("ID: " + id);
            System.out.println("Brand: " + brand);
            System.out.println("Model: " + model);
            System.out.println("Year: " + year);
        }

        public String getId() {
            return id;
        }
    }

    // Car class
    static class Car extends Vehicle {
        public Car(String id, String brand, String model, int year) {
            super(id, brand, model, year);
        }

        @Override
        public String getType() {
            return "Car";
        }
    }

    // Truck class
    static class Truck extends Vehicle {
        public Truck(String id, String brand, String model, int year) {
            super(id, brand, model, year);
        }

        @Override
        public String getType() {
            return "Truck";
        }
    }

    // VehicleManager class
    static class VehicleManager {
        private ArrayList<Vehicle> vehicles = new ArrayList<>();
        private Scanner scanner = new Scanner(System.in);

        public void addVehicle() {
            System.out.print("Enter vehicle type (Car/Truck): ");
            String type = scanner.nextLine().trim();
            System.out.print("Enter ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter Brand: ");
            String brand = scanner.nextLine();
            System.out.print("Enter Model: ");
            String model = scanner.nextLine();
            System.out.print("Enter Year: ");
            int year = Integer.parseInt(scanner.nextLine());

            Vehicle vehicle;
            if (type.equalsIgnoreCase("Car")) {
                vehicle = new Car(id, brand, model, year);
            } else if (type.equalsIgnoreCase("Truck")) {
                vehicle = new Truck(id, brand, model, year);
            } else {
                System.out.println("Invalid vehicle type.");
                return;
            }

            vehicles.add(vehicle);
            System.out.println("Vehicle added successfully.");
        }

        public void showAllVehicles() {
            if (vehicles.isEmpty()) {
                System.out.println("No vehicles found.");
                return;
            }
            for (Vehicle v : vehicles) {
                System.out.println("\n--- Vehicle Info ---");
                v.displayInfo();
                System.out.println("Type: " + v.getType());
            }
        }

        public void deleteVehicle() {
            System.out.print("Enter vehicle ID to delete: ");
            String id = scanner.nextLine();
            boolean removed = vehicles.removeIf(v -> v.getId().equals(id));
            if (removed) {
                System.out.println("Vehicle deleted successfully.");
            } else {
                System.out.println("Vehicle ID not found.");
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        VehicleManager manager = new VehicleManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Vehicle Management System ---");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Show All Vehicles");
            System.out.println("3. Delete Vehicle");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                choice = -1;
            }

            switch (choice) {
                case 1: manager.addVehicle(); break;
                case 2: manager.showAllVehicles(); break;
                case 3: manager.deleteVehicle(); break;
                case 4: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
