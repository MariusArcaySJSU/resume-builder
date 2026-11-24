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
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ManifestoTabFX extends VBox {

    private final HeaderFX headerFX;
    private final EducationGroupFX educationGroupFX;
    private final ExperienceGroupFX experienceGroupFX;
    private final ProjectGroupFX projectGroupFX;
    private final TechnicalSkillsGroupFX technicalSkillsGroupFX;
    private final Button continueButton;

    public ManifestoTabFX() {

        super(50);
        headerFX = new HeaderFX();
        educationGroupFX = new EducationGroupFX();
        experienceGroupFX = new ExperienceGroupFX();
        projectGroupFX = new ProjectGroupFX();
        technicalSkillsGroupFX = new TechnicalSkillsGroupFX();
        continueButton = createContinueButton();

        this.setPadding(new Insets(50));
        this.getStyleClass().add("manifesto-page");
        Label title = new Label("Build your resume");
        title.getStyleClass().add("page-title");
        this.getChildren().addAll(
            title,
            headerFX,
            educationGroupFX,
            experienceGroupFX,
            projectGroupFX,
            technicalSkillsGroupFX,
            continueButton
        );
    }

    public ManifestoTabFX(Manifesto manifesto) throws Exception {
        
        super(50);
        Resume resume = manifesto.generateResume();
        
        headerFX = new HeaderFX(resume.getHeader());
        educationGroupFX = new EducationGroupFX(resume.getEducationGroup());
        experienceGroupFX = new ExperienceGroupFX(resume.getExperienceGroup());
        projectGroupFX = new ProjectGroupFX(resume.getProjectGroup());
        technicalSkillsGroupFX = new TechnicalSkillsGroupFX(resume.getTechnicalSkillsGroup());
        continueButton = createContinueButton();

        this.setPadding(new Insets(50));
        this.getStyleClass().add("manifesto-page");
        Label title = new Label("Build your resume");
        title.getStyleClass().add("page-title");
        this.getChildren().addAll(
            title,
            headerFX,
            educationGroupFX,
            experienceGroupFX,
            projectGroupFX,
            technicalSkillsGroupFX,
            continueButton
        );

    }

    public void setOnContinue(Runnable action) {
        continueButton.setOnAction(event -> action.run());
    }

    private Button createContinueButton() {
        Button button = new Button("Continue to Generate");
        button.getStyleClass().add("primary-action");
        button.setMaxWidth(Double.MAX_VALUE);
        return button;
    }

    public Manifesto generateManifesto() throws Exception {
        
        Resume resume = generateResume();
        return new Manifesto(resume);

    }

    public Resume generateResume() throws Exception {
        
        Header header = headerFX.getHeader();
        EducationGroup educationGroup = educationGroupFX.getEducationGroup();
        ExperienceGroup experienceGroup = experienceGroupFX.getExperienceGroup();
        ProjectGroup projectGroup = projectGroupFX.getProjectGroup();
        TechnicalSkillsGroup technicalSkillsGroup = technicalSkillsGroupFX.getTechnicalSkillsGroup();

        return new Resume(header, educationGroup, experienceGroup, projectGroup, technicalSkillsGroup);

    }

}
