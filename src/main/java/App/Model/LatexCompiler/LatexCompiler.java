package App.Model.LatexCompiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class LatexCompiler {
    
    private static final String pathToCompiler = "tools/TinyTeX/bin/windows/pdflatex.exe";
    private static final String resumeFolder = "resume/";
    private static final String outputFolder = resumeFolder + "output/";
    private static final ProcessBuilder pb = new ProcessBuilder();

    public String saveLatexFile(String sanitizedCanidateName, String latex) throws IOException {

        Path latexFile = Path.of(outputFolder + sanitizedCanidateName + ".tex");
        Files.writeString(latexFile, latex);
        System.out.println("Succesfully generated Latex File: " + latexFile.toString().replace("\\", "/"));

        return latexFile.toString().replace("\\", "/");
        
    }

    public String compile(String sanitizedCanidateName) throws Exception {

        String outputFlag = "-output-directory=" + outputFolder;
        String interactionFlag = "-interaction=nonstopmode"; // forces TinyLaTeX to crash on errors
        
        String logOutput;
        pb.redirectErrorStream(true);
        pb.command(pathToCompiler, outputFlag, interactionFlag, outputFolder + sanitizedCanidateName + ".tex");
        Process p = pb.start();

        // Read and capture the console output text
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            logOutput = reader.lines().collect(Collectors.joining("\n"));
        }

        int exitCode = p.waitFor(); // some errors still generate .pdf, so we don't use error code to determine the compile failed

        if (!Files.exists(Path.of(outputFolder + sanitizedCanidateName + ".pdf"))) {
            System.out.println("Latex Compilation failed:");
            throw new Exception(logOutput);
        }

        System.out.println("Succesfully compiled Latex File: " + outputFolder + sanitizedCanidateName + ".pdf");

        return outputFolder + sanitizedCanidateName + ".pdf";

    }
    
}