package org.verstyukhnutov.kmateam;

import org.verstyukhnutov.kmateam.commands.KMATeam;
import org.verstyukhnutov.kmateam.components.University;

import picocli.CommandLine;

public class Program {
    public static University university = University.fromJson();

    public static void main(String[] args) {
        int exitCode = new CommandLine(new KMATeam()).execute(args);
        university.toJson();
        System.exit(exitCode);
    }
}
