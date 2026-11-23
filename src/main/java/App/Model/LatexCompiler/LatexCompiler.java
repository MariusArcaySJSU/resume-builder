package App.Model.LatexCompiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LatexCompiler {
    
    private static final String resumeFolder = "resume/";
    private static final String outputFolder = resumeFolder + "output/";
    private static final ProcessBuilder pb = new ProcessBuilder();
    
    private static List<String> compilerCommand() {
        String osName = System.getProperty("os.name").toLowerCase();
        List<String> command = new ArrayList<>();

        if (osName.contains("win")) {
            command.add("tools/TinyTeX/bin/windows/pdflatex.exe");
        } else if (osName.contains("mac")) {
            Path userTinyTex = Path.of(System.getProperty("user.home"), "Library", "TinyTeX",
                    "bin", "universal-darwin", "pdflatex");
            if (isUsableExecutable(userTinyTex)) {
                command.add(userTinyTex.toString());
                return command;
            }

            Path bundledPdflatex = Path.of("tools/TinyTeX/bin/universal-darwin/pdflatex");
            if (isUsableExecutable(bundledPdflatex)) {
                command.add(bundledPdflatex.toString());
                return command;
            }

            
            Path bundledPdftex = Path.of("tools/TinyTeX/bin/universal-darwin/pdftex");
            if (isUsableExecutable(bundledPdftex)) {
                command.add(bundledPdftex.toString());
                command.add("-fmt=pdflatex");
                return command;
            }
        } else {
            command.add("tools/TinyTeX/bin/x86_64-linux/pdflatex");
        }

        if (isUsableExecutable(Path.of(command.get(0)))) {
            return command;
        }

        command.clear();
        command.add("pdflatex");
        return command;
    }

    private static boolean isUsableExecutable(Path path) {
        try {
            return Files.isExecutable(path) && Files.size(path) > 100;
        } catch (IOException exception) {
            return false;
        }
    }

    public String saveLatexFile(String sanitizedCanidateName, String latex) throws IOException {

        // Generates output folder if doesn't exists
        if (!Files.isDirectory(Path.of(outputFolder))) {
            Files.createDirectories(Path.of(outputFolder));
        }

        Path latexFile = Path.of(outputFolder + sanitizedCanidateName + ".tex");
        Files.writeString(latexFile, latex);
        System.out.println("Succesfully saved Latex File: " + latexFile.toString().replace("\\", "/"));

        return latexFile.toString().replace("\\", "/");
        
    }

    public String compile(String sanitizedCanidateName) throws Exception {

        String outputFlag = "-output-directory=" + outputFolder;
        String interactionFlag = "-interaction=nonstopmode"; // forces TinyLaTeX to crash on errors
        
        String logOutput;
        pb.redirectErrorStream(true);
        List<String> command = compilerCommand();
        command.add(outputFlag);
        command.add(interactionFlag);
        command.add(outputFolder + sanitizedCanidateName + ".tex");
        pb.command(command);
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
