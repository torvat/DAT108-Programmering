package no.hvl.dat108.task3;

import no.hvl.dat108.task2.Employee;
import no.hvl.dat108.task2.EmployeeList;
import no.hvl.dat108.task2.enums.Gender;
import no.hvl.dat108.task2.enums.Posistion;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class Main {
    static EmployeeList employees = new EmployeeList();

    public static void main(String[] args) {


        /** a)
         * Lag en ny liste som kun inneholder etternavn til de ansatte
         */

        List<String> firstnames = employees.getEmployees().stream()
                .map(Employee::getLastName)
                .toList();
        System.out.println(firstnames);

        /** b)
         * Finn ut antall kvinner blant de ansatte
         */

        Long countWomen = employees.getEmployees().stream()
                .filter(e -> e.getGender().equals(Gender.FEMALE))
                .count();
        System.out.println("Antall kvinner: " + countWomen);

        /** c)
         * Regn ut gjennomsnittslønnen til kvinnene
         */
        OptionalDouble average = employees.getEmployees().stream()
                .filter(e -> e.getGender().equals(Gender.FEMALE))
                .mapToDouble(Employee::getSalary)
                .average();

        System.out.println("Avarage salaries: " + average);

        /** d)
         * Gi alle sjefer (stilling inne holder noe med "sjef") en lønnsøkning på 7% ved å bruke
         * streams direkte i stedet for metoden du laget i Oppg2. Skriv ut listen av ansatte etter
         * lønnsøkningen
         */

        employees.getEmployees().stream()
                .filter(e -> e.getPosition().equals(Posistion.AVDELINGSLEDER) || e.getPosition().equals(Posistion.DAGLIGLEDER))
                .forEach(e -> e.setSalary(e.getSalary() * 1.07));

        employees.printEmployees();

        /** e)
         * Finn ut (true|false) om det er noen ansatte som tjener mer enn 800.000,-
         */

        employees.getEmployees().stream()
                .filter(e -> e.getSalary() < 800_000)
                .forEach(e -> System.out.println(e.getFirstName()));

        System.out.println();

        /** f)
         * Skriv ut alle de ansatte sysout uten å bruke løkke
         */

        employees.getEmployees()
                .forEach(e -> System.out.println(e.toString()));

        /** g)
         * Finn den/de ansatte som har lavest lønn.
         */
        Employee lowestSal = employees.getEmployees().stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);

        System.out.println("The employee with lowest salary: " + lowestSal.toString());


        /** h)
         * Finn ut summen av alle heltall i [1,1000> som er delelig med 3 eller 5
         */

        Integer sum = IntStream.range(1, 1000)
                .filter(j -> j % 3 == 0 || j % 5 == 0)
                .reduce(0, Integer::sum);

        System.out.println("The sum of the numbers from 1 - 1000 that is divadable with 3 or/and 5 = " + sum);
    }
}
