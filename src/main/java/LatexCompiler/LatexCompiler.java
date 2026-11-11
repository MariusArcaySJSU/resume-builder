package LatexCompiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class LatexCompiler {
    
    private final String pathToCompiler = "tools/TinyTeX/bin/windows/pdflatex.exe";
    private final String resumeFolder = "resume/";
    private final String outputFolder = resumeFolder + "output/";
    private final ProcessBuilder pb = new ProcessBuilder();

    public String generateLatexFile(String fileName, String latex) throws IOException {

        Path latexFile = Path.of(outputFolder + fileName);
        Files.writeString(latexFile, latex);
        System.out.println("Succesfully generated Latex File: " + latexFile.toString().replace("\\", "/"));

        return latexFile.toString().replace("\\", "/");
        
    }

    public String compile(String fileName) throws Exception {

        String outputFlag = "-output-directory=" + outputFolder;
        String interactionFlag = "-interaction=nonstopmode"; // forces TinyLaTeX to crash on errors
        
        String logOutput;
        pb.redirectErrorStream(true);
        pb.command(pathToCompiler, outputFlag, interactionFlag, outputFolder + fileName);
        Process p = pb.start();

        // Read and capture the console output text
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            logOutput = reader.lines().collect(Collectors.joining("\n"));
        }

        int exitCode = p.waitFor();

        if (exitCode != 0) {
            System.out.println("Latex Compilation failed:");
            throw new Exception(logOutput);
        }


        int lastDot = fileName.lastIndexOf(".");
        String nameWithoutExtension = (lastDot == -1) ? fileName : fileName.substring(0, lastDot);

        System.out.println("Succesfully compiled Latex File: " + outputFolder + nameWithoutExtension + ".pdf");

        return outputFolder + nameWithoutExtension + ".pdf";

    }
    
}