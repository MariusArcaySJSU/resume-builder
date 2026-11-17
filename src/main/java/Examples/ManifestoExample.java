package Examples;

import Manifesto.Manifesto;

public class ManifestoExample {
    
    public static void manifestoExample() throws Exception {
        Manifesto m = new Manifesto();
        m.read("marius_resume.manifesto");
        m.generateFromJobDescription("");
    }
}
