package App.View;

import App.Model.Manifesto.Manifesto;
import App.Model.Resume.Resume;
import App.Model.Resume.ResumeItems.EducationGroup;
import App.Model.Resume.ResumeItems.ExperienceGroup;
import App.Model.Resume.ResumeItems.Header;
import App.Model.Resume.ResumeItems.ProjectGroup;
import App.Model.Resume.ResumeItems.TechnicalSkillsGroup;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class ManifestoTabFX extends VBox {

    private final HeaderFX headerFX;
    private final EducationGroupFX educationGroupFX;
    private final ExperienceGroupFX experienceGroupFX;
    private final ProjectGroupFX projectGroupFX;
    private final TechnicalSkillsGroupFX technicalSkillsGroupFX;

    public ManifestoTabFX() {
        this(null);
    }

    public ManifestoTabFX(Resume resume) {

        super(50);

        if (resume == null) {
            headerFX = new HeaderFX();
            educationGroupFX = new EducationGroupFX();
            experienceGroupFX = new ExperienceGroupFX();
            projectGroupFX = new ProjectGroupFX();
            technicalSkillsGroupFX = new TechnicalSkillsGroupFX();
        } else {
            headerFX = new HeaderFX(resume.getHeader());
            educationGroupFX = new EducationGroupFX(resume.getEducationGroup());
            experienceGroupFX = new ExperienceGroupFX(resume.getExperienceGroup());
            projectGroupFX = new ProjectGroupFX(resume.getProjectGroup());
            technicalSkillsGroupFX = new TechnicalSkillsGroupFX(resume.getTechnicalSkillsGroup());
        }

        this.setPadding(new Insets(50));
        this.getChildren().addAll(
            headerFX,
            educationGroupFX,
            experienceGroupFX,
            projectGroupFX,
            technicalSkillsGroupFX
        );

    }

    public Manifesto generateManifesto() throws Exception {
        
        Header header = headerFX.getHeader();
        EducationGroup educationGroup = educationGroupFX.getEducationGroup();
        ExperienceGroup experienceGroup = experienceGroupFX.getExperienceGroup();
        ProjectGroup projectGroup = projectGroupFX.getProjectGroup();
        TechnicalSkillsGroup technicalSkillsGroup = technicalSkillsGroupFX.getTechnicalSkillsGroup();

        Resume resume = new Resume(header, educationGroup, experienceGroup, projectGroup, technicalSkillsGroup);
        return new Manifesto(resume);

    }

}
