package chanh.finalproject;

// Custom ListView Data
public class MyData {

    private String date;
    private String time;
    private String position;
    private double pointX;
    private double pointY;
    private String bigCategory;
    private String smallCategory;
    private String story;

    public MyData(String d, String t, String p, double pX, double pY, String bC, String sC, String s) {
        date = d;
        time = t;
        position = p;
        pointX = pX;
        pointY = pY;
        bigCategory = bC;
        smallCategory = sC;
        story = s;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPosition() {
        return position;
    }

    public double getPointX() {
        return pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public String getBigCategory() {
        return bigCategory;
    }

    public String getSmallCategory() {
        return smallCategory;
    }

    public String getStory() {
        return story;
    }
}
