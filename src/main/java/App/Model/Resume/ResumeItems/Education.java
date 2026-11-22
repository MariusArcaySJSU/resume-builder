package App.Model.Resume.ResumeItems;
import java.time.LocalDate;
import java.util.ArrayList;

public class Education extends ResumeItem {
    
    public String institution;
    public ArrayList<String> degrees;
    public String location;
    public LocalDate startDate;
    public LocalDate endDate;

    public Education(String institution, ArrayList<String> degrees, String location, LocalDate startDate, LocalDate endDate) {
        this.institution = institution;
        this.degrees = new ArrayList<>(degrees);
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean isValid() {
        return !institution.isBlank() && !degrees.isEmpty() && !location.isBlank() && startDate != null && endDate != null;
    }

    @Override
    public String toLatex() {

        String degreesObtainedList = "";
        for (int i = 0; i < degrees.size(); ++i) {
            degreesObtainedList += degrees.get(i) + ((i == degrees.size() - 1) ? "" : ", ");
        }
        
        String dateRange = super.getResumeFormattedDate(startDate) + " -- " + super.getResumeFormattedDate(endDate);

        String latex =  
            "\\resumeSubheading\n" + //
            "\t{" + sanitizeLatex(institution) + "}{" + sanitizeLatex(location) + "}\n" + //
            "\t{" + sanitizeLatex(degreesObtainedList) + "}{" + sanitizeLatex(dateRange) + "}\n";

        return latex;

    }

    @Override
    public String toJSON() {

        String degreesJSON = "\"degrees\": [\n";
        for (String degree : degrees) {
            degreesJSON += indentText(1, "\"" + sanitizeJSON(degree) + "\",\n");
        }
        if (!degrees.isEmpty()) degreesJSON = degreesJSON.substring(0, degreesJSON.length() - 2) + "\n";
        degreesJSON += "],\n";

        String JSON =
            "{\n" + //
            indentText(1, "\"institution\": \"" + sanitizeJSON(institution) + "\",\n") + //
            indentText(1, degreesJSON) + //
            indentText(1, "\"location\": \"" + sanitizeJSON(location) + "\",\n") + //
            indentText(1, "\"start_date\": \"" + sanitizeJSON(toSanitizedDate(startDate)) + "\",\n") + //
            indentText(1, "\"end_date\": \"" + sanitizeJSON(toSanitizedDate(endDate)) + "\"\n") + //
            "}";

        return JSON;

    }

}
