package App.Model.Examples;

import java.util.Scanner;

import App.Model.Manifesto.Manifesto;
import App.Model.Resume.Resume;

public class ManifestoExample {
    
    public static void manifestoExample() throws Exception {

        Scanner scnr = new Scanner(System.in);

        /*
        String jobDescription = "";
        System.out.printf("Enter job description: ");
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();
            if (line.isEmpty()) break;
            jobDescription += line + "\n";
        }*/

        Manifesto manifesto = new Manifesto("example_manifesto.json");
        Resume resume = manifesto.generateResume();
        //Resume resume = manifesto.generateResumeFromJobDescription(jobDescription);
        resume.export("resume");

    }
}
