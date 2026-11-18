package App.Model.Resume.ResumeItems;

public abstract class ResumeItemGroup extends ResumeItem {
    
    protected String groupName;
    protected boolean smallText = false;    // mainly used in TechnicalSkillsGroup
    public ResumeItem[] items;
    
    @Override
    public boolean isValid() {

        for (ResumeItem item : items) {
            if (!item.isValid()) return false;
        }

        return true;

    }

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

    @Override
    public String toJSON() {

        String itemsAppendedJSON = "";
        for (ResumeItem item : items) {
            itemsAppendedJSON += item.toJSON() + ",\n";
        }

        if (!itemsAppendedJSON.isBlank()) {
            itemsAppendedJSON = itemsAppendedJSON.substring(0, itemsAppendedJSON.length() - 2) + "\n";
        }
        
        String JSON = 
                "\"" + groupName.toLowerCase().replace(" ", "_") + "\": [\n" + //
                indentText(1, itemsAppendedJSON) + "\n" + //
                "]";
        
        return JSON;
    }

}