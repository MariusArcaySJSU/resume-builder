import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import ResumeItems.ResumeItem;

public class Resume {

    public ArrayList<ResumeItem> items = new ArrayList<>();
    private final String preambleFile = "resume/jakes_resume_preamble.tex";
    private final String preambleLatex;

    public Resume() throws IOException{
        Path filePath = Path.of(preambleFile);
        preambleLatex = Files.readString(filePath);
    }

    public void export() {

        String bodyLatex = "";

        for (ResumeItem item : items) {
            bodyLatex += item.toLatex() + "\n";
        }

        String latex = preambleLatex + "\n\\begin{document}" + bodyLatex + "\\end{document}";

        System.out.println(latex);

    }

}
