package ResumeItems;

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
            linksLatex += "$|$ \\href{" + link.destination + "}{\\underline{" + link.anchorText + "}} \n";
        }

        String latex = 
            "\\begin{center}\n" + //
            indentText(1, "\\textbf{\\Huge \\scshape " + name + "} \\\\ \\vspace{1pt}\n") + //
            indentText(1, "\\small " + phone + " $|$ \\href{mailto:" + email + "}{\\underline{" + email + "}}\n") + //
            indentText(1, linksLatex) + //
            "\\end{center}";

        return latex;

    }

}
