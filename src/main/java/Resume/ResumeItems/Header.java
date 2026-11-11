package Resume.ResumeItems;

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
    public String toLatex() {

        String linksLatex = "";
        for (Link link : links) {
            linksLatex += "$|$ \\href{" + sanitizeLatex(link.destination) + "}{\\underline{" + sanitizeLatex(link.anchorText) + "}} \n";
        }

        String latex = 
            "\\begin{center}\n" + //
            indentText(1, "\\textbf{\\Huge \\scshape " + sanitizeLatex(name) + "} \\\\ \\vspace{1pt}\n") + //
            indentText(1, "\\small " + sanitizeLatex(phone) + " $|$ \\href{mailto:" + sanitizeLatex(email) + "}{\\underline{" + sanitizeLatex(email) + "}}\n") + //
            indentText(1, linksLatex) + //
            "\\end{center}";

        return latex;

    }

}
