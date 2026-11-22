package App.Model.Manifesto;

import java.lang.Exception;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import App.Model.ChatGptService.ChatGptService;
import App.Model.Resume.Resume;
import App.Model.Resume.ResumeItems.ResumeItem;
import App.Model.Resume.ResumeParser;

public class Manifesto {
    
    private final String sanitizedCanidateName;
    private final String JSON;

    private static final ResumeParser resumeParser = new ResumeParser();

    private static final ChatGptService gpt = new ChatGptService();
    private static final String manifestoFolder = "resume/manifestos/";
    private static final String tailorManifesto_SystemPromptFile = "resume/resources/tailor_manifesto_system_prompt.txt";
    
    public Manifesto(String canidateName) throws Exception {
        
        this.sanitizedCanidateName = canidateName.replace(" ", "_").toLowerCase().trim();
        JSON = Files.readString(Path.of(manifestoFolder + sanitizedCanidateName + ".json"));

    }

    public Manifesto(Resume resume) {

        sanitizedCanidateName = resume.getSanitizedCanidateName();

        ArrayList<ResumeItem> items = resume.getItems();

        String bodyJSON = "";
        for (ResumeItem item : items) {
            bodyJSON += item.toJSON() + ",\n";
        }

        if (!bodyJSON.isBlank()) {
            bodyJSON = bodyJSON.substring(0, bodyJSON.length() - 2) + "\n";
        }

        JSON = "{\n" + ResumeItem.indentText(1, bodyJSON) + "}";

    }

    public String saveManifestoFile() throws Exception {

        if (sanitizedCanidateName.trim().equals("")) throw new Exception("Cannot save empty file");

        Path manifestoFile = Path.of(manifestoFolder + sanitizedCanidateName + ".json");
        Files.writeString(manifestoFile, JSON);
        System.out.println("Succesfully generated manifesto File: " + manifestoFile.toString().replace("\\", "/"));

        return manifestoFile.toString().replace("\\", "/");

    }


    public Resume generateResume() throws Exception {

        return resumeParser.generateResume(JSON);

    }

    public Resume generateResumeFromJobDescription(String jobDescription) throws Exception {

        String tailoredJSON = tailorManifesto(jobDescription);        
        return resumeParser.generateResume(tailoredJSON);

    }

    private String tailorManifesto(String jobDescription) throws Exception {
        
        String result = gpt.sendPrompt(getSystemPrompt(tailorManifesto_SystemPromptFile) + "\nTARGETED JOB DESCRIPTION:\n" + jobDescription + "\nRESUME MANIFESTO:\n" + JSON);
        return result;
    }

    private String getSystemPrompt(String systemPromptFile) throws IOException {

        Path tailorManifesto_SystemPromptFileObject = Path.of(systemPromptFile);
        String systemPrompt = Files.readString(tailorManifesto_SystemPromptFileObject);
        System.out.println("Succesfully read manifesto system prompt: " + tailorManifesto_SystemPromptFileObject.toString().replace("\\", "/"));

        return systemPrompt;

    }

    public static ArrayList<String> listAllManifestoFiles() throws Exception {

        ArrayList<String> manifestos = new ArrayList<>();

        File outputFolderAsFile = new File(manifestoFolder);
        File[] files = outputFolderAsFile.listFiles(file -> 
            file.isFile() && file.getName().toLowerCase().endsWith(".json"));
        
        for (File f : files) {
            manifestos.add(f.getName());
        }

        return manifestos;

    }

}
