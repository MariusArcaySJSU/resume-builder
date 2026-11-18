package App.View;

import java.util.ArrayList;

import App.Model.Resume.Resume;
import App.Model.Resume.ResumeItems.Education;
import App.Model.Resume.ResumeItems.EducationGroup;
import App.Model.Resume.ResumeItems.Experience;
import App.Model.Resume.ResumeItems.ExperienceGroup;
import App.Model.Resume.ResumeItems.Header;
import App.Model.Resume.ResumeItems.Project;
import App.Model.Resume.ResumeItems.ProjectGroup;
import App.Model.Resume.ResumeItems.TechnicalSkillsGroup;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class ManifestoTabFX extends VBox {

    private TitledPane headerSection;
    private TitledPane educationSection;
    private TitledPane experienceSection;
    private TitledPane projectSection;
    private TitledPane technicalSkillsSection;

    private VBox educationList;
    private VBox experienceList;
    private VBox projectList;

    public ManifestoTabFX() {

        super(50);

        createHeaderSection();
        createEducationSection();
        createExperienceSection();
        createProjectSection();
        createTechnicalSkillsSection();

        this.setPadding(new Insets(50));
        this.getChildren().addAll(
            headerSection,
            educationSection,
            experienceSection,
            projectSection,
            technicalSkillsSection
        );

    }

    public Resume getResume() {

        HeaderFX headerFX = (HeaderFX) (headerSection.getContent());

        ArrayList<Education> educationModelList = new ArrayList<>();
        for (Node node : educationList.getChildren()) {

            EducationFX educationFX = (EducationFX) ((VBox) node).getChildren().get(0);
            educationModelList.add(educationFX.getEducation());
        
        }

        ArrayList<Experience> experienceModelList = new ArrayList<>();
        for (Node node : experienceList.getChildren()) {

            ExperienceFX experienceFX = (ExperienceFX) ((VBox) node).getChildren().get(0);
            experienceModelList.add(experienceFX.getExperience());
        
        }

        ArrayList<Project> projectModelList = new ArrayList<>();
        for (Node node : projectList.getChildren()) {

            ProjectFX projectFX = (ProjectFX) ((VBox) node).getChildren().get(0);
            projectModelList.add(projectFX.getProject());
        
        }

        Education[] educationModelArray = educationModelList.toArray(Education[]::new);
        Experience[] experienceModelArray = experienceModelList.toArray(Experience[]::new);
        Project[] projectModelArray = projectModelList.toArray(Project[]::new);

        Header header = headerFX.getHeader();
        EducationGroup educationGroup = new EducationGroup(educationModelArray);
        ExperienceGroup experienceGroup = new ExperienceGroup(experienceModelArray);
        ProjectGroup projectGroup = new ProjectGroup(projectModelArray);
        TechnicalSkillsGroup technicalSkillsGroup = ((TechnicalSkillsFX) technicalSkillsSection.getContent()).getTechnicalSkillsGroup();

        Resume resume = null;
        try {
            resume = new Resume(header, educationGroup, experienceGroup, projectGroup, technicalSkillsGroup);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return resume;
        }

        return resume;

    }

    private void createHeaderSection() {

        VBox header = new HeaderFX();
        header.setPadding(new Insets(15));

        headerSection = new TitledPane("Personal Information", header);
        headerSection.setCollapsible(true);

    }

    private void createEducationSection() {

        educationList = new VBox(40);
        educationList.setPadding(new Insets(15));
        
        Button addEducation = new Button("Add education");
        addEducation.setOnAction(event -> {
            addEducation();
        });

        educationSection = new TitledPane("Education", new VBox(educationList, addEducation));
        educationSection.setCollapsible(true);

        addEducation();

    }

    private void createExperienceSection() {

        experienceList = new VBox(40);
        experienceList.setPadding(new Insets(15));
        
        Button addExperience = new Button("Add experience");
        addExperience.setOnAction(event -> {
            addExperience();
        });

        experienceSection = new TitledPane("Experience", new VBox(experienceList, addExperience));
        experienceSection.setCollapsible(true);

        addExperience();
    }

    private void createProjectSection() {

        projectList = new VBox(40);
        projectList.setPadding(new Insets(15));
        
        Button addProject = new Button("Add project");
        addProject.setOnAction(event -> {
            addProject();
        });

        projectSection = new TitledPane("Projects", new VBox(projectList, addProject));
        projectSection.setCollapsible(true);

        addProject();
    }

    private void createTechnicalSkillsSection() {

        TechnicalSkillsFX skill = new TechnicalSkillsFX();
        skill.setPadding(new Insets(15));

        technicalSkillsSection = new TitledPane("Technical Skills", skill);
        technicalSkillsSection.setCollapsible(true);
    }

    private void addEducation() {

        EducationFX education = new EducationFX();
        Button removeButton = new Button("Remove");
        removeButton.setFocusTraversable(false);

        VBox educationBox;
        if (educationList.getChildren().isEmpty()) {
            educationBox = new VBox(10, education, new Separator());
        } else {
            educationBox = new VBox(10, education, removeButton, new Separator());
        }

        educationList.getChildren().add(
            educationBox
        );

        removeButton.setOnAction(event -> {
            educationList.getChildren().remove(educationBox);
        });

    }

    private void addExperience() {

        ExperienceFX experience = new ExperienceFX();
        Button removeButton = new Button("Remove");
        removeButton.setFocusTraversable(false);

        VBox experienceBox;
        if (experienceList.getChildren().isEmpty()) {
            experienceBox = new VBox(10, experience, new Separator());
        } else {
            experienceBox = new VBox(10, experience, removeButton, new Separator());
        }

        experienceList.getChildren().add(
            experienceBox
        );

        removeButton.setOnAction(event -> {
            experienceList.getChildren().remove(experienceBox);
        });
        
    }

    private void addProject() {

        ProjectFX project = new ProjectFX();
        Button removeButton = new Button("Remove");
        removeButton.setFocusTraversable(false);

        VBox projectBox;
        if (projectList.getChildren().isEmpty()) {
            projectBox = new VBox(10, project, new Separator());
        } else {
            projectBox = new VBox(10, project, removeButton, new Separator());
        }

        projectList.getChildren().add(
            projectBox
        );

        removeButton.setOnAction(event -> {
            projectList.getChildren().remove(projectBox);
        });
        
    }

}
