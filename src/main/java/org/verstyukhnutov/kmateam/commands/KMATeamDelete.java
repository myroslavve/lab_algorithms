package org.verstyukhnutov.kmateam.commands;

import org.verstyukhnutov.kmateam.Program;
import org.verstyukhnutov.kmateam.components.Department;
import org.verstyukhnutov.kmateam.components.Faculty;
import org.verstyukhnutov.kmateam.utils.Debug;
import org.verstyukhnutov.kmateam.utils.ManipulateType;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "delete", description = "Delete faculty/department/student/teacher.")
public class KMATeamDelete implements Runnable {

    @Parameters(
        index = "0", 
        paramLabel = "<manipulateType>", 
        description = "manipulate type (department, faculty, student, teacher)"
    )
    ManipulateType manipulateType;

    @Option(
        names = "--name", 
        required = true, 
        description = "manipulate name to search (department, faculty, student, teacher)"
    )
    String manipulateName;

    @Override
    public void run() {
        switch (manipulateType) {
            case faculty:
                deleteFaculty();
                return;
            case department:
                deleteDepartment();
                return;
            case student:
                deleteStudent();
                return;
            case teacher:
                deleteTeacher();
        }
    }

    private void deleteTeacher() {
        for (Faculty fac : Program.university.getFaculties().values()) {
            for (Department dep : fac.getDepartments().values()) {
                for (String teacherName : dep.getTeachers().keySet()) {
                    if (teacherName.equals(manipulateName)) {
                        dep.removeTeacher(teacherName);
                        Debug.info("Teacher is successfully deleted.");
                        return;
                    }
                }
            }
        }

        Debug.error("No such teacher `" + manipulateName + "`!");
    }

    private void deleteStudent() {
        for (Faculty fac : Program.university.getFaculties().values()) {
            for (Department dep : fac.getDepartments().values()) {
                for (String studentName : dep.getStudents().keySet()) {
                    if (studentName.equals(manipulateName)) {
                        dep.removeStudent(studentName);
                        Debug.info("Student is successfully deleted.");
                        return;
                    }
                }
            }
        }

        Debug.error("No such student `" + manipulateName + "`!");
    }

    private void deleteDepartment() {
        for (Faculty fac : Program.university.getFaculties().values()) {
            for (String departmentName : fac.getDepartments().keySet()) {
                if (departmentName.equals(manipulateName)) {
                    fac.removeDepartment(departmentName);
                    Debug.info("Department is successfully deleted.");
                    return;
                }
            }
        }

        Debug.error("No such department `" + manipulateName + "`!");
    }

    private void deleteFaculty() {
        for (String facultyName : Program.university.getFaculties().keySet()) {
            if (facultyName.equals(manipulateName)) {
                Program.university.removeFaculty(facultyName);
                Debug.info("Faculty is successfully deleted.");
                return;
            }
        }

        Debug.error("No such faculty `" + manipulateName + "`!");
    }

}
