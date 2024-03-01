package org.verstyukhnutov.kmateam.utils;

import java.util.Comparator;
import java.util.List;

import org.verstyukhnutov.kmateam.components.Student;
import org.verstyukhnutov.kmateam.components.Teacher;

// TODO: Sorting
public class Sort {
    /**
     * Sorts teachers by alphabet
     *
     * @param teachers teachers array
     */
    public static void sortTeachersAlphabetically(List<Teacher> teachers) {
        Debug.info("Teachers sorted alphabetically");
    }

    /**
     * Sorts students by alphabet
     *
     * @param students students array
     */
    public static void sortStudentsAlphabetically(List<Student> students) {
        Debug.info("Students sorted alphabetically");
    }

    /**
     * Sorts students by course
     *
     * @param students students array
     */
    public static void sortStudentsByCourse(List<Student> students) {
        students.sort(Comparator.comparingInt(Student::getCourse));
        Debug.info("Students sorted by course");
    }
}
