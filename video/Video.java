package video;

public class Video {
    public static final Video NULL_VIDEO;
    static {
        NULL_VIDEO = new Video("", "", 0, 0, 0);
    }

    private String title;
    private String uploader;
    private int views;
    private int likes;
    private int dislikes;

    public Video(String name, String uploader, int views, int likes, int dislikes) {
        this.title = name;
        this.uploader = uploader;
        this.views = views;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public static Video getInstanceFromString(String details) {
        String[] detailArray = details.split("\\\\");

        try {
            String title = detailArray[0].trim();
            String uploader = detailArray[1].trim();
            int views = Integer.parseInt(detailArray[2].trim());
            int likes = Integer.parseInt(detailArray[3].trim());
            int dislikes = Integer.parseInt(detailArray[4].trim());

            return new Video(title, uploader, views, likes, dislikes);
        } catch (Exception e) {
            return null;
        }
    }

    public String toFormattedString() {
        return String.format("%s\\%s\\%d\\%d\\%d", title, uploader, views, likes, dislikes);
    }

    public void setTitle(String s) {title = s;}
    public void setUploader(String s) {uploader = s;}
    public void setViews(int n) {views = n;}
    public void setLikes(int n) {likes = n;}
    public void setDislikes(int n) {dislikes = n;}

    public String getTitle() {return title;}
    public String getUploader() {return uploader;}
    public int getViews() {return views;}
    public int getLikes() {return likes;}
    public int getDislikes() {return dislikes;}

    public String toString() {return title;}
}