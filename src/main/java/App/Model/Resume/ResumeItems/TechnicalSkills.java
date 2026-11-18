package App.Model.Resume.ResumeItems;

public class TechnicalSkills extends ResumeItem {
    
    public String category;
    public String[] skills;

    public TechnicalSkills(String category, String[] skills) {
        this.category = category;
        this.skills = skills;
    }

    @Override
    public boolean isValid() {
        return !category.isBlank() && skills.length > 0;
    }

    @Override
    public String toLatex() {

        String skillsCombined = "";
        for (int i = 0; i < skills.length; ++i) {
            skillsCombined += skills[i] + ((i == skills.length - 1) ? "" : ", ");
        }

        return "\\textbf{" + sanitizeLatex(category) + "}{: " + sanitizeLatex(skillsCombined) + "}\\\\\n";
    }

    @Override
    public String toJSON() {

        String skillsJSON = "\"skills\": [";
        for (String skill : skills) {
            skillsJSON += "\"" + sanitizeJSON(skill) + "\", ";
        }
        if (skills.length != 0) skillsJSON = skillsJSON.substring(0, skillsJSON.length() - 2);
        skillsJSON += "]\n";

        String JSON = 
                "{\n" + //
                indentText(1, "\"category\": \"" + sanitizeJSON(category) + "\",\n") + //
                indentText(1, skillsJSON) + //
                "}";

        return JSON;
    }

}
