package org.verstyukhnutov.kmateam.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import picocli.CommandLine.Option;

/**
 * Represents a student in a university or college.
 */
public class Student {
    @JsonProperty("ПІБ Студента")
    @Option(names = "--student-name", required = true)
    String name;

    @JsonProperty("Курс")
    @Option(names = "--student-course", required = true)
    int course;

    @JsonProperty("Група")
    @Option(names = "--student-group", required = true)
    int group;

    @JsonProperty("Кафедра")
    @Option(names = "--student-department", required = true)
    String department;

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
     * Returns a string representation of the student.
     *
     * @return a string representation of the student
     */
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();        
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "<undefined>";
        }
    }
}