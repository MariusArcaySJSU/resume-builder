package ResumeItems;
import java.time.LocalDate;

public class Education extends ResumeItem {
    
    public String schoolName;
    public String[] degrees;
    public String location;
    public LocalDate startDate;
    public LocalDate endDate;

    public Education(String schoolName, String[] degrees, String location, LocalDate startDate, LocalDate endDate) {
        this.schoolName = schoolName;
        this.degrees = degrees;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toLatex() {

        String degreesObtainedList = "";
        for (int i = 0; i < degrees.length; ++i) {
            degreesObtainedList += degrees[i] + ((i == degrees.length - 1) ? "" : ", ");
        }
    
        String dateRange = super.getResumeFormattedDate(startDate) + " -- " + super.getResumeFormattedDate(endDate) + ((endDate.isAfter(LocalDate.now())) ? " (Expected)" : "");

        String latex =  
            "resumeSubheading\n" + //
            "\t{" + schoolName + "}{" + location + "}\n" + //
            "\t{" + degreesObtainedList + "}{" + dateRange + "}\n";

        return latex;

    }

}
