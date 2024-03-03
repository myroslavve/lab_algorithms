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
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "edit", description = "Edit faculty/department/student/teacher.")
public class KMATeamEdit implements Runnable {

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
                editFaculty();
                return;
            case department:
                editDepartment();
                return;
            case student:
                editStudent();
                return;
            case teacher:
                editTeacher();
        }
    }

    private void editTeacher() {
        if (teacher != null) {
            Department borrowedDepartment = null;
            Teacher teacherToEdit = null;
            for (Faculty fac : Program.university.getFaculties().values()) {
                for (Department dep : fac.getDepartments().values()) {
                    for (String teacherName : dep.getTeachers().keySet()) {
                        if (teacherName.equals(manipulateName)) {
                            borrowedDepartment = dep;
                            teacherToEdit = dep.getTeacher(teacherName);
                        }
                    }
                }
            }

            if (teacherToEdit != null) {

                if (teacher.getDepartment() != null) {
                    Debug.error("Cannot edit teacher's department! Use `delete` command instead.");
                    return;
                }

                if (teacher.getName() != null) {
                    if (!Validation.isValidName(teacher.getName())) {
                        Debug.error("Invalid teacher name! Name must be of format `P_I_B`");
                        return;
                    }

                    borrowedDepartment.removeTeacher(teacherToEdit.getName());
                    teacherToEdit.setName(teacher.getName());
                    borrowedDepartment.addTeacher(teacherToEdit);
                }

            } else {
                Debug.error("No such student `" + manipulateName + "`!");
                return;
            }

            Debug.info("Teacher is successfully edited.");
            return;
        }

        Debug.error("Teacher must have at least 1 field to edit!");
    }

    private void editStudent() {
        if (student != null) {
            Department borrowedDepartment = null;
            Student studentToEdit = null;
            for (Faculty fac : Program.university.getFaculties().values()) {
                for (Department dep : fac.getDepartments().values()) {
                    for (String studentName : dep.getStudents().keySet()) {
                        if (studentName.equals(manipulateName)) {
                            borrowedDepartment = dep;
                            studentToEdit = dep.getStudent(studentName);
                        }
                    }
                }
            }

            if (studentToEdit != null) {

                if (student.getDepartment() != null) {
                    Debug.error("Cannot edit student's department! Use `delete` command instead.");
                    return;
                }

                if (student.getGroup() != 0) {
                    if (!Validation.isValidGroup(student.getGroup())) {
                        Debug.error("Invalid student group! Group must be in range 1-6");
                        return;
                    }

                    studentToEdit.setGroup(student.getGroup());
                }

                if (student.getCourse() != 0) {
                    if (!Validation.isValidCourse(student.getCourse())) {
                        Debug.error("Invalid student course! Course must be in range 1-6");
                        return;
                    }

                    studentToEdit.setCourse(student.getCourse());
                }

                if (student.getName() != null) {
                    if (!Validation.isValidName(student.getName())) {
                        Debug.error("Invalid student name! Name must be of format `P_I_B`");
                        return;
                    }

                    borrowedDepartment.removeStudent(studentToEdit.getName());
                    studentToEdit.setName(student.getName());
                    borrowedDepartment.addStudent(studentToEdit);
                }

            } else {
                Debug.error("No such student `" + manipulateName + "`!");
                return;
            }

            Debug.info("Student is successfully edited.");
            return;
        }

        Debug.error("Student must have at least 1 field to edit!");
    }

    private void editDepartment() {
        if (department != null) {
            Faculty borrowedFaculty = null;
            Department departmentToEdit = null;
            for (Faculty fac : Program.university.getFaculties().values()) {
                for (String departmentName : fac.getDepartments().keySet()) {
                    if (departmentName.equals(manipulateName)) {
                        borrowedFaculty = fac;
                        departmentToEdit = fac.getDepartment(departmentName);
                    }
                }
            }

            if (departmentToEdit != null) {

                if (department.getFaculty() != null) {
                    Debug.error("Cannot edit department's faculty! Use `delete` command instead.");
                    return;
                }

                if (department.getName() != null) {
                    borrowedFaculty.removeDepartment(departmentToEdit.getName());

                    departmentToEdit.setName(department.getName());

                    for (Student student : departmentToEdit.getStudents().values()) {
                        student.setDepartment(department.getName());
                    }

                    for (Teacher teacher : departmentToEdit.getTeachers().values()) {
                        teacher.setDepartment(department.getName());
                    }

                    borrowedFaculty.addDepartment(departmentToEdit);
                }

            } else {
                Debug.error("No such department `" + manipulateName + "`!");
                return;
            }

            Debug.info("Department is successfully edited.");
            return;
        }

        Debug.error("Department must have at least 1 field to edit!");
    }

    private void editFaculty() {
        if (faculty != null) {
            Faculty facultyToEdit = null;
            for (String facultyName : Program.university.getFaculties().keySet()) {
                if (facultyName.equals(manipulateName)) {
                    facultyToEdit = Program.university.getFaculty(facultyName);
                }
            }

            if (facultyToEdit != null) {

                if (faculty.getName() != null) {
                    Program.university.removeFaculty(facultyToEdit.getName());

                    facultyToEdit.setName(faculty.getName());
                    for (Department dep : facultyToEdit.getDepartments().values()) {
                        dep.setFaculty(faculty.getName());
                    }

                    Program.university.addFaculty(facultyToEdit);
                }

            } else {
                Debug.error("No such faculty `" + manipulateName + "`!");
                return;
            }

            Debug.info("Faculty is successfully edited.");
            return;
        }

        Debug.error("Faculty must have at least 1 field to edit!");
    }

}
