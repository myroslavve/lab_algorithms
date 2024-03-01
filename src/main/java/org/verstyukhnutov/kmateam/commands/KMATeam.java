package org.verstyukhnutov.kmateam.commands;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(
    name = "kmateam", 
    mixinStandardHelpOptions = true, 
    version = "kmateam 0.1", 
    description = "Program for forming a list of students and teachers of NaUKMA University.",
    subcommands = {
        KMATeamAdd.class,
        KMATeamShow.class,
        CommandLine.HelpCommand.class,
    }
)
public class KMATeam implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return 0;
    }

}