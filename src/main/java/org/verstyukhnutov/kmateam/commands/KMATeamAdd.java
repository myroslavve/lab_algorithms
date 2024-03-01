package org.verstyukhnutov.kmateam.commands;

import org.verstyukhnutov.kmateam.Program;
import org.verstyukhnutov.kmateam.components.Department;
import org.verstyukhnutov.kmateam.components.Faculty;
import org.verstyukhnutov.kmateam.components.Student;
import org.verstyukhnutov.kmateam.components.Teacher;
import org.verstyukhnutov.kmateam.utils.Debug;
import org.verstyukhnutov.kmateam.utils.ManipulateType;

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
                if (faculty != null) {
                    if (faculty.getName() != null) {
                        if (Program.university.addFaculty(faculty)) {
                            Debug.info("Faculty is successfully added.");
                        } else {
                            Debug.error("The faculty already exists!");
                        }

                        return;
                    }
                }

                Debug.error("All faculty options must be specified!");
                return;

            case department:
                if (department != null) {
                    if (department.getName() != null && department.getFaculty() != null) {
                        Faculty fac = Program.university.getFaculty(department.getFaculty());
                        if (fac != null) {
                            if (fac.addDepartment(department)) {
                                Debug.info("Department is successfully added.");
                            } else {
                                Debug.error("The department already exists!");
                            }
                            return;
                        } else {
                            Debug.error("No such faculty `" + department.getFaculty() + "`");
                            return;
                        }
                    }
                }

                Debug.error("All department options must be specified!");
                return;

            case student:
                if (student != null) {
                    if (student.getName() != null && 
                        student.getCourse() != 0 && 
                        student.getGroup() != 0 && 
                        student.getDepartment() != null)
                    {
                        Department dep = null;
                        for (Faculty fac : Program.university.getFaculties().values()) {
                            dep = fac.getDepartment(student.getDepartment());
                            if (dep != null)
                                break;
                        }

                        if (dep != null) {
                            if (dep.addStudent(student)) {
                                Debug.info("Student is successfully added.");
                            } else {
                                Debug.error("The student already exists!");
                            }
                            return;
                        } else {
                            Debug.error("No such department `" + student.getDepartment() + "`");
                            return;
                        } 
                    }
                }

                Debug.error("All student options must be specified!");
                return;
            
            case teacher:
                if (teacher != null) {
                    if (teacher.getDepartment() != null && teacher.getName() != null) {
                        Department dep = null;
                        for (Faculty fac : Program.university.getFaculties().values()) {
                            dep = fac.getDepartment(teacher.getDepartment());
                            if (dep != null)
                                break;
                        }
    
                        if (dep != null) {
                            if (dep.addTeacher(teacher)) {
                                Debug.info("Teacher is successfully added.");
                            } else {
                                Debug.error("The teacher already exists!");
                            }
                            return;
                        } else {
                            Debug.error("No such department `" + teacher.getDepartment() + "`");
                            return;
                        } 
                    }
                }

                Debug.error("All teacher options must be specified!");
                return;
        }
    }

}
