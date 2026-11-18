package App.Model.Resume;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import App.Model.LatexCompiler.LatexCompiler;
import App.Model.Resume.ResumeItems.EducationGroup;
import App.Model.Resume.ResumeItems.ExperienceGroup;
import App.Model.Resume.ResumeItems.Header;
import App.Model.Resume.ResumeItems.ProjectGroup;
import App.Model.Resume.ResumeItems.ResumeItem;
import App.Model.Resume.ResumeItems.TechnicalSkillsGroup;

public class Resume {

    private final String preambleFile = "resume/resources/jakes_resume_preamble.tex";
    private final String preambleLatex;

    public ArrayList<ResumeItem> items = new ArrayList<>();
    private final LatexCompiler latexCompiler = new LatexCompiler();
    
    private final String canidateFullName;

    public Resume(
        Header header, 
        EducationGroup education, 
        ExperienceGroup experience, 
        ProjectGroup projects, 
        TechnicalSkillsGroup technicalSkills) 
    throws IOException {
        
        Path filePath = Path.of(preambleFile);
        preambleLatex = Files.readString(filePath);

        items.add(header);
        items.add(education);
        items.add(experience);
        items.add(projects);
        items.add(technicalSkills);

        canidateFullName = header.name;
    }

    public String export() throws Exception {

        String bodyLatex = "";

        for (ResumeItem item : items) {
            bodyLatex += item.toLatex() + "\n";
        }

        String latex = preambleLatex + "\n\\begin{document}" + bodyLatex + "\\end{document}";

        String sanitizeName = canidateFullName.replace(" ", "_").toLowerCase().trim();

        latexCompiler.generateLatexFile(sanitizeName + ".tex", latex);
        return latexCompiler.compile(sanitizeName + ".tex");

    }

}
