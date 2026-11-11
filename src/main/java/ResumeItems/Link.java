package ResumeItems;
public class Link {
    
    public String anchorText;
    public String destination;

    public Link(String destination) {
        this.anchorText = destination;
        this.destination = destination;
    }

    public Link(String destination, String anchorText) {
        this.anchorText = anchorText;
        this.destination = destination;
    }
}
