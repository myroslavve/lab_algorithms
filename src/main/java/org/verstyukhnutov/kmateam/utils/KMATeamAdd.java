package org.verstyukhnutov.kmateam.utils;

import org.verstyukhnutov.kmateam.Program;
import org.verstyukhnutov.kmateam.components.Department;
import org.verstyukhnutov.kmateam.components.Faculty;
import org.verstyukhnutov.kmateam.components.Student;
import org.verstyukhnutov.kmateam.components.Teacher;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
    name = "add", 
    description = "Add faculty/department/student/teacher."
)
public class KMATeamAdd implements Runnable {

    @Parameters(
        index = "0", 
        paramLabel = "<manipulateType>", 
        description = "manipulate type (department, faculty, student, teacher)"
    )
    ManipulateType manipulateType;

    @ArgGroup(exclusive = false)
    Teacher teacher;

    @ArgGroup(exclusive = false)
    Student student;

    @ArgGroup(exclusive = false)
    Faculty faculty;

    @ArgGroup(exclusive = false)
    Department department;

    @Override
    public void run() {
        switch (manipulateType) {
            case faculty:
                if (faculty != null) {
                    Program.university.addFaculty(faculty);
                    System.out.println(ConsoleColor.GREEN + "Faculty is successfully added." + ConsoleColor.RESET);
                    return;
                }
                break;

            case department:
                if (department != null) {
                    Faculty fac = Program.university.getFaculty(department.getFaculty());
                    if (fac != null) {
                        fac.addDepartment(department);
                        System.out.println(ConsoleColor.GREEN + "Department is successfully added." + ConsoleColor.RESET);
                        return;
                    } else {
                        break;
                    }
                }
                break;

            case student:
                if (student != null) {
                    Department dep = null;
                    for (Faculty fac : Program.university.getFaculties().values()) {
                        dep = fac.getDepartment(student.getDepartment());
                        if (dep != null)
                            break;
                    }

                    if (dep != null) {
                        dep.addStudent(student);
                        System.out.println(ConsoleColor.GREEN + "Student is successfully added." + ConsoleColor.RESET);
                        return;
                    } else {
                        break;
                    } 
                }
                break;
            
            case teacher:
                if (teacher != null) {
                    Department dep = null;
                    for (Faculty fac : Program.university.getFaculties().values()) {
                        dep = fac.getDepartment(teacher.getDepartment());
                        if (dep != null)
                            break;
                    }

                    if (dep != null) {
                        dep.addTeacher(teacher);
                        System.out.println(ConsoleColor.GREEN + "Teacher is successfully added." + ConsoleColor.RESET);
                        return;
                    } else {
                        break;
                    } 
                }
                break;

            default:
                break;
        }

        System.out.println(ConsoleColor.RED + "ERROR" + ConsoleColor.RESET);
        System.exit(-1);
    }

}
