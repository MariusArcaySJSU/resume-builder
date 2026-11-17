package Resume.ResumeItems;
public class Link {
    
    public String url;
    public String label;

    public Link(String url) {
        this.url = url;
        this.label = url;
    }

    public Link(String url, String label) {
        this.url = url;
        this.label = label;
    }
}
