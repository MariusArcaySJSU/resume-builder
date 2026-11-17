package Examples;

import Manifesto.Manifesto;
import Resume.Resume;

public class ManifestoExample {
    
    public static void manifestoExample() throws Exception {

        String jobDescription = "What We Are Looking For\r\n" + //
                        "\r\n" + //
                        "We're searching for Summer 2027 Simulation Engineering Interns. You'll work on the simulation systems that let Aurora develop and validate the Driver without driving every mile — sensor simulation (rendering, path tracing, USD scene representation) and/or behavioral simulation (actor and world modeling in closed loop).\r\n" + //
                        "\r\n" + //
                        "In this role you will\r\n" + //
                        "\r\n" + //
                        "Build or extend simulation, rendering, or physics-modeling components used to validate the Aurora Driver\r\n" + //
                        "Work in C++ (with Python for tooling) on performance-sensitive simulation code\r\n" + //
                        "Reason about 3D math and geometry (sensor sim) or vehicle dynamics and behavior modeling (behavioral sim)\r\n" + //
                        "Validate simulation output against measured or ground-truth data rather than relying on visual inspection alone\r\n" + //
                        "Profile and improve performance of simulation pipelines running at scale\r\n" + //
                        "\r\n" + //
                        "Required Qualifications\r\n" + //
                        "\r\n" + //
                        "Currently pursuing a Bachelor's or Master's degree in Computer Science, Computer Graphics, Physics, or a related field\r\n" + //
                        "Has built a simulator, renderer, physics engine, game engine, or robotics simulation not just used an existing one\r\n" + //
                        "Personal or research-project experience with C++\r\n" + //
                        "Depth in at least one relevant specialization (rendering/ray tracing, GPU programming, physically-based materials/optics, lidar/radar/signal processing, vehicle dynamics/controls, motion planning/agent behavior, real-time simulation architecture, 3D content pipelines, or ML for 3D)\r\n" + //
                        "Has verified a simulation or model's correctness systematically, not just by visual inspection\r\n" + //
                        "\r\n" + //
                        "Desirable Qualifications\r\n" + //
                        "\r\n" + //
                        "Prior internship or production-level C++ experience\r\n" + //
                        "Experience with GPU-accelerated raytracing or texturing\r\n" + //
                        "";



        Manifesto manifesto = new Manifesto("example_manifesto.json");
        //Resume resume = manifesto.generateResume();
        Resume resume = manifesto.generateResumeFromJobDescription(jobDescription);
        resume.export("resume");

    }
}
