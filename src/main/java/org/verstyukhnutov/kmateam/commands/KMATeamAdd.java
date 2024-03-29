package org.verstyukhnutov.kmateam.commands;

import org.verstyukhnutov.kmateam.Program;
import org.verstyukhnutov.kmateam.components.Department;
import org.verstyukhnutov.kmateam.components.Faculty;
import org.verstyukhnutov.kmateam.components.Student;
import org.verstyukhnutov.kmateam.components.Teacher;
import org.verstyukhnutov.kmateam.utils.Debug;
import org.verstyukhnutov.kmateam.utils.ManipulateType;
import org.verstyukhnutov.kmateam.utils.Validation;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "add", description = "Add faculty/department/student/teacher.")
public class KMATeamAdd implements Runnable {

    @Parameters(
        index = "0", 
        paramLabel = "<manipulateType>", 
        description = "manipulate type (department, faculty, student, teacher)"
    )
    ManipulateType manipulateType;

    @ArgGroup(exclusive = false, heading = "Teacher options%n")
    Teacher teacher;

    @ArgGroup(exclusive = false, heading = "Student options%n")
    Student student;

    @ArgGroup(exclusive = false, heading = "Faculty options%n")
    Faculty faculty;

    @ArgGroup(exclusive = false, heading = "Department options%n")
    Department department;

    @Override
    public void run() {
        switch (manipulateType) {
            case faculty:
                addFaculty();
                return;
            case department:
                addDepartment();
                return;
            case student:
                addStudent();
                return;
            case teacher:
                addTeacher();
        }
    }

    private void addTeacher() {
        if (teacher == null) {
            Debug.error("All teacher options must be specified!");
            return;
        }

        if (teacher.getDepartment() == null || teacher.getName() == null) {
            Debug.error("All teacher options must be specified!");
            return;
        }

        if (!Validation.isValidName(teacher.getName())) {
            Debug.error("Invalid teacher name!");
            return;
        }

        Department dep = null;
        for (Faculty fac : Program.university.getFaculties().values()) {
            dep = fac.getDepartment(teacher.getDepartment());
            if (dep != null)
                break;
        }

        if (dep == null) {
            Debug.error("No such department `" + teacher.getDepartment() + "`");
            return;
        }

        if (dep.addTeacher(teacher)) {
            Debug.info("Teacher is successfully added.");
        } else {
            Debug.error("The teacher already exists!");
        }
    }

    private void addStudent() {
        if (student == null || student.getName() == null || student.getCourse() == 0 ||
            student.getGroup() == 0 || student.getDepartment() == null) {
            Debug.error("All student options must be specified!");
            return;
        }

        if (!Validation.isValidName(student.getName())) {
            Debug.error("Invalid student name! Name must be of format `P_I_B`");
            return;
        }

        if (!Validation.isValidCourse(student.getCourse())) {
            Debug.error("Invalid student course! Course must be in range 1-4");
            return;
        }

        if (!Validation.isValidGroup(student.getGroup())) {
            Debug.error("Invalid student group! Group must be in range 1-6");
            return;
        }

        Department dep = null;
        for (Faculty fac : Program.university.getFaculties().values()) {
            dep = fac.getDepartment(student.getDepartment());
            if (dep != null)
                break;
        }

        if (dep == null) {
            Debug.error("No such department `" + student.getDepartment() + "`");
            return;
        }

        if (dep.addStudent(student)) {
            Debug.info("Student is successfully added.");
        } else {
            Debug.error("The student already exists!");
        }
    }

    private void addDepartment() {
        if (department == null || department.getName() == null || department.getFaculty() == null) {
            Debug.error("All department options must be specified!");
            return;
        }

        if (department.getName().isEmpty()) {
            Debug.error("Department name cannot be empty!");
            return;
        }

        Faculty fac = Program.university.getFaculty(department.getFaculty());

        if (fac == null) {
            Debug.error("No such faculty `" + department.getFaculty() + "`");
            return;
        }

        if (fac.addDepartment(department)) {
            Debug.info("Department is successfully added.");
        } else {
            Debug.error("The department already exists!");
        }
    }

    private void addFaculty() {
        if (faculty == null || faculty.getName() == null) {
            Debug.error("All faculty options must be specified!");
            return;
        }
        if (faculty.getName().isEmpty()) {
            Debug.error("Faculty name cannot be empty!");
            return;
        }

        if (Program.university.addFaculty(faculty)) {
            Debug.info("Faculty is successfully added.");
        } else {
            Debug.error("The faculty already exists!");
        }
    }
}
