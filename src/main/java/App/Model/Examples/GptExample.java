package App.Model.Examples;

import java.util.Scanner;

import App.Model.ChatGptService.ChatGptService;

public class GptExample {
    
    public static void gptExample() throws Exception {

        Scanner scnr = new Scanner(System.in);
        ChatGptService gpt = new ChatGptService();
        
        System.out.printf("Send Message: ");
        String message = scnr.nextLine();

        System.out.println("ChatGPT: " + gpt.sendPrompt(message) );

        scnr.close();

    }

}
