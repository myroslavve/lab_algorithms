package org.verstyukhnutov.kmateam.components;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a faculty in university.
 */
public class Faculty {
    private String name;
    Map<String, Department> departments;

    /**
     * Constructs a new Faculty with an empty map of departments.
     */
    public Faculty(String name) {
        this.departments = new HashMap<>();
        this.name = name;
    }

    /**
     * Constructs a new Faculty with a map of departments.
     *
     * @param departments the map of departments in the faculty
     */
    public Faculty(String name, Map<String, Department> departments) {
        this.departments = departments;
        this.name = name;
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

    /**
     * Returns the name of the faculty.
     *
     * @return the name of the faculty
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the faculty.
     *
     * @param name the new name of the faculty
     */
    public void setName(String name) {
        this.name = name;
    }
}