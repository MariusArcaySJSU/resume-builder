package Manifesto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import Resume.*;
import Resume.ResumeItems.*;
import ChatGptService.*;

public class Manifesto {
    
    private final String manifestoFolder = "resume/";
    private String manifestoJSON;

    private ChatGptService gpt = new ChatGptService();
    private final String tailorManifesto_SystemPromptFile = "resume/tailor_manifesto_system_prompt.txt";

    public void read(String fileName) throws IOException {

        Path manifestoFile = Path.of(manifestoFolder + fileName);
        manifestoJSON = Files.readString(manifestoFile).replaceAll("[\\r\\n\\t]", "");
        System.out.println("Succesfully read manifesto: " + manifestoFile.toString().replace("\\", "/"));

    }

    public Resume generateFromJobDescription(String jobDescription) throws Exception {

        assert (manifestoJSON != null);

        String tailoredManifestoJSON = tailorManifesto(manifestoJSON);

        ArrayList<String> trailoredManifestoJSON_blocks = splitByOuterComma(tailoredManifestoJSON);

        Header header;
        for (String b : trailoredManifestoJSON_blocks) {

            String blockName = b.substring(0, b.indexOf("{"));

            switch (blockName) {
                case "HEADER":
                    header = JSONtoHeader(b);
                    break;
            }

        }

        return null;

    }

    private String tailorManifesto(String JSON) throws Exception {
        
        String result = gpt.sendPrompt(getSystemPrompt(tailorManifesto_SystemPromptFile) + JSON);
        
        System.out.println(result);

        return result;
    }

    private String getSystemPrompt(String systemPromptFile) throws IOException {

        Path tailorManifesto_SystemPromptFileObject = Path.of(systemPromptFile);
        String systemPrompt = Files.readString(tailorManifesto_SystemPromptFileObject);
        System.out.println("Succesfully read manifesto system prompt: " + tailorManifesto_SystemPromptFileObject.toString().replace("\\", "/"));

        return systemPrompt;

    }

    private Header JSONtoHeader(String headerJSON) {

        String headerJSON_removeHeader = headerJSON.substring(headerJSON.indexOf('{', 0) + 1, headerJSON.lastIndexOf('}') - 1);
        ArrayList<String> headerJSON_blocks = splitByOuterComma(headerJSON_removeHeader);

        String name = headerJSON_blocks.get(0).replaceAll("^\"|\"$", "");
        String phone = headerJSON_blocks.get(1).replaceAll("^\"|\"$", "");
        String email = headerJSON_blocks.get(2).replaceAll("^\"|\"$", "");
        ArrayList<Link> links = new ArrayList<>();

        // Parses links
        String linksJSON = headerJSON_blocks.get(3).replaceAll("^\"|\"$", "");
        ArrayList<String> linksJSON_blocks = splitByOuterComma(linksJSON);

        for (String s : linksJSON_blocks) {

            String[] sArray = s.split(",");

            String destination = sArray[0].replaceAll("^\"|\"$", "");
            String anchorText = (sArray.length > 1) ? sArray[1].replaceAll("^\"|\"$", "") : "";

            Link link;

            if (!anchorText.equals("")) {
                link = new Link(destination);
            } else {
                link = new Link(destination, anchorText);
            }

            links.add(link);

        }

        return new Header(name, phone, email, links.toArray(new Link[links.size()]));

    }

    private ArrayList<String> splitByOuterComma(String content) {

        ArrayList<String> result = new ArrayList<>();
        String temp = "";

        int blockDepth = 0;
        for (char c : content.toCharArray()) {

            if (c == '{') {
                ++blockDepth;
            } else if (c == '}') {
                --blockDepth;
            }

            if (c == ',' && blockDepth == 0) {
                result.add(temp.trim());
                temp = "";
                continue;
            }

            temp += c;
        }

        result.add(temp);

        return result;

    }

}
