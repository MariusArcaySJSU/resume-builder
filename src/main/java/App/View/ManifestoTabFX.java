package App.View;

import App.Model.Manifesto.Manifesto;
import App.Model.Resume.Resume;
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
        return null;
    }

}
