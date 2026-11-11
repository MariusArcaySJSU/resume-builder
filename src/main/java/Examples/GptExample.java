package Examples;

import java.util.Scanner;
import ChatGptService.ChatGptService;

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
