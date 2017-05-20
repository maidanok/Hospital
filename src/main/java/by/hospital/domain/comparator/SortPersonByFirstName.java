package by.hospital.domain.comparator;

import by.hospital.domain.Person;

import java.util.Comparator;

/**
 * Created by Admin on 20.05.2017.
 */
public class SortPersonByFirstName implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        return person1.getFirstName().compareTo(person2.getFirstName());
    }
}
