import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LatexCompiler {
    
    private final String pathToCompiler = "tools/TinyTeX/bin/windows/pdflatex.exe";
    private final String resumeFolder = "resume/";
    private final String outputFolder = resumeFolder + "output/";
    private final ProcessBuilder pb = new ProcessBuilder();

    public String compile(String fileName) throws Exception {

        // Creates outputFolder
        Files.createDirectories( Paths.get(outputFolder) );

        String outputFlag = "-output-directory=" + outputFolder;
        String interactionFlag = "-interaction=nonstopmode"; // forces TinyLaTeX to crash on errors
        
        String logOutput;
        pb.redirectErrorStream(true);
        pb.command(pathToCompiler, outputFlag, interactionFlag, resumeFolder + fileName);
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

        return outputFolder + nameWithoutExtension + ".pdf";

    }
    
}