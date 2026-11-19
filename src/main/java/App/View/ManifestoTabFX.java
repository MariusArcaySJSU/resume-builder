package App.View;

import App.Model.Manifesto.Manifesto;
import App.Model.Resume.Resume;
import App.Model.Resume.ResumeItems.EducationGroup;
import App.Model.Resume.ResumeItems.ExperienceGroup;
import App.Model.Resume.ResumeItems.Header;
import App.Model.Resume.ResumeItems.ProjectGroup;
import App.Model.Resume.ResumeItems.TechnicalSkillsGroup;
import javafx.geometry.Insets;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class ManifestoTabFX extends VBox {

    private TitledPane headerSection;

    private HeaderFX headerFX;
    private EducationGroupFX educationGroupFX;
    private ExperienceGroupFX experienceGroupFX;
    private ProjectGroupFX projectGroupFX;
    private TechnicalSkillsGroupFX technicalSkillsGroupFX;

    public ManifestoTabFX(Resume resume) {

    }

    public ManifestoTabFX() {

        super(50);
        
        headerFX = new HeaderFX();
        educationGroupFX = new EducationGroupFX();
        experienceGroupFX = new ExperienceGroupFX();
        projectGroupFX = new ProjectGroupFX();
        technicalSkillsGroupFX = new TechnicalSkillsGroupFX();

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
