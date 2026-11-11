package Resume.ResumeItems;

public class TechnicalSkills extends ResumeItem {
    
    public String category;
    public String[] skills;

    public TechnicalSkills(String category, String[] skills) {
        this.category = category;
        this.skills = skills;
    }

    public String toLatex() {

        String skillsCombined = "";
        for (int i = 0; i < skills.length; ++i) {
            skillsCombined += skills[i] + ((i == skills.length - 1) ? "" : ", ");
        }

        return "\\textbf{" + category + "}{: " + skillsCombined + "}\\\\\n";
    }

}
