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

        String dateRange = super.getResumeFormattedDate(startDate) + " -- " + ((endDate.isAfter(LocalDate.now())) ? "Current" : super.getResumeFormattedDate(endDate));

        String latex = 
            "   \\resumeSubheading\n" + //
            "   {" + role + "}{" + dateRange + "}\n" + //
            "   {" + employer + "}{" + location + "}\n" + //
            "   \\resumeItemListStart\n" + //
            "       \\resumeItem{Developed a REST API using FastAPI and PostgreSQL to store data from learning management systems}\n" + //
            "       \\resumeItem{Developed a full-stack web application using Flask, React, PostgreSQL and Docker to analyze GitHub data}\n" + //
            "       \\resumeItem{Explored ways to visualize GitHub collaboration in a classroom setting}\n" + //
            "   \\resumeItemListEnd\n";
        
        return latex;
    }

}
