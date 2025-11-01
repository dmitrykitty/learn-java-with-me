package collections.sorting.practise.practise2;

import java.util.Comparator;
import java.util.List;

public class StudentUtils {
    private StudentUtils() {}

    public static Student getBestStudent1(List<Student> students){
        students.sort(Comparator.comparing(Student::getAvgGrade).reversed());
        return students.getFirst();
    }

    public static Student getBestStudent2(List<Student> students){
        Student bestStudent = null;
        for (Student student : students) {
            if(bestStudent == null || student.getAvgGrade() > bestStudent.getAvgGrade()){
                bestStudent = student;
            }
        }
        return bestStudent;
    }
}
