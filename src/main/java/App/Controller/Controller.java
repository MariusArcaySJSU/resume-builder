package App.Controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;

import App.Model.Manifesto.Manifesto;
import App.Model.Resume.Resume;
import App.View.GenerateTabFX;
import App.View.ManifestoTabFX;
import App.View.ToolBarFX;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

public class Controller {

    private final BorderPane root;
    private ToolBarFX toolBar;
    private ManifestoTabFX manifestoTab;
    private GenerateTabFX generateTab;

    private ScrollPane manifestoScrollPane;

    public Controller(BorderPane root, ToolBarFX toolBar, ManifestoTabFX manifestoTab, GenerateTabFX generateTab, ScrollPane manifestoScrollPane) {
        this.root = root;
        this.toolBar = toolBar;
        this.manifestoTab = manifestoTab;
        this.generateTab = generateTab;
        this.manifestoScrollPane = manifestoScrollPane;

        generateTab.setOnGenerateManifesto( event -> {
                try {
                    generateManifesto(
                        manifestoTab.generateManifesto(), 
                        generateTab.getOpenWhenDone()
                    ); 
                } catch (Exception e) {
                    System.out.println("Error generating manifesto:" + e.getMessage());
                    e.printStackTrace();
                }
            }
        );

        generateTab.setOnGenerateResume( event -> {
                try {
                    generateResume(
                        manifestoTab.generateManifesto(), 
                        generateTab.getJobDescription(), 
                        generateTab.getOpenWhenDone()
                    ); 
                } catch (Exception e) {
                    System.out.println("Error generating manifesto: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        );
    
    }

    public void generateResume(Manifesto manifesto, String jobDescription, Boolean openWhenDone) {

        String pdfOutput;

        try {

            Resume resume = manifesto.generateResume(); // Generate just manifesto so you can check validity before sending to chatgpt

            if (!resume.isValid()) {
                ToolBarFX.createAlert("Manifesto incomplete!", "Please continue filling out the manifesto before generating");
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

                ToolBarFX.createAlert("Manifesto incomplete!", "Please continue filling out the following before generating:\n" + invalidItemsList);
                return;
            }

            toolBar.saveManifesto();
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

}
