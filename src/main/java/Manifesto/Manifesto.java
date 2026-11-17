package Manifesto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import ChatGptService.ChatGptService;
import Resume.Resume;
import Resume.ResumeParser;

public class Manifesto {
    
    private final ResumeParser resumeParser = new ResumeParser();

    private final String manifestoFolder = "resume/";
    private final Path manifestoPath;

    private final ChatGptService gpt = new ChatGptService();
    private final String tailorManifesto_SystemPromptFile = "resume/tailor_manifesto_system_prompt.txt";

    public Manifesto(String fileName) {
        manifestoPath = Path.of(manifestoFolder + fileName);
    }

    public Resume generateFromJobDescription(String jobDescription) throws Exception {

        assert (manifestoPath != null);

        //String tailoredJSON = tailorManifesto(jobDescription);
        String tailoredJSON = Files.readString(manifestoPath);
        
        return resumeParser.generateResume(tailoredJSON);

    }

    private String tailorManifesto(String jobDescription) throws Exception {
        
        String manifestoJSON = Files.readString(manifestoPath);
        String result = gpt.sendPrompt(getSystemPrompt(tailorManifesto_SystemPromptFile) + "\nTARGETED JOB DESCRIPTION:\n" + jobDescription + "\nRESUME MANIFESTO:\n" + manifestoJSON);
        
        System.out.println(result);

        return result;
    }

    private String getSystemPrompt(String systemPromptFile) throws IOException {

        Path tailorManifesto_SystemPromptFileObject = Path.of(systemPromptFile);
        String systemPrompt = Files.readString(tailorManifesto_SystemPromptFileObject);
        System.out.println("Succesfully read manifesto system prompt: " + tailorManifesto_SystemPromptFileObject.toString().replace("\\", "/"));

        return systemPrompt;

    }

}
