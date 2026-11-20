package App.Model.Manifesto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import App.Model.ChatGptService.ChatGptService;
import App.Model.Resume.Resume;
import App.Model.Resume.ResumeItems.ResumeItem;
import App.Model.Resume.ResumeParser;

public class Manifesto {
    
    private final Path manifestoPath;

    private static final ResumeParser resumeParser = new ResumeParser();

    private static final ChatGptService gpt = new ChatGptService();
    private static final String manifestoFolder = "resume/manifestos/";
    private static final String tailorManifesto_SystemPromptFile = "resume/resources/tailor_manifesto_system_prompt.txt";
    
    public Manifesto(String fileName) {
        manifestoPath = Path.of(manifestoFolder + fileName + ".json");
    }

    public Manifesto(Resume resume) throws IOException {

        String bodyJSON = "";
        for (ResumeItem item : resume.items) {
            bodyJSON += item.toJSON() + ",\n";
        }

        if (!bodyJSON.isBlank()) {
            bodyJSON = bodyJSON.substring(0, bodyJSON.length() - 2) + "\n";
        }

        String JSON = 
                "{\n" + //
                ResumeItem.indentText(1, bodyJSON) + //
        "}";

        generateManifestoFile(resume.getSanitizedCanidateName(), JSON);
        manifestoPath = Path.of(manifestoFolder + resume.getSanitizedCanidateName() + ".json");

    }

    public static String generateManifestoFile(String canidateFullName, String JSON) throws IOException {

        Path manifestoFile = Path.of(manifestoFolder + canidateFullName + ".json");
        Files.writeString(manifestoFile, JSON);
        System.out.println("Succesfully generated manifesto File: " + manifestoFile.toString().replace("\\", "/"));

        return manifestoFile.toString().replace("\\", "/");

    }
    
    public Resume generateResume() throws Exception {

        assert (manifestoPath != null);
        String JSON = Files.readString(manifestoPath);
        return resumeParser.generateResume(JSON);

    }

    public Resume generateResumeFromJobDescription(String jobDescription) throws Exception {

        assert (manifestoPath != null);
        String tailoredJSON = tailorManifesto(jobDescription);        
        return resumeParser.generateResume(tailoredJSON);

    }

    private String tailorManifesto(String jobDescription) throws Exception {
        
        String manifestoJSON = Files.readString(manifestoPath);
        String result = gpt.sendPrompt(getSystemPrompt(tailorManifesto_SystemPromptFile) + "\nTARGETED JOB DESCRIPTION:\n" + jobDescription + "\nRESUME MANIFESTO:\n" + manifestoJSON);
        return result;
    }

    private String getSystemPrompt(String systemPromptFile) throws IOException {

        Path tailorManifesto_SystemPromptFileObject = Path.of(systemPromptFile);
        String systemPrompt = Files.readString(tailorManifesto_SystemPromptFileObject);
        System.out.println("Succesfully read manifesto system prompt: " + tailorManifesto_SystemPromptFileObject.toString().replace("\\", "/"));

        return systemPrompt;

    }

}
