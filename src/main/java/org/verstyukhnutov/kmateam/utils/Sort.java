package org.verstyukhnutov.kmateam.utils;

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
        System.out.println(ConsoleColor.CYAN + "Teachers sorted alphabetically" + ConsoleColor.RESET);
    }

    /**
     * Sorts students by alphabet
     *
     * @param students students array
     */
    public static void sortStudentsAlphabetically(List<Student> students) {
        System.out.println(ConsoleColor.CYAN + "Students sorted alphabetically" + ConsoleColor.RESET);
    }

    /**
     * Sorts students by course
     *
     * @param students students array
     */
    public static void sortStudentsByCourse(List<Student> students) {
        System.out.println(ConsoleColor.CYAN + "Students sorted by course" + ConsoleColor.RESET);
    }
}
