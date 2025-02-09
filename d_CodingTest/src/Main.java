import java.util.*;

public class Main {
    static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<> ();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bib", 25));
        people.add(new Person("Char", 35));

        Comparator<Person> nameComparator = new Comparator<Person> () {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.name.compareTo(p2.name);
            }
        };

        Collections.sort(people, nameComparator);
        System.out.println(people);

        people.sort((p1, p2) -> Integer.compare(p1.age, p2.age));
        System.out.println(people);
    }
}