package org.example.HW1;

import java.util.Arrays;
import java.util.List;


public class MainApp {
    public static void main(String[] args) {

        List<Double> list = Arrays.asList(1.0,2.0,5.0,7.0);
        double average = averageAllEvenNumbers(list);
        if(average == 0) System.out.println("Четные числа в списке отсутствуют");
        else System.out.printf("среднее значение всех четных чисел в списке: %.2f", average);

    }

    public static double averageAllEvenNumbers(List<Double> numbers){
        return  numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }
}
