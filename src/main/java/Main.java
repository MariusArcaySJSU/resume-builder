import java.time.LocalDate;
import java.util.Scanner;

import ResumeItems.Education;
import ResumeItems.EducationGroup;
import ResumeItems.Experience;
import ResumeItems.ExperienceGroup;
import ResumeItems.Project;
import ResumeItems.ProjectGroup;

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
            new String[] {"Provided personalized expertise to help guests select, understand, and purchase electronics"}, 
            "Morgan Hill, CA", 
            LocalDate.of(2021, 5, 1),
            LocalDate.of(9999, 1, 1)
        );

        Project projectItem = new Project(
            "CUDA Fractal Renderer",
            new String[] {"C++", "CUDA", "Mutli-threading", "Git", "Visual Studio"},
            new String[] {"Built a CUDA fractal renderer achieving a 100× GPU speedup over single-threaded CPU benchmarks", "Developed a benchmarking system comparing CPU/GPU speeds with dynamic iteration scaling and animation rendering", "Automated build setups to check for CUDA support and drop unneeded dependencies"},
            LocalDate.of(2021, 5, 1),
            LocalDate.of(2022, 4, 5)
        );

        EducationGroup educationGroup = new EducationGroup(new Education[] {educationItem});
        ExperienceGroup experienceGroup = new ExperienceGroup(new Experience[] {experienceItem});
        ProjectGroup projectGroup = new ProjectGroup(new Project[] {projectItem});

        System.out.println(educationGroup.toLatex() + "\n");
        System.out.println(experienceGroup.toLatex() + "\n");
        System.out.println(projectGroup.toLatex() + "\n");

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
