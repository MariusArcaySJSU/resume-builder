package Examples;

import LatexCompiler.LatexCompiler;

public class LatexExample {
    
    public static void latexExample() throws Exception {

        LatexCompiler latexCompiler = new LatexCompiler();

        String outputFile = latexCompiler.compile("marius_resume.tex");
        System.out.println("Latex Succesfully Compiled: " + outputFile);

    }

}
