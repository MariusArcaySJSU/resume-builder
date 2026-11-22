package App.Model.Resume.ResumeItems;
import java.time.LocalDate;
import java.util.ArrayList;

public class Experience extends ResumeItem {
    
    public String role;
    public String employer;
    public ArrayList<String> description;
    public String location;
    public LocalDate startDate;
    public LocalDate endDate;

    public Experience(String role, String employer, ArrayList<String> description, String location, LocalDate startDate, LocalDate endDate) {
        this.role = role;
        this.employer = employer;
        this.description = new ArrayList<>(description);
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Experience(String role, String employer, ArrayList<String> description, String location, LocalDate startDate) {
        this(role, employer, description, location, startDate, null);
    }

    @Override
    public boolean isValid() {
        return !role.isBlank() && !employer.isBlank() && !description.isEmpty() && !location.isBlank() && startDate != null;
    }

    @Override
    public String toLatex() {

        String dateRange = super.getResumeFormattedDate(startDate) + " -- " + super.getResumeFormattedDate(endDate);


        String latex = 
            "\\resumeSubheading\n" + //
            indentText(1, "{" + sanitizeLatex(role) + "}{" + sanitizeLatex(dateRange) + "}\n") + //
            indentText(1, "{" + sanitizeLatex(employer) + "}{" + sanitizeLatex(location) + "}\n") + //
            indentText(1, "\\resumeItemListStart\n") + //
            indentText(2, descriptionToLatex(description)) + //
            indentText(1, "\\resumeItemListEnd\n");
        
        return latex;
    }

    @Override
    public String toJSON() {

        String descriptionJSON = "\"description\": [\n";
        for (String bulletPoint : description) {
            descriptionJSON += indentText(1, "\"" + sanitizeJSON(bulletPoint) + "\",\n");
        }
        if (!description.isEmpty()) descriptionJSON = descriptionJSON.substring(0, descriptionJSON.length() - 2) + "\n";
        descriptionJSON += "],\n";

        String JSON =
            "{\n" + //
            indentText(1, "\"role\": \"" + sanitizeJSON(role) + "\",\n") + //
            indentText(1, "\"employer\": \"" + sanitizeJSON(employer) + "\",\n") + //
            indentText(1, descriptionJSON) + //
            indentText(1, "\"location\": \"" + sanitizeJSON(location) + "\",\n") + //
            indentText(1, "\"startDate\": \"" + sanitizeJSON(safeDateToString(startDate)) + "\",\n") + //
            indentText(1, "\"endDate\": \"" + sanitizeJSON(safeDateToString(endDate)) + "\"\n") + //
            "}";

        return JSON;
    }

}
