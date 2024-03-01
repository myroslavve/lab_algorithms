package org.verstyukhnutov.kmateam.commands;

import java.util.ArrayList;
import java.util.List;

import org.verstyukhnutov.kmateam.Program;
import org.verstyukhnutov.kmateam.components.Department;
import org.verstyukhnutov.kmateam.components.Faculty;
import org.verstyukhnutov.kmateam.components.Student;
import org.verstyukhnutov.kmateam.components.Teacher;
import org.verstyukhnutov.kmateam.utils.ConsoleColor;
import org.verstyukhnutov.kmateam.utils.DisplayType;
import org.verstyukhnutov.kmateam.utils.SearchRange;
import org.verstyukhnutov.kmateam.utils.Sort;
import org.verstyukhnutov.kmateam.utils.SortMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "show", 
    description = "Show students/teachers in university/faculty/department."
)
public class KMATeamShow implements Runnable {

    static class Search {
        @Option(names = "--range", required = true, description = "search in range of faculty, department or university")
        SearchRange searchRange;
        
        @Option(names = "--name", required = false, description = "name of faculty or department")
        String rangeName;
    }

    @Parameters(
        index = "0", 
        paramLabel = "<displayType>", 
        description = "type to display (teachers or students)"
    )
    DisplayType displayType;

    @Option(
        names = "--sort", 
        required = true, 
        description = "sort by alphabet or by courses"
    )
    SortMethod sortMethod;  
    
    @ArgGroup(exclusive = false, heading = "Search parameters%n")
    Search search;

    @Override
    public void run() {
        List<Teacher> teachers = new ArrayList<Teacher>();
        List<Student> students = new ArrayList<Student>();

        if (search == null) {
            System.out.println(ConsoleColor.RED + "`--range` option is mandatory!" + ConsoleColor.RESET);
            return;
        }

        switch (search.searchRange) {
            case department:
                if (search.rangeName != null) {
                    Department dep = null;
                    for (Faculty fac : Program.university.getFaculties().values()) {
                        dep = fac.getDepartment(search.rangeName);
                        if (dep != null)
                            break;
                    }

                    if (dep != null) {
                        switch (displayType) {
                            case students:
                                students = dep.getStudents();
                                break;

                            case teachers:
                                teachers = dep.getTeachers();
                                break;

                            default:
                                break;
                        }

                        break;
                    } else {
                        System.out.println(ConsoleColor.RED + "No such department `" + search.rangeName + "`" + ConsoleColor.RESET);
                        return;
                    } 
                }

                System.out.println(ConsoleColor.RED + "Department name must be specified with `--name`!" + ConsoleColor.RESET);
                return;

            case faculty:
                if (search.rangeName != null) {
                    Faculty fac = Program.university.getFaculty(search.rangeName);

                    if (fac != null) {
                        for (Department dep : fac.getDepartments().values()) {
                            switch (displayType) {
                                case students:
                                    students.addAll(dep.getStudents());
                                    break;

                                case teachers:
                                    teachers.addAll(dep.getTeachers());
                                    break;

                                default:
                                    break;
                            }
                        }

                        break;
                    } else {
                        System.out.println(ConsoleColor.RED + "No such faculty `" + search.rangeName + "`" + ConsoleColor.RESET);
                        return;
                    } 
                }

                System.out.println(ConsoleColor.RED + "Faculty name must be specified with `--name`!" + ConsoleColor.RESET);
                return;
            
            case all:
                if (search.rangeName == null) {
                    for (Faculty fac : Program.university.getFaculties().values()) {
                        for (Department dep : fac.getDepartments().values()) {
                            switch (displayType) {
                                case students:
                                    students.addAll(dep.getStudents());
                                    break;

                                case teachers:
                                    teachers.addAll(dep.getTeachers());
                                    break;

                                default:
                                    break;
                            }
                        }
                    }

                    break;
                }
                
                System.out.println(ConsoleColor.RED + "`--name` must not be specified for `all` sort method!" + ConsoleColor.RESET);
                return;

            case course:
                switch (displayType) {
                    case students:
                        if (search.rangeName != null) {
                            try {
                                int course = Integer.valueOf(search.rangeName);

                                for (Faculty fac : Program.university.getFaculties().values()) {
                                    for (Department dep : fac.getDepartments().values()) {
                                        for (Student stud : dep.getStudents()) {
                                            if (stud.getCourse() == course) {
                                                students.add(stud);
                                            }
                                        }
                                    }
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(ConsoleColor.RED + "`" + search.rangeName + "` is not a number!" + ConsoleColor.RESET);
                                return;
                            }
        
                            break;
                        }

                        System.out.println(ConsoleColor.RED + "Course number must be specified with `--name`!" + ConsoleColor.RESET);
                        return;

                    case teachers:
                        System.out.println(ConsoleColor.RED + "Teachers cannot be sorted by course!" + ConsoleColor.RESET);
                        return;

                    default:
                        break;
                
                }
                break;

            default:
                return;                  
        }

        switch (sortMethod) {
            case byAlphabet:
                switch (displayType) {
                    case students:
                        Sort.sortStudentsAlphabetically(students);
                        break;

                    case teachers:
                        Sort.sortTeachersAlphabetically(teachers);
                        break;

                    default:
                        break;
                
                }
                break;

            case byCourses:
                switch (displayType) {
                    case students:
                        Sort.sortStudentsByCourse(students);
                        break;

                    case teachers:
                        System.out.println(ConsoleColor.RED + "Teachers cannot be sorted by course!" + ConsoleColor.RESET);
                        return;

                    default:
                        break;
                
                }
                break;

            default:
                break;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();    
            switch (displayType) {
                case students:
                    String jsonStudents = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(students);
                    System.out.println("Students: "+jsonStudents);
                    break;

                case teachers:
                    String jsonTeachers = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(teachers);
                    System.out.println("Teachers: "+jsonTeachers);
                    break;
                
                default:
                    break;
                
            }    
            return;
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }
    }

}
