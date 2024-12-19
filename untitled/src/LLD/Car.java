package LLD;

import java.util.*;

public class Car {
    private String name;
    private int carNumber;
    private int pricePerDay;
    private List<CarBooking> bookings;  // A car can have multiple bookings

    public Car(String name, int carNumber, int pricePerDay) {
        this.name = name;
        this.carNumber = carNumber;
        this.pricePerDay = pricePerDay;
        this.bookings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    // Method to calculate the total price for a given booking period
    public int calculateTotalPrice(Date fromDate, Date toDate) {
        long durationInMilliSeconds = toDate.getTime() - fromDate.getTime();
        long durationInDays = durationInMilliSeconds / (1000 * 60 * 60 * 24);
        return (int) durationInDays * pricePerDay;
    }

    // Method to check if the car is available for the requested period
    public boolean isAvailable(Date fromDate, Date toDate) {
        for (CarBooking booking : bookings) {
            if (booking.isOverlapping(fromDate, toDate)) {
                return false;  // If there's an overlap, car is not available
            }
        }
        return true;  // Car is available if no overlapping bookings are found
    }

    // Method to add a booking to the car
    public void addBooking(CarBooking booking) {
        bookings.add(booking);
    }
}

// CarBooking Class
 class CarBooking {
    private Date fromDate;
    private Date toDate;
    private Customer customer;
    private Car car;

    public CarBooking(Date fromDate, Date toDate, Customer customer, Car car) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.customer = customer;
        this.car = car;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Car getCar() {
        return car;
    }

    // Method to check if this booking overlaps with another date range
    public boolean isOverlapping(Date fromDate, Date toDate) {
        System.out.println(this.toDate+" "+this.fromDate);
        return !(this.toDate.before(fromDate) || this.fromDate.after(toDate));
    }
}

// Customer Class
 class Customer {
    private String name;
    private long drivingLicense;
    private String address;
    private long phoneNumber;

    public Customer(String name, long drivingLicense, String address, long phoneNumber) {
        this.name = name;
        this.drivingLicense = drivingLicense;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public long getDrivingLicense() {
        return drivingLicense;
    }

    public String getAddress() {
        return address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }
}

// Booking System Class (Handles Car booking process)
class BookingSystem {

    private List<Car> availableCars;
    private List<CarBooking> allBookings;

    public BookingSystem() {
        this.availableCars = new ArrayList<>();
        this.allBookings = new ArrayList<>();
    }

    // Method to add cars to the system
    public void addCar(Car car) {
        availableCars.add(car);
    }

    // Method to book a car
    public boolean bookCar(Customer customer, int carNumber, Date fromDate, Date toDate) {
        // Find the car by car number
        Car selectedCar = findCarByNumber(carNumber);
        if (selectedCar == null) {
            System.out.println("Car not found.");
            return false;
        }

        // Check if the car is available for the requested period
        if (!selectedCar.isAvailable(fromDate, toDate)) {
            System.out.println("Car is not available for the selected dates.");
            return false;
        }

        // If available, create a new booking
        CarBooking booking = new CarBooking(fromDate, toDate, customer, selectedCar);
        selectedCar.addBooking(booking);
        allBookings.add(booking);
        System.out.println("Booking successful!");
        return true;
    }

    // Method to find a car by its car number
    private Car findCarByNumber(int carNumber) {
        for (Car car : availableCars) {
            if (car.getCarNumber() == carNumber) {
                return car;
            }
        }
        return null;
    }

    // Method to calculate the total price for a booking
    public int calculateBookingPrice(int carNumber, Date fromDate, Date toDate) {
        Car car = findCarByNumber(carNumber);
        if (car != null) {
            return car.calculateTotalPrice(fromDate, toDate);
        }
        return -1; // Return -1 if car not found
    }
}
