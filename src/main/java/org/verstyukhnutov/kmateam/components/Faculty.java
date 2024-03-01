package org.verstyukhnutov.kmateam.components;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import picocli.CommandLine.Option;

/**
 * Represents a faculty in university.
 */
public class Faculty {
    @JsonProperty("Назва факультету")
    @Option(names = "--faculty-name", required = false)
    String name;

    @JsonProperty("Кафедри")
    Map<String, Department> departments = new HashMap<String, Department>();

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
     * Gets a department from the faculty.
     *
     * @param name the name of the department
     */
    public Department getDepartment(String name) {
        return departments.get(name);
    }

    /**
     * Adds a department to the faculty.
     *
     * @param department the department to add
     */
    public boolean addDepartment(Department department) {
        if (departments.containsKey(department.getName())) {
            return false;
        } else {
            departments.put(department.getName(), department);
            return true;
        }
    }

    /**
     * Removes a department from the faculty.
     *
     * @param name the name of the department to remove
     */
    public boolean removeDepartment(String name) {
        if (departments.containsKey(name)) {
            departments.remove(name);
            return true;
        } else {
            return false;
        }
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