package org.verstyukhnutov.kmateam.components;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import picocli.CommandLine.Option;

/**
 * Represents a department in university.
 */
public class Department {
    @JsonProperty("Назва кафедри")
    @Option(names = "--department-name", required = true)
    String name;

    @JsonProperty("Назва факультету")
    @Option(names = "--department-faculty", required = true)
    String faculty;

    @JsonProperty("Викладачі")
    List<Teacher> teachers = new ArrayList<Teacher>();

    @JsonProperty("Студенти")
    List<Student> students = new ArrayList<Student>();

    /**
     * Returns the name of the department.
     *
     * @return the name of the department
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the faculty the department belongs to.
     *
     * @return the faculty the department belongs to
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Returns the list of teachers in the department.
     *
     * @return the list of teachers in the department
     */
    public List<Teacher> getTeachers() {
        return teachers;
    }

    /**
     * Returns the list of students in the department.
     *
     * @return the list of students in the department
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Sets the name of the department.
     *
     * @param name the new name of the department
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the faculty the department belongs to.
     *
     * @param faculty the new faculty the department belongs to
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    /**
     * Sets the list of teachers in the department.
     *
     * @param teachers the new list of teachers in the department
     */
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    /**
     * Sets the list of students in the department.
     *
     * @param students the new list of students in the department
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * Adds a teacher to the department.
     *
     * @param teacher the teacher to add
     */
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    /**
     * Removes a teacher from the department.
     *
     * @param teacher the teacher to remove
     */
    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    /**
     * Edits a teacher in the department.
     *
     * @param name the name of the teacher to edit
     * @param teacher the new teacher to replace the old one
     */
    public void editTeacher(String name, Teacher teacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getName().equals(name)) {
                teachers.set(i, teacher);
                break;
            }
        }
    }

    /**
     * Adds a student to the department.
     *
     * @param student the student to add
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Removes a student from the department.
     *
     * @param student the student to remove
     */
    public void removeStudent(Student student) {
        students.remove(student);
    }

    /**
     * Edits a student in the department.
     *
     * @param name the name of the student to edit
     * @param student the new student to replace the old one
     */
    public void editStudent(String name, Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equals(name)) {
                students.set(i, student);
                break;
            }
        }
    }
}