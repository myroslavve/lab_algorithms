package org.verstyukhnutov.kmateam.components;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import picocli.CommandLine.Option;

/**
 * Represents a department in university.
 */
public class Department {
    @JsonProperty("Назва кафедри")
    @Option(names = "--department-name", required = false)
    String name;

    @JsonProperty("Назва факультету")
    @Option(names = "--department-faculty", required = false)
    String faculty;

    @JsonProperty("Викладачі")
    Map<String, Teacher> teachers = new HashMap<String, Teacher>();

    @JsonProperty("Студенти")
    Map<String, Student> students = new HashMap<String, Student>();

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
    public Map<String, Teacher> getTeachers() {
        return teachers;
    }

    /**
     * Returns the list of students in the department.
     *
     * @return the list of students in the department
     */
    public Map<String, Student> getStudents() {
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
    public void setTeachers(Map<String, Teacher> teachers) {
        this.teachers = teachers;
    }

    /**
     * Sets the list of students in the department.
     *
     * @param students the new list of students in the department
     */
    public void setStudents(Map<String, Student> students) {
        this.students = students;
    }

    /**
     * Adds a teacher to the department.
     *
     * @param teacher the teacher to add
     */
    public boolean addTeacher(Teacher teacher) {
        if (teachers.containsKey(teacher.getName())) {
            return false;
        } else {
            teachers.put(teacher.getName(), teacher);
            return true;
        }
    }

    /**
     * Removes a teacher from the department.
     *
     * @param teacher the teacher to remove
     */
    public boolean removeTeacher(String name) {
        if (teachers.containsKey(name)) {
            teachers.remove(name);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds a student to the department.
     *
     * @param student the student to add
     */
    public boolean addStudent(Student student) {
        if (students.containsKey(student.getName())) {
            return false;
        } else {
            students.put(student.getName(), student);
            return true;
        }
    }

    /**
     * Removes a student from the department.
     *
     * @param student the student to remove
     */
    public boolean removeStudent(String name) {
        if (students.containsKey(name)) {
            students.remove(name);
            return true;
        } else {
            return false;
        }
    }
}