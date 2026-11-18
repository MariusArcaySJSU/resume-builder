package App.Model.Resume.ResumeItems;

public class Header extends ResumeItem {
    
    public String name;
    public String phone;
    public String email;
    public Link[] links;

    public Header(String name, String phone, String email, Link[] links) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.links = links;
    }

    @Override
    public boolean isValid() {
        return !name.isBlank() && !phone.isBlank() && !email.isBlank();
    }

    @Override
    public String toLatex() {

        String linksLatex = "";
        for (Link link : links) {
            linksLatex += "$|$ \\href{" + sanitizeLatex(link.url) + "}{\\underline{" + sanitizeLatex(link.label) + "}} \n";
        }

        String latex = 
            "\\begin{center}\n" + //
            indentText(1, "\\textbf{\\Huge \\scshape " + sanitizeLatex(name) + "} \\\\ \\vspace{1pt}\n") + //
            indentText(1, "\\small " + sanitizeLatex(phone) + " $|$ \\href{mailto:" + sanitizeLatex(email) + "}{\\underline{" + sanitizeLatex(email) + "}}\n") + //
            indentText(1, linksLatex) + //
            "\\end{center}";

        return latex;

    }

    @Override
    public String toJSON() {

        String linksJSON = "\"links\": [\n";
        for (Link link : links) {
            linksJSON += indentText(1, "{ \"url\": \"" + sanitizeJSON(link.url) + "\", \"label\": \"" + sanitizeJSON(link.label) + "\" },\n");
        }
        if (links.length != 0) linksJSON = linksJSON.substring(0, linksJSON.length() - 2) + "\n";
        linksJSON += "]\n";

        String JSON =
            "\"header\": {\n" + //
            indentText(1, "\"name\": \"" + sanitizeJSON(name) + "\",\n") + //
            indentText(1, "\"phone\": \"" + sanitizeJSON(phone) + "\",\n") + //
            indentText(1, "\"email\": \"" + sanitizeJSON(email) + "\",\n") + //
            indentText(1, linksJSON) + //
            "}";

        return JSON;

    }

}
