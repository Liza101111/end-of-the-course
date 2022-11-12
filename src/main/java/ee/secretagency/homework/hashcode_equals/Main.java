package ee.secretagency.homework.hashcode_equals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Corolla");
        Car car2 = new Car("Toyota", "Corolla");
        Car car3 = car1;
        System.out.printf("toyota: [%s]%n", car1);
        System.out.println("car1 == car2: " + (car1 == car2));
        System.out.println("car3 == car1: " + (car3 == car1));

        System.out.println("car1.equals(car2): " + (car1.equals(car2)));
        System.out.println("car3.equals(car1): " + (car3.equals(car1)));

        List<Car> carList = new ArrayList<>();

        Set<Car> cars = new HashSet<>();
        cars.add(car1);
        cars.add(car2);
        System.out.println("cars size: %d". formatted(cars.size()));//without hashcode,cars.size is 2
    }

}
