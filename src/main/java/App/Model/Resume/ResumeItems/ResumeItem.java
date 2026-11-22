package App.Model.Resume.ResumeItems;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public abstract class ResumeItem {

    public static final String INDENTATION = "    ";
    public abstract boolean isValid();
    public abstract String toLatex();
    public abstract String toJSON();

    // LocalDate.toString() but with additonal null checking
    protected String toSanitizedDate(LocalDate date) {
        if (date == null) return "null";
        return date.toString();
    }

    protected String getResumeFormattedDate(LocalDate date) {

        if (date == null) return "Present";

        String formattedMonth = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH).substring(0, 3) + ".";
        return formattedMonth + " " + date.getYear() + (date.isAfter(LocalDate.now()) ? " (Expected)" : "");

    }

    public static String indentText(String text) {
        return text.replaceAll("(?m)^", INDENTATION);
    }

    public static String indentText(int times, String text) {
        assert (times > 0);
        String result = text;
        
        for (int i = 0; i < times; ++i) {
            result = indentText(result);
        }
        
        return result;
    }

    protected static String descriptionToLatex(ArrayList<String> description) {
        String latex = "";

        for (String bulletPoint : description) {
            latex += "\\resumeItem{" + sanitizeLatex(bulletPoint) + "}\n";
        }
        
        return latex;
    }

    // Sanitizes latex escape characters with a blacklash so it doesn't crash latex 
    protected static String sanitizeJSON(String text) {

        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\t':
                    sb.append(INDENTATION);
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }

        return sb.toString();
    }

    // Sanitizes latex escape characters with a blacklash so it doesn't crash latex 
    protected static String sanitizeLatex(String text) {

        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            switch (c) {
                case '\\':
                    sb.append("\\textbackslash{}");
                    break;
                case '{':
                    sb.append("\\{");
                    break;
                case '}':
                    sb.append("\\}");
                    break;
                case '$':
                    sb.append("\\$");
                    break;
                case '&':
                    sb.append("\\&");
                    break;
                case '#':
                    sb.append("\\#");
                    break;
                case '^':
                    sb.append("\\textasciicircum{}");
                    break;
                case '_':
                    sb.append("\\_");
                    break;
                case '~':
                    sb.append("\\textasciitilde{}");
                    break;
                case '%':
                    sb.append("\\%");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }

        return sb.toString();
    }

}
