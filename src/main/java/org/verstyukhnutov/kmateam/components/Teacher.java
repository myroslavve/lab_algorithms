package org.verstyukhnutov.kmateam.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import picocli.CommandLine.Option;

/**
 * Represents a teacher in a university or college.
 */
public class Teacher {
    @JsonProperty("ПІБ Викладача")
    @Option(names = "--teacher-name", required = true)
    String name;

    @JsonProperty("Кафедра")
    @Option(names = "--teacher-department", required = true)
    String department;

    // /**
    //  * Constructs a new Teacher with the given name, department, and faculty.
    //  *
    //  * @param name the name of the teacher
    //  * @param department the department the teacher belongs to
    //  * @param faculty the faculty the teacher belongs to
    //  */
    // public Teacher(String name, String department) {
    //     this.name = name;
    //     this.department = department;
    // }

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
     * Returns a string representation of the teacher.
     *
     * @return a string representation of the teacher
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