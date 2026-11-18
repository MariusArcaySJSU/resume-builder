package App.Model.Resume.ResumeItems;
import java.time.LocalDate;

public class Project extends ResumeItem {
    
    public String title;
    public String[] technologies;
    public String[] description;
    public LocalDate startDate;
    public LocalDate endDate;
    public boolean displayDate = true;

    public Project(String title, String[] technologies, String[] description, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.technologies = technologies;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Project(String title, String[] technologies, String[] description, LocalDate startDate, LocalDate endDate, boolean displayDate) {
        this(title, technologies, description, startDate, endDate);
        this.displayDate = displayDate;
    }

    public Project(String title, String[] technologies, String[] description) {
        this(title, technologies, description, null, null);
        this.displayDate = false;
    }

    @Override
    public boolean isValid() {
        return !title.isBlank() && technologies.length > 0 && description.length > 0 && (displayDate ? startDate != null : true);
    }

    @Override
    public String toLatex() {

        String dateRange = super.getResumeFormattedDate(startDate) + " -- " + super.getResumeFormattedDate(endDate);
        
        String technologiesCombined = "";
        for (int i = 0; i < technologies.length; ++i) {
            technologiesCombined += technologies[i] + ((i == technologies.length - 1) ? "" : ", ");
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
        if (description.length != 0) descriptionJSON = descriptionJSON.substring(0, descriptionJSON.length() - 2) + "\n";
        descriptionJSON += "],\n";

        String technologiesJSON = "\"technologies\": [";
        for (String technology : technologies) {
            technologiesJSON += "\"" + sanitizeJSON(technology) + "\", ";
        }
        if (technologies.length != 0) technologiesJSON = technologiesJSON.substring(0, technologiesJSON.length() - 2);
        technologiesJSON += "],\n";

        String JSON =
            "{\n" + //
            indentText(1, "\"title\": \"" + sanitizeJSON(title) + "\",\n") + //
            indentText(1, technologiesJSON) + //
            indentText(1, descriptionJSON) + //
            indentText(1, "\"startDate\": \"" + sanitizeJSON(getResumeFormattedDate(startDate)) + "\",\n") + //
            indentText(1, "\"endDate\": \"" + sanitizeJSON(getResumeFormattedDate(endDate)) + "\",\n") + //
            indentText(1, "\"displayDate\": \"" + (displayDate ? "true" : "false") + "\"\n") + //
            "}";

        return JSON;
    }

}
