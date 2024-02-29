package org.verstyukhnutov.kmateam.components;

/**
 * Represents a student in a university or college.
 */
public class Student {
    private String name;
    private int course;
    private int group;
    private String department;
    private String faculty;

    /**
     * Constructs a new Student with the given name, course, department, and faculty.
     *
     * @param name       the name of the student
     * @param course     the course the student is enrolled in
     * @param group      the group the student is enrolled in
     * @param department the department the student belongs to
     * @param faculty    the faculty the student belongs to
     */
    public Student(String name, int course, int group, String department, String faculty) {
        this.name = name;
        this.course = course;
        this.group = group;
        this.department = department;
        this.faculty = faculty;
    }

    /**
     * Returns the name of the student.
     *
     * @return the name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the course the student is enrolled in.
     *
     * @return the course the student is enrolled in
     */
    public int getCourse() {
        return course;
    }

    /**
     * Returns the group the student is enrolled in.
     *
     * @return the group the student is enrolled in
     */
    public int getGroup() {
        return group;
    }

    /**
     * Returns the department the student belongs to.
     *
     * @return the department the student belongs to
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Returns the faculty the student belongs to.
     *
     * @return the faculty the student belongs to
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Sets the name of the student.
     *
     * @param name the new name of the student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the course the student is enrolled in.
     *
     * @param course the new course the student is enrolled in
     */
    public void setCourse(int course) {
        this.course = course;
    }

    /**
     * Sets the group the student is enrolled in.
     *
     * @param group the new group the student is enrolled in
     */
    public void setGroup(int group) {
        this.group = group;
    }

    /**
     * Sets the department the student belongs to.
     *
     * @param department the new department the student belongs to
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Sets the faculty the student belongs to.
     *
     * @param faculty the new faculty the student belongs to
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    /**
     * Returns a string representation of the student.
     *
     * @return a string representation of the student
     */
    public String toString() {
        return "Student{name='" + name + "', course=" + course + ", department='" + department + "', faculty='" + faculty + "'}";
    }
}