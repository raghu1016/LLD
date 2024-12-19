package LLD;

import java.util.Date;

public class ZoomCar {
    public static void main(String[] args) {
        Car car1 = new Car("Sedan", 1001, 50);
        Car car2 = new Car("SUV", 1002, 70);

        // Create a customer
        Customer customer = new Customer("John Doe", 123456, "123 Main St", 5551234567L);

        // Create the booking system
        BookingSystem system = new BookingSystem();
        system.addCar(car1);
        system.addCar(car2);

        // Attempt to book a car
        Date fromDate = new Date(2024, 10, 20);  // YYYY, MM, DD
        Date toDate = new Date(2024, 10, 25);
        system.bookCar(customer, 1001, fromDate, toDate);

        // Calculate price for the booking
        int price = system.calculateBookingPrice(1001, fromDate, toDate);
        System.out.println("Total price for booking: " + price);

    }
}
