package ResumeItems;
import java.time.LocalDate;

public class Experience extends ResumeItem {
    
    public String role;
    public String employer;
    public String[] description;
    public String location;
    public LocalDate startDate;
    public LocalDate endDate;

    public Experience(String role, String employer, String[] description, String location, LocalDate startDate, LocalDate endDate) {
        this.role = role;
        this.employer = employer;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toLatex() {

        String dateRange = super.getResumeFormattedDate(startDate) + " -- " + ((endDate.isAfter(LocalDate.now())) ? "Present" : super.getResumeFormattedDate(endDate));


        String latex = 
            "\\resumeSubheading\n" + //
            indentText(1, "{" + role + "}{" + dateRange + "}\n") + //
            indentText(1, "{" + employer + "}{" + location + "}\n") + //
            indentText(1, "\\resumeItemListStart\n") + //
            indentText(2, descriptionToLatex(description)) + //
            indentText(1, "\\resumeItemListEnd\n");
        
        return latex;
    }

}
