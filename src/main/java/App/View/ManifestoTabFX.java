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

    private HeaderFX headerFX;
    private EducationGroupFX educationGroupFX;
    private ExperienceGroupFX experienceGroupFX;
    private ProjectGroupFX projectGroupFX;
    private TechnicalSkillsGroupFX technicalSkillsGroupFX;

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
            headerFX = new HeaderFX((Header) resume.items.get(0));
            educationGroupFX = new EducationGroupFX((EducationGroup) resume.items.get(1));
            experienceGroupFX = new ExperienceGroupFX((ExperienceGroup) resume.items.get(2));
            projectGroupFX = new ProjectGroupFX((ProjectGroup) resume.items.get(3));
            technicalSkillsGroupFX = new TechnicalSkillsGroupFX((TechnicalSkillsGroup) resume.items.get(4));
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
