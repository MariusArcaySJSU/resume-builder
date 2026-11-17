package Resume.ResumeItems;
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
    public String toLatex() {

        String dateRange = super.getResumeFormattedDate(startDate) + " -- " + (endDate == null || (endDate.isAfter(LocalDate.now())) ? "Present" : super.getResumeFormattedDate(endDate));
        
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

}
