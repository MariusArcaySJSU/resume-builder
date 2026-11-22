package App.Controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;

import App.Model.Manifesto.Manifesto;
import App.Model.Resume.Resume;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Controller {
    
    public void generateResume(Manifesto manifesto, String jobDescription, Boolean openWhenDone) {

        String pdfOutput;

        try {

            Resume resume = manifesto.generateResume(); // Generate just manifesto so you can check validity before sending to chatgpt

            if (!resume.isValid()) {
                createAlert("Manifesto incomplete!", "Please continue filling out the manifesto before generating");
                return;
            }

            resume = manifesto.generateResumeFromJobDescription(jobDescription);

            pdfOutput = resume.compile();


        } catch (Exception e) {
            System.out.println("Error compiling resume: " + e.getMessage() + ".pdf");
            e.printStackTrace();
            return;
        }

        if (openWhenDone) {
            Path absolutePdfPath = Path.of(pdfOutput).toAbsolutePath();
            openPdfInBrowser(absolutePdfPath);   
        }

    }

    public void generateManifesto(Manifesto manifesto, Boolean openWhenDone) {

        String pdfOutput;

        try {

            Resume resume = manifesto.generateResume();

            if (!resume.isValid()) {

                ArrayList<String> invalidItems = resume.getInvalid();
                String invalidItemsList = "\n";
                for (String item : invalidItems) {
                    invalidItemsList += item + "\n";
                }

                if (!invalidItemsList.isBlank()) invalidItemsList = invalidItemsList.substring(0, invalidItemsList.length() - 1);

                createAlert("Manifesto incomplete!", "Please continue filling out the following before generating:\n" + invalidItemsList);
                return;
            }

            pdfOutput = resume.compile();


        } catch (Exception e) {
            System.out.println("Error compiling resume: " + e.getMessage() + ".pdf");
            e.printStackTrace();
            return;
        }

        if (openWhenDone) {
            Path absolutePdfPath = Path.of(pdfOutput).toAbsolutePath();
            openPdfInBrowser(absolutePdfPath);   
        }
        
    }

    private void openPdfInBrowser(Path absolutePdfPath) {

        URI pdfURI = absolutePdfPath.toUri();

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

    private void createAlert(String header, String content) {
        
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();

    }

}
