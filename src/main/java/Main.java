import java.util.Scanner;

import ResumeItems.Education;
import ResumeItems.Experience;
import kotlin.experimental.ExperimentalTypeInference;

import java.time.LocalDate;

public class Main {
    
    public static void main(String args[]) throws Exception {
        
        Education educationItem = new Education(
            "San Jose State University",
            new String[] { "Bachelor of Science in Computer Science" },
            "San Jose, CA",
            LocalDate.of(2026, 1, 1),
            LocalDate.of(2028, 5, 1)
        );

        Experience experienceItem = new Experience(
            "Tech Consultant", 
            "Target", 
            new String[] {"test"}, 
            "Morgan Hill, CA", 
            LocalDate.of(2021, 5, 1),
            LocalDate.of(9999, 1, 1)
        );

        System.out.println(experienceItem.toLatex());

        // latexExample();

    }

    public static void gptExample() throws Exception {

        Scanner scnr = new Scanner(System.in);
        ChatGptService gpt = new ChatGptService();
        
        System.out.printf("Send Message: ");
        String message = scnr.nextLine();

        System.out.println("ChatGPT: " + gpt.sendPrompt(message) );

    }

    public static void latexExample() throws Exception {

        LatexCompiler latexCompiler = new LatexCompiler();

        String outputFile = latexCompiler.compile("marius_resume.tex");
        System.out.println("Latex Succesfully Compiled: " + outputFile);

    }

}
