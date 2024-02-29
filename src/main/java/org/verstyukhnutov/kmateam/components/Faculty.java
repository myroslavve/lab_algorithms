package org.verstyukhnutov.kmateam.components;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a faculty in a university or college.
 */
public class Faculty {
    Map<String, Department> departments;

    /**
     * Constructs a new Faculty with an empty map of departments.
     */
    public Faculty() {
        this.departments = new HashMap<>();
    }

    /**
     * Returns the map of departments in the faculty.
     *
     * @return the map of departments in the faculty
     */
    public Map<String, Department> getDepartments() {
        return departments;
    }

    /**
     * Sets the map of departments in the faculty.
     *
     * @param departments the new map of departments in the faculty
     */
    public void setDepartments(Map<String, Department> departments) {
        this.departments = departments;
    }

    /**
     * Adds a department to the faculty.
     *
     * @param name the name of the department
     * @param department the department to add
     */
    public void addDepartment(String name, Department department) {
        departments.put(name, department);
    }

    /**
     * Removes a department from the faculty.
     *
     * @param name the name of the department to remove
     */
    public void removeDepartment(String name) {
        departments.remove(name);
    }

    /**
     * Edits a department in the faculty.
     *
     * @param name the name of the department to edit
     * @param department the new department to replace the old one
     */
    public void editDepartment(String name, Department department) {
        departments.put(name, department);
    }
}