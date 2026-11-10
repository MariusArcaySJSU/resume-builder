package ResumeItems;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public abstract class ResumeItem {

    public abstract String toLatex();

    protected String getResumeFormattedDate(LocalDate date) {

        String formattedMonth = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH).substring(0, 3) + ".";
        return formattedMonth + " " + date.getYear();

    }

}
