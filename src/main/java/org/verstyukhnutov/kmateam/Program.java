package org.verstyukhnutov.kmateam;

import org.verstyukhnutov.kmateam.components.University;
import org.verstyukhnutov.kmateam.utils.KMATeam;
import picocli.CommandLine;

public class Program {
    public static University university = University.fromJson();

    public static void main(String[] args) {
        int exitCode = new CommandLine(new KMATeam()).execute(args);
        university.toJson();
        System.exit(exitCode);
    }
}
