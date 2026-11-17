package Examples;

import Manifesto.Manifesto;
import Resume.Resume;

public class ManifestoExample {
    
    public static void manifestoExample() throws Exception {

        Manifesto manifesto = new Manifesto("marius_manifesto.json");
        Resume resume = manifesto.generateResume();
        resume.export("resume");

    }
}
