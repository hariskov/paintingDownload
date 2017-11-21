/**
 * Created by xumepa on 11/21/17.
 */

public class Painting {

    private String paintingAuthor;
    private String paintingName;
    private String paintingUrl;
    private String type;
    private String bornDied;


    public String getPaintingAuthor() {
        return paintingAuthor;
    }

    public void setPaintingAuthor(String paintingAuthor) {
        this.paintingAuthor = paintingAuthor;
    }

    public String getPaintingName() {
        return paintingName;
    }

    public void setPaintingName(String paintingName) {
        this.paintingName = paintingName;
    }

    public String getPaintingUrl() {
        return paintingUrl;
    }

    public void setPaintingUrl(String paintingUrl) {
        this.paintingUrl = paintingUrl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setBornDied(String bornDied) {
        this.bornDied = bornDied;
    }

    public String getBornDied() {
        return bornDied;
    }
}
