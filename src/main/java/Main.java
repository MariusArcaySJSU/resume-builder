import java.util.Scanner;

public class Main {
    
    public static void main(String args[]) throws Exception {

        latexExample();

    }

    public static void gptExample() throws Exception {

        Scanner scnr = new Scanner(System.in);
        ChatGptService gpt = new ChatGptService();
        
        System.out.printf("Send Message: ");
        String message = scnr.nextLine();

        System.out.println("ChatGPT: " + gpt.sendPrompt(message) );

    }

    public static void latexExample() throws Exception {

        LatexCompiler latexCompiler = new LatexCompiler();

        String outputFile = latexCompiler.compile("marius_resume.tex");
        System.out.println("Latex Succesfully Compiled: " + outputFile);

    }

}
