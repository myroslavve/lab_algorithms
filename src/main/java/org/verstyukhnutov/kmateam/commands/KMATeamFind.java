package org.verstyukhnutov.kmateam.commands;

import java.util.ArrayList;
import java.util.List;

import org.verstyukhnutov.kmateam.Program;
import org.verstyukhnutov.kmateam.components.Department;
import org.verstyukhnutov.kmateam.components.Faculty;
import org.verstyukhnutov.kmateam.components.Student;
import org.verstyukhnutov.kmateam.components.Teacher;
import org.verstyukhnutov.kmateam.utils.Debug;
import org.verstyukhnutov.kmateam.utils.FindType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "find", description = "Find teacher/student by name/course/group.")
public class KMATeamFind implements Runnable {

    static class SearchOptions {
        @Option(names = "--name", required = false, description = "name of student/teacher")
        String name;
        
        @Option(names = "--course", required = false, description = "course of student")
        Integer course;

        @Option(names = "--group", required = false, description = "group of student")
        Integer group;
    }

    @Parameters(
        index = "0", 
        paramLabel = "<findType>", 
        description = "find type (teacher or student)"
    )
    FindType findType;
    
    @ArgGroup(exclusive = false, heading = "Search options%n")
    SearchOptions searchOptions;

    @Override
    public void run() {
        if (searchOptions == null) {
            Debug.error("At least 1 search option must be specified!");
            return;
        }

        switch (findType) {
            case student:
                findStudent();
                return;
            case teacher:
                findTeacher();
            default:
                break;
        }
    }

    private void findTeacher() {
        List<Teacher> teachers = new ArrayList<Teacher>();
        for (Faculty fac : Program.university.getFaculties().values()) {
            for (Department dep : fac.getDepartments().values()) {
                for (Teacher teacher : dep.getTeachers().values()) {
                    if (searchOptions.course != null || searchOptions.group != null) {
                        Debug.error("Cannot search teachers by course or group!");
                        return;
                    }

                    if (searchOptions.name != null && !searchOptions.name.equals(teacher.getName())) {
                        continue;
                    } else {
                        teachers.add(teacher);
                    }
                }
            }
        }

        if (teachers.isEmpty()) {
            Debug.error("No teachers found!");
            return;
        } else {
            try {
                ObjectMapper mapper = new ObjectMapper();
                String jsonStudents = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(teachers);
                Debug.info("Teachers found: "+jsonStudents);
                return;
            } catch (JsonProcessingException jpe) {
                jpe.printStackTrace();
                return;
            }
        }
    }

    private void findStudent() {
        List<Student> students = new ArrayList<Student>();
        for (Faculty fac : Program.university.getFaculties().values()) {
            for (Department dep : fac.getDepartments().values()) {
                for (Student student : dep.getStudents().values()) {
                    if (
                        (searchOptions.name != null && !searchOptions.name.equals(student.getName())) ||
                        (searchOptions.course != null && searchOptions.course != student.getCourse()) ||
                        (searchOptions.group != null && searchOptions.group != student.getGroup())
                    ) {
                        continue;
                    } else {
                        students.add(student);
                    }
                }
            }
        }

        if (students.isEmpty()) {
            Debug.error("No students found!");
            return;
        } else {
            try {
                ObjectMapper mapper = new ObjectMapper();
                String jsonStudents = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(students);
                Debug.info("Students found: "+jsonStudents);
                return;
            } catch (JsonProcessingException jpe) {
                jpe.printStackTrace();
                return;
            }
        }
    }

}
