package org.verstyukhnutov.kmateam.commands;

import org.verstyukhnutov.kmateam.components.Department;
import org.verstyukhnutov.kmateam.components.Faculty;
import org.verstyukhnutov.kmateam.components.Student;
import org.verstyukhnutov.kmateam.components.Teacher;
import org.verstyukhnutov.kmateam.utils.Debug;
import org.verstyukhnutov.kmateam.utils.ManipulateType;

import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "edit", description = "Edit faculty/department/student/teacher.")
public class KMATeamEdit implements Runnable {

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
                
                    return;
                }

                Debug.error("Faculty must have at least 1 field to edit!");
                return;

            case department:
                if (department != null) {
                    
                    return;
                }

                Debug.error("Department must have at least 1 field to edit!");
                return;

            case student:
                if (student != null) {
                        
                    return;
                }

                Debug.error("Student must have at least 1 field to edit!");
                return;
            
            case teacher:
                if (teacher != null) {
                            
                    return;
                }

                Debug.error("Teacher must have at least 1 field to edit!");
                return;
        }
    }

}
