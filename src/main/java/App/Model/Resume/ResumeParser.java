package App.Model.Resume;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import App.Model.Resume.ResumeItems.Education;
import App.Model.Resume.ResumeItems.EducationGroup;
import App.Model.Resume.ResumeItems.Experience;
import App.Model.Resume.ResumeItems.ExperienceGroup;
import App.Model.Resume.ResumeItems.Header;
import App.Model.Resume.ResumeItems.Link;
import App.Model.Resume.ResumeItems.Project;
import App.Model.Resume.ResumeItems.ProjectGroup;
import App.Model.Resume.ResumeItems.TechnicalSkills;
import App.Model.Resume.ResumeItems.TechnicalSkillsGroup;

public class ResumeParser {

    private final ObjectMapper mapper = new ObjectMapper();

    public Resume generateResume(String JSON) throws Exception {

        Header header = generateHeaderFromJSON(JSON);
        EducationGroup educationGroup = generateEducationGroupFromJSON(JSON);
        ExperienceGroup experienceGroup = generateExperienceGroupFromJSON(JSON);
        ProjectGroup projectGroup = generateProjectGroupFromJSON(JSON);
        TechnicalSkillsGroup technicalSkillsGroup = generateTechnicalSkillsGroupFromJSON(JSON);

        return new Resume(header, educationGroup, experienceGroup, projectGroup, technicalSkillsGroup);

    }

    private Header generateHeaderFromJSON(String JSON) throws Exception {

        JsonNode header = mapper.readTree(JSON).path("header");
        String name = header.path("name").asText();
        String phone = header.path("phone").asText();
        String email = header.path("email").asText();

        ArrayList<Link> links = new ArrayList<>();

        JsonNode linksNode = header.path("links");
        for (JsonNode linkNode : linksNode) {
            String url = linkNode.path("url").asText();
            String label = linkNode.has("label") ? linkNode.path("label").asText() : "";

            links.add(label.equals("") ? new Link(url) : new Link(url, label));
        }

        return new Header(name, phone, email, links);

    }

    private EducationGroup generateEducationGroupFromJSON(String JSON) throws Exception {

        JsonNode educationArrayNode = mapper.readTree(JSON).path("education");
        ArrayList<Education> educationList = new ArrayList<>();
        
        for (JsonNode educationNode : educationArrayNode) {

            String institution = educationNode.path("institution").asText();
            String location = educationNode.path("location").asText();
            String startDate = educationNode.path("start_date").asText();
            String endDate = educationNode.path("end_date").asText();

            ArrayList<String> degrees = parseList(educationNode.path("degrees"));

            educationList.add(new Education(institution, degrees, location, stringToDate(startDate), stringToDate(endDate)));
        }

        EducationGroup educationGroup = new EducationGroup(educationList);
        return educationGroup;

    }

    private ExperienceGroup generateExperienceGroupFromJSON(String JSON) throws Exception {

        JsonNode experienceArrayNode = mapper.readTree(JSON).path("experience");
        ArrayList<Experience> experienceList = new ArrayList<>();
        
        for (JsonNode experienceNode : experienceArrayNode) {

            String role = experienceNode.path("role").asText();
            String employer = experienceNode.path("employer").asText();
            String location = experienceNode.path("location").asText();
            String startDate = experienceNode.path("start_date").asText();
            String endDate = experienceNode.path("end_date").asText();

            ArrayList<String> description = parseList(experienceNode.path("description"));

            experienceList.add(new Experience(role, employer, description, location, stringToDate(startDate), stringToDate(endDate)));
        }

        ExperienceGroup experienceGroup = new ExperienceGroup(experienceList);
        return experienceGroup;

    }

    private ProjectGroup generateProjectGroupFromJSON(String JSON) throws Exception {
     
        JsonNode projectArrayNode = mapper.readTree(JSON).path("project");
        ArrayList<Project> projectList = new ArrayList<>();
        
        for (JsonNode projectNode : projectArrayNode) {

            String title = projectNode.path("title").asText();
            String startDate = projectNode.path("start_date").asText();
            String endDate = projectNode.path("end_date").asText();
            Boolean displayDate = projectNode.path("display_date").asBoolean();

            ArrayList<String> technologies = parseList(projectNode.path("technologies"));
            ArrayList<String> description = parseList(projectNode.path("description"));

            projectList.add(new Project(title, technologies, description, stringToDate(startDate), stringToDate(endDate), displayDate));
        }

        ProjectGroup projectGroup = new ProjectGroup(projectList);
        return projectGroup;

    }

    private TechnicalSkillsGroup generateTechnicalSkillsGroupFromJSON(String JSON) throws Exception {

        JsonNode technicalSkillsNode = mapper.readTree(JSON).path("technical_skills");
        ArrayList<TechnicalSkills> technicalSkillsList = new ArrayList<>();

        for (JsonNode technicalSkill : technicalSkillsNode) {
            String category = technicalSkill.path("category").asText();
            ArrayList<String> skills = parseList(technicalSkill.path("skills"));

            technicalSkillsList.add(new TechnicalSkills(category, skills));
        }

        TechnicalSkillsGroup technicalSkillsGroup = new TechnicalSkillsGroup(technicalSkillsList);
        return technicalSkillsGroup;

    }

    private ArrayList<String> parseList(JsonNode jsonList) {

        ArrayList<String> list = new ArrayList<>();

        for (JsonNode element : jsonList) {
            list.add(element.asText());
        }

        return list;
    }

    private LocalDate stringToDate(String date) {

        if (date == null || date.equals("null") || date.equals("")) return null;

        ArrayList<String> split = new ArrayList<>(Arrays.asList(date.split("-")));
        return LocalDate.of(Integer.parseInt(split.get(0).trim()), Integer.parseInt(split.get(1).trim()), Integer.parseInt(split.get(2).trim()));
    }

}