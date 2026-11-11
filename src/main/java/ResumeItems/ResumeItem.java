package ResumeItems;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public abstract class ResumeItem {

    public static final String indentation = "   ";
    public abstract String toLatex();

    protected String getResumeFormattedDate(LocalDate date) {

        String formattedMonth = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH).substring(0, 3) + ".";
        return formattedMonth + " " + date.getYear();

    }

    protected String indentText(String text) {
        return text.replaceAll("(?m)^", indentation);
    }

    protected String indentText(int times, String text) {
        assert (times > 0);
        String result = text;
        
        for (int i = 0; i < times; ++i) {
            result = indentText(result);
        }
        
        return result;
    }

    protected String descriptionToLatex(String[] description) {
        String latex = "";

        for (String bulletPoint : description) {
            latex += "\\resumeItem{" + bulletPoint + "}\n";
        }
        
        return latex;
    }

}
