package App.Controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;

import App.Model.Resume.Resume;

public class Controller {
    
    public void generateResume(Resume resume, String jobDescription, Boolean openWhenDone) {

        String pdfOutput;

        try {
            pdfOutput = resume.export();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        if (openWhenDone) {

            Path pdfPath = Path.of(pdfOutput).toAbsolutePath();
            URI pdfURI = pdfPath.toUri();

            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(pdfURI);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Cannot pull up output pdf: Desktop browsing is not supported on this system.");
            }
            
        }
        
    }

}
