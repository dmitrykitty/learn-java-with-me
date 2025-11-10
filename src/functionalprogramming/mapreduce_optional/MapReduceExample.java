package functionalprogramming.mapreduce_optional;

import java.util.Optional;
import java.util.stream.Stream;

public class MapReduceExample {
    static void main() {

        Stream.of(new Student(12, "a"),
                        new Student(15, "a"),
                        new Student(20, "a"),
                        new Student(27, "a"),
                        new Student(32, "a"),
                        new Student(50, "a"),
                        new Student(80, "a"),
                        new Student(96, "a"),
                        new Student(102, "a")
                )
                .parallel() //obliczeia rownolegle - przyspiesza prace
                .map(Student::getAge)
                .reduce(Math::max)//(age1, age2) -> Math.max(age1, age2)
                //reduce zwraca optional
                .ifPresent(System.out::println);

        String sumOfAges = Stream.of(new Student(12, "a"),
                        new Student(15, "a"),
                        new Student(20, "a"),
                        new Student(27, "a"),
                        new Student(32, "a"),
                        new Student(50, "a"),
                        new Student(80, "a"),
                        new Student(96, "a"),
                        new Student(102, "a")
                )
                .sequential() //obliczenie w jednym treadu(np, jezeli wyzej było parallel
                .map(Student::getAge)
                .reduce(0, Integer::sum)//(age1, age2) -> Math.max(age1, age2)
                //reduce zwraca optional
//                .ifPresent(System.out::println); -juz nie ma isPresent, bo suma zaczyna sie od 0 wartosci,
//                wiec zawsze bedzie cos zwrocono
                .toString();

        System.out.println(sumOfAges);


        Optional<Student> optionalStudent = Stream.of(new Student(12, "a"),
                        new Student(15, "a"),
                        new Student(20, "a"),
                        new Student(27, "a"),
                        new Student(32, "a"),
                        new Student(50, "a"),
                        new Student(80, "a"),
                        new Student(96, "a"),
                        new Student(102, "a")
                )
                .reduce((st1, st2) -> st1.getAge() > st2.getAge() ? st1 : st2);

        //optional -> jak praca funkjonalna, tylko z jednym elementem
        optionalStudent.ifPresent(System.out::println);
        optionalStudent.map(Student::getAge)
                .filter(age -> age > 50 && age < 105)
                .ifPresent(System.out::println);

        //zamiast ifPresent moznauzyc roznyc or
        optionalStudent.map(Student::getAge)
                .filter(age -> age > 50 && age < 100)
                .orElse(55);

        Stream.of(new Student(12, "a"),
                        new Student(15, "a"),
                        new Student(20, "a"),
                        new Student(27, "a"),
                        new Student(32, "a"),
                        new Student(50, "a"),
                        new Student(80, "a"),
                        new Student(96, "a"),
                        new Student(102, "a")
                )
                .flatMap(student -> student.getMarks().stream()); //zamiast streamu Students dostalismy stream marka
    }


}
