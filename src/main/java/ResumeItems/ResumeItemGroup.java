package ResumeItems;

public abstract class ResumeItemGroup extends ResumeItem {
    
    protected String groupName;
    public ResumeItem[] items;

    @Override
    public String toLatex() {

        String itemsAppendedLatex = "";
        for (ResumeItem item : items) {
            itemsAppendedLatex += indentText(2, item.toLatex());
        }

        String latex = 
            "\\section{" + groupName + "}\n" + //
            indentText(1, "\\resumeSubHeadingListStart\n") + //
            indentText(2, itemsAppendedLatex) + //
            indentText(1, "\\resumeSubHeadingListEnd");

        return latex;
        
    }

}