package Examples;

import Manifesto.Manifesto;
import Resume.Resume;

public class ManifestoExample {
    
    public static void manifestoExample() throws Exception {

        Manifesto m = new Manifesto("marius_manifesto.json");

        Resume r = m.generateFromJobDescription("");
        r.export("resume");

        // m.generateFromJobDescription(jobDescription);
    }
}
