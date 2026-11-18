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

    public ArrayList<ResumeItem> items = new ArrayList<>();    

    private final String canidateFullName;
    private final LatexCompiler latexCompiler = new LatexCompiler();
    private final String preambleFile = "resume/resources/jakes_resume_preamble.tex";
    private final String preambleLatex;
    

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

        canidateFullName = header.name.isBlank() ? "untitled" : header.name;
    }

    public String compile() throws Exception {

        String bodyLatex = "";

        for (ResumeItem item : items) {
            bodyLatex += item.toLatex() + "\n";
        }

        String latex = preambleLatex + "\n\\begin{document}" + bodyLatex + "\\end{document}";
    
        latexCompiler.generateLatexFile(getSanitizedCanidateName(), latex);
        return latexCompiler.compile(getSanitizedCanidateName());

    }

    public String getSanitizedCanidateName() {
        return canidateFullName.replace(" ", "_").toLowerCase().trim();
    }

}
