package App.Model.Resume.ResumeItems;
import java.time.LocalDate;
import java.util.ArrayList;

public class Project extends ResumeItem {
    
    public String title;
    public ArrayList<String> technologies;
    public ArrayList<String> description;
    public LocalDate startDate;
    public LocalDate endDate;
    public boolean displayDate = true;

    public Project(String title, ArrayList<String> technologies, ArrayList<String> description, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.technologies = new ArrayList<>(technologies);
        this.description = new ArrayList<>(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Project(String title, ArrayList<String> technologies, ArrayList<String> description, LocalDate startDate, LocalDate endDate, boolean displayDate) {
        this(title, technologies, description, startDate, endDate);
        this.displayDate = displayDate;
    }

    public Project(String title, ArrayList<String> technologies, ArrayList<String> description) {
        this(title, technologies, description, null, null);
        this.displayDate = false;
    }

    @Override
    public boolean isValid() {
        return !title.isBlank() && !technologies.isEmpty() && !description.isEmpty() && (displayDate ? startDate != null : true);
    }

    @Override
    public String toLatex() {

        String dateRange = super.getResumeFormattedDate(startDate) + " -- " + super.getResumeFormattedDate(endDate);
        
        String technologiesCombined = "";
        for (int i = 0; i < technologies.size(); ++i) {
            technologiesCombined += technologies.get(i) + ((i == technologies.size() - 1) ? "" : ", ");
        }

        String latex = 
            "\\resumeProjectHeading\r\n" + //
            indentText(1, "{\\textbf{" + sanitizeLatex(title) + "} $|$ \\emph{" + sanitizeLatex(technologiesCombined) + "}}{" + (displayDate ? sanitizeLatex(dateRange) : "") + "}\n") + //
            indentText(1, "\\resumeItemListStart\n") + //
            indentText(2, descriptionToLatex(description)) + //;
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

        String technologiesJSON = "\"technologies\": [";
        for (String technology : technologies) {
            technologiesJSON += "\"" + sanitizeJSON(technology) + "\", ";
        }
        if (!technologies.isEmpty()) technologiesJSON = technologiesJSON.substring(0, technologiesJSON.length() - 2);
        technologiesJSON += "],\n";

        String JSON =
            "{\n" + //
            indentText(1, "\"title\": \"" + sanitizeJSON(title) + "\",\n") + //
            indentText(1, technologiesJSON) + //
            indentText(1, descriptionJSON) + //
            indentText(1, "\"startDate\": \"" + sanitizeJSON(safeDateToString(startDate)) + "\",\n") + //
            indentText(1, "\"endDate\": \"" + sanitizeJSON(safeDateToString(endDate)) + "\",\n") + //
            indentText(1, "\"displayDate\": \"" + (displayDate ? "true" : "false") + "\"\n") + //
            "}";

        return JSON;
    }

}
