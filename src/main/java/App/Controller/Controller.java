package App.Controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;

import App.Model.Manifesto.Manifesto;

public class Controller {
    
    public void generateResume(Manifesto manifesto, String jobDescription, Boolean openWhenDone) {

        String pdfOutput;

        try {
            pdfOutput = manifesto.generateResume().compile();
        } catch (Exception e) {
            System.out.println("Error compiling resume: " + e.getMessage() + ".pdf");
            e.printStackTrace();
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
