package Resume.ResumeItems;

public abstract class ResumeItemGroup extends ResumeItem {
    
    protected String groupName;
    protected boolean smallText = false;    // mainly used in TechnicalSkillsGroup
    public ResumeItem[] items;
    
    @Override
    public String toLatex() {

        String itemsAppendedLatex = "";
        for (ResumeItem item : items) {
            itemsAppendedLatex += indentText(2, item.toLatex());
        }

        String latex;

        if (smallText) {

            latex = 
                "\\section{" + groupName + "}\n" + //
                indentText(1, "\\begin{itemize}[leftmargin=0.15in, label={}]\n") + //
                indentText(2, "\\small{\\item{\n") + //
                indentText(3, itemsAppendedLatex) + "\n" + //
                indentText(2, "}}\n") + //;
                indentText(1, "\\end{itemize}\n");

        } else {

            latex = 
                "\\section{" + groupName + "}\n" + //
                indentText(1, "\\resumeSubHeadingListStart\n") + //
                indentText(2, itemsAppendedLatex) + "\n" + //
                indentText(1, "\\resumeSubHeadingListEnd\n");

        }

        return latex;
        
    }

}