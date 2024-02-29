package org.verstyukhnutov.kmateam.components;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class University {
    private HashMap<String, Faculty> faculties;
    private List<Student> students;

    public University() {
        this.faculties = new HashMap<>();
        this.students = new ArrayList<>();
    }

    public University(HashMap<String, Faculty> faculties) {
        this.faculties = faculties;
        this.students = new ArrayList<>();
        updateStudents();
    }

    public HashMap<String, Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(HashMap<String, Faculty> faculties) {
        this.faculties = faculties;
        updateStudents();
    }

    public void addFaculty(Faculty faculty) {
        faculties.put(faculty.getName(), faculty);
        updateStudents();
    }

    public void removeFaculty(String name) {
        faculties.remove(name);
        updateStudents();
    }

    public Faculty getFaculty(String name) {
        return faculties.get(name);
    }

    public List<Student> getAllStudentsSortedByCourse() {
        List<Student> sortedStudents = new ArrayList<>(students);
        sortedStudents.sort(Comparator.comparingInt(Student::getCourse));
        return sortedStudents;
    }

    public List<Student> getStudentsByPIB(String pib) {
        List<Student> studentsByPIB = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equals(pib)) {
                studentsByPIB.add(student);
            }
        }
        return studentsByPIB;
    }

    public List<Student> getStudentsByCourse(int course) {
        List<Student> studentsByCourse = new ArrayList<>();
        for (Student student : students) {
            if (student.getCourse() == course) {
                studentsByCourse.add(student);
            }
        }
        return studentsByCourse;
    }

    public List<Student> getStudentsByGroup(int group) {
        List<Student> studentsByGroup = new ArrayList<>();
        for (Student student : students) {
            if (student.getGroup() == group) {
                studentsByGroup.add(student);
            }
        }
        return studentsByGroup;
    }

    private void updateStudents() {
        students.clear();
        for (Faculty faculty : faculties.values()) {
            for (Department department : faculty.getDepartments().values()) {
                students.addAll(department.getStudents());
            }
        }
    }
}
