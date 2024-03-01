package org.verstyukhnutov.kmateam.components;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.verstyukhnutov.kmateam.utils.Debug;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class University {
    @JsonProperty("Факультети")
    HashMap<String, Faculty> faculties;

    public University() {
        this.faculties = new HashMap<String, Faculty>();
    }

    public University(HashMap<String, Faculty> faculties) {
        this.faculties = faculties;
    }

    public static University fromJson() {
        try {
            File file = new File("university.json");
            Scanner scanner = new Scanner(file);
            String json = new String();
            while (scanner.hasNextLine()) {
                json += scanner.nextLine();
            }
            scanner.close();

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, University.class);
        } catch (FileNotFoundException fnfe) {
            File file = new File("university.json");
            University university = new University();

            try {
                file.createNewFile();
                ObjectMapper mapper = new ObjectMapper();        
                String json = mapper.writeValueAsString(university);

                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(json);
                writer.close();
            } catch (IOException ioe) {
                Debug.error("I/O error: "+ioe.getMessage());
                System.exit(-1);
            }

            return university;
        } catch (JsonProcessingException jpe) {
            Debug.error("Cannot process JSON: "+jpe.getMessage());
            System.exit(-1);
            return null;
        }
    }

    public void toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();        
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);

            BufferedWriter writer = new BufferedWriter(new FileWriter("university.json"));
            writer.write(json);
            writer.close();
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
    }

    public HashMap<String, Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(HashMap<String, Faculty> faculties) {
        this.faculties = faculties;
    }

    public boolean addFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getName())) {
            return false;
        } else {
            faculties.put(faculty.getName(), faculty);
            return true;
        }
    }

    public boolean removeFaculty(String name) {
        if (faculties.containsKey(name)) {
            faculties.remove(name);
            return true;
        } else {
            return false;
        }
    }

    public Faculty getFaculty(String name) {
        return faculties.get(name);
    }

    public List<Student> getStudentsByPIB(String pib) {
        List<Student> studentsByPIB = new ArrayList<>();
        for (Faculty fac : faculties.values()) {
            for (Department dep : fac.getDepartments().values()) {
                for (Student student : dep.getStudents().values()) {
                    if (student.getName().equals(pib)) {
                        studentsByPIB.add(student);
                    }
                }
            }
        }
        return studentsByPIB;
    }

    public List<Student> getStudentsByCourse(int course) {
        List<Student> studentsByCourse = new ArrayList<>();
        for (Faculty fac : faculties.values()) {
            for (Department dep : fac.getDepartments().values()) {
                for (Student student : dep.getStudents().values()) {
                    if (student.getCourse() == course) {
                        studentsByCourse.add(student);
                    }
                }
            }
        }
        return studentsByCourse;
    }

    public List<Student> getStudentsByGroup(int group) {
        List<Student> studentsByGroup = new ArrayList<>();
        for (Faculty fac : faculties.values()) {
            for (Department dep : fac.getDepartments().values()) {
                for (Student student : dep.getStudents().values()) {
                    if (student.getGroup() == group) {
                        studentsByGroup.add(student);
                    }
                }
            }
        }
        return studentsByGroup;
    }
}
