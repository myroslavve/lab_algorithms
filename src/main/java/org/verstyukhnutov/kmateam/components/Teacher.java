package org.verstyukhnutov.kmateam.components;
/**
 * Represents a teacher in a university or college.
 */
public class Teacher {
    private String name;
    private String department;
    private String faculty;

    /**
     * Constructs a new Teacher with the given name, department, and faculty.
     *
     * @param name the name of the teacher
     * @param department the department the teacher belongs to
     * @param faculty the faculty the teacher belongs to
     */
    public Teacher(String name, String department, String faculty) {
        this.name = name;
        this.department = department;
        this.faculty = faculty;
    }

    /**
     * Returns the name of the teacher.
     *
     * @return the name of the teacher
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the department the teacher belongs to.
     *
     * @return the department the teacher belongs to
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Returns the faculty the teacher belongs to.
     *
     * @return the faculty the teacher belongs to
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Sets the name of the teacher.
     *
     * @param name the new name of the teacher
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the department the teacher belongs to.
     *
     * @param department the new department the teacher belongs to
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Sets the faculty the teacher belongs to.
     *
     * @param faculty the new faculty the teacher belongs to
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    /**
     * Returns a string representation of the teacher.
     *
     * @return a string representation of the teacher
     */
    public String toString() {
        return "Teacher{name='" + name + "', department='" + department + "', faculty='" + faculty + "'}";
    }
}