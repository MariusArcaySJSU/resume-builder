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

    public Resume() throws IOException {
        Path filePath = Path.of(preambleFile);
        preambleLatex = Files.readString(filePath);
    }

    public Resume(
        Header header, 
        EducationGroup education, 
        ExperienceGroup experience, 
        ProjectGroup projects, 
        TechnicalSkillsGroup technicalSkills) 
    throws IOException {
        this();
        items.add(header);
        items.add(education);
        items.add(experience);
        items.add(projects);
        items.add(technicalSkills);
    }

    public void export(String name) throws Exception {

        String bodyLatex = "";

        for (ResumeItem item : items) {
            bodyLatex += item.toLatex() + "\n";
        }

        String latex = preambleLatex + "\n\\begin{document}" + bodyLatex + "\\end{document}";

        latexCompiler.generateLatexFile(name + ".tex", latex);
        latexCompiler.compile(name + ".tex");

    }

}
