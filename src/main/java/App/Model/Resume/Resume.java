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

    private final ArrayList<ResumeItem> items = new ArrayList<>();    

    private final Header header;
    private final EducationGroup educationGroup;
    private final ExperienceGroup experienceGroup;
    private final ProjectGroup projectGroup;
    private final TechnicalSkillsGroup technicalSkillsGroup;

    private final LatexCompiler latexCompiler = new LatexCompiler();
    private final String preambleFile = "resume/resources/jakes_resume_preamble.tex";
    private final String preambleLatex;

    public Resume(
        Header header, 
        EducationGroup educationGroup, 
        ExperienceGroup experienceGroup, 
        ProjectGroup projectGroup, 
        TechnicalSkillsGroup technicalSkillsGroup) 
    throws IOException {
        
        Path filePath = Path.of(preambleFile);
        preambleLatex = Files.readString(filePath);

        items.add(header);
        items.add(educationGroup);
        items.add(experienceGroup);
        items.add(projectGroup);
        items.add(technicalSkillsGroup);

        this.header = header;
        this.educationGroup = educationGroup;
        this.experienceGroup = experienceGroup;
        this.projectGroup = projectGroup;
        this.technicalSkillsGroup = technicalSkillsGroup;

    }

    public boolean isValid() {

        for (ResumeItem item : items) {
            if (!item.isValid()) {
                System.out.println(item);
                return false;
            }
        }

        return true;

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

    public ArrayList<ResumeItem> getItems() {
        return new ArrayList<>(items);
    }

    public Header getHeader() {
        return header;
    }

    public EducationGroup getEducationGroup() {
        return educationGroup;
    }

    public ExperienceGroup getExperienceGroup() {
        return experienceGroup;
    }

    public ProjectGroup getProjectGroup() {
        return projectGroup;
    }

    public TechnicalSkillsGroup getTechnicalSkillsGroup() {
        return technicalSkillsGroup;
    }

    public String getSanitizedCanidateName() {
        return header.name.replace(" ", "_").toLowerCase().trim();
    }

}
