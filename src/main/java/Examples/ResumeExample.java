package Examples;

import java.time.LocalDate;

import Resume.Resume;
import Resume.ResumeItems.Education;
import Resume.ResumeItems.EducationGroup;
import Resume.ResumeItems.Experience;
import Resume.ResumeItems.ExperienceGroup;
import Resume.ResumeItems.Header;
import Resume.ResumeItems.Link;
import Resume.ResumeItems.Project;
import Resume.ResumeItems.ProjectGroup;
import Resume.ResumeItems.TechnicalSkills;
import Resume.ResumeItems.TechnicalSkillsGroup;

public class ResumeExample {

    public static void resumeExample() throws Exception{

        Header headerItem = new Header(
            "Marius Arcay",
            "408-607-2800",
            "marius@arcay.net",
            new Link[] {
                new Link("https://linkedin.com", "LinkedIn"),
                new Link("https://netlify.app", "Portfolio Website"),
                new Link("https://github.com", "GitHub"),
                new Link("https://artstation.com", "ArtStation")
            }
        );

        Education sjsuEducation = new Education(
            "San Jose State University",
            new String[] { "Bachelor of Science in Computer Science" },
            "San Jose, CA",
            LocalDate.of(2026, 1, 1),
            LocalDate.of(2028, 5, 1)
        );

        Education wvcPhysicsEducation = new Education(
            "West Valley College",
            new String[] { "Associate of Science in Physics" },
            "Saratoga, CA",
            LocalDate.of(2026, 1, 1),
            LocalDate.of(2028, 5, 1)
        );

        Education wvcMathEducation = new Education(
            "West Valley College",
            new String[] { "Associate of Science in Mathematics" },
            "Saratoga, CA",
            LocalDate.of(2022, 8, 1),
            LocalDate.of(2026, 1, 1)
        );

        Experience valveExperience = new Experience(
            "Contract 3D Artist",
            "Valve Corporation",
            new String[] {
                "Authored Rooftop, an official Counter-Strike 2 multiplayer map using Valve’s proprietary technology, balancing visual fidelity, gameplay, and performance",
                "Built, profiled and optimized scene geometry, materials, lighting, and assets to meet performance standards expected of an official Counter-Strike 2 map",
                "Shipped to a global audience of millions of players"
            },
            "Remote",
            LocalDate.of(2025, 10, 1),
            LocalDate.of(2026, 1, 1)
        );

        Experience targetExperience = new Experience(
            "Retail Tech Consultant",
            "Target",
            new String[] {
                "Provided personalized expertise to help guests select, understand, and purchase electronics"
            },
            "Morgan Hill, CA",
            LocalDate.of(2021, 6, 1),
            LocalDate.of(9999, 1, 1)
        );

        Project cudaProject = new Project(
            "CUDA Fractal Renderer",
            new String[] { "C++", "CUDA", "Multi-threading", "Git", "Visual Studio" },
            new String[] {
                "Built a CUDA fractal renderer achieving a 100× GPU speedup over single-threaded CPU benchmarks",
                "Developed a benchmarking system comparing CPU/GPU speeds with dynamic iteration scaling and animation rendering",
                "Automated build setups to check for CUDA support and drop unneeded dependencies"
            },
            LocalDate.of(2021, 5, 1),
            LocalDate.of(2022, 4, 5),
            false
        );

        Project openglProject = new Project(
            "OpenGL Path Tracing Engine",
            new String[] { "C++", "OpenGL", "GLSL", "Git", "Visual Studio" },
            new String[] {
                "Built a GPU path tracer using OpenGL/GLSL with Monte Carlo sampling and Cook-Torrance GGX PBR maps",
                "Implemented a depth prepass to reduce overdraw alongside texture pooling and bounding-volume intersection tests",
                "Developed interactive rendering infrastructure featuring a translation gizmo, material editor, and debug modes"
            },
            LocalDate.of(2025, 1, 1),
            LocalDate.of(2025, 6, 1),
            false
        );

        Project graphicsLibProject = new Project(
            "3D Graphics Library",
            new String[] { "C++", "Multi-threading", "Visual Studio" },
            new String[] {
                "Built a multi-threaded CPU 3D renderer in C++ featuring a model conversion pipeline and scene configuration"
            },
            LocalDate.of(2024, 6, 1),
            LocalDate.of(2024, 12, 1),
            false
        );

        Project envMonitorProject = new Project(
            "Environment Monitoring Device",
            new String[] { "C", "MySQL", "I2C" },
            new String[] {
                "Developed a Raspberry Pi device in C using I2C protocols to collect climate metrics and log data to MySQL"
            },
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 5, 1),
            false
        );

        Project gameServerProject = new Project(
            "Game Server Infrastructure",
            new String[] { "Python", "Linux", "OCI" },
            new String[] {
                "Hosted Counter-Strike game servers on Oracle Cloud Infrastructure (OCI) Linux virtual machines",
                "Wrote a Python Discord bot using a game query library to keep track of server status and live player activity"
            },
            LocalDate.of(2023, 6, 1),
            LocalDate.of(2023, 12, 1),
            false
        );

        TechnicalSkills languagesItem = new TechnicalSkills("Languages", new String[]{"C++", "C", "GLSL", "Python", "Java", "Assembly (ARM)"});
        TechnicalSkills graphicsItem = new TechnicalSkills("Graphics and GPU", new String[]{"OpenGL", "CUDA", "PBR", "Monte Carlo Rendering"});
        TechnicalSkills systemsItem = new TechnicalSkills("Systems and Cloud", new String[]{"Linux (CLI/Bash)", "Oracle Cloud Infrastructure (OCI)", "Multithreading", "I2C", "Windows"});
        TechnicalSkills librariesItem = new TechnicalSkills("Libraries and Tools", new String[]{"OpenCV", "Raylib", "Git", "Visual Studio", "VS Code"});

        EducationGroup educationGroup = new EducationGroup(new Education[] { sjsuEducation, wvcPhysicsEducation, wvcMathEducation });
        ExperienceGroup experienceGroup = new ExperienceGroup(new Experience[] { valveExperience, targetExperience });
        ProjectGroup projectGroup = new ProjectGroup(new Project[] { cudaProject, openglProject, graphicsLibProject, envMonitorProject, gameServerProject });
        TechnicalSkillsGroup technicalSkillsGroup = new TechnicalSkillsGroup(new TechnicalSkills[] { languagesItem, graphicsItem, systemsItem, librariesItem });


        Resume resume = new Resume(headerItem, educationGroup, experienceGroup, projectGroup, technicalSkillsGroup);
        resume.export("resume");

    }

}