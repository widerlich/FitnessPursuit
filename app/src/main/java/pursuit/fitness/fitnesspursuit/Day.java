package pursuit.fitness.fitnesspursuit;

/**
 * Created by a17268192 on 10/04/2018.
 */

public class Day {

    private String name;
    private String link;
    private int imageResourceId;


    public Day(String name, String link, int imageResourceId){
        this.name = name;
        this.link = link;
        this.imageResourceId = imageResourceId;

    }

    public static final Day[] days = {

            new Day("Monday",
                    "Go for it !",
                    R.drawable.monday),

            new Day("Tuesday",
                    "Go for it !",
                    R.drawable.tuesday),

            new Day("Wednesday",
                    "Go for it !",
                    R.drawable.wednesday),

            new Day("Thursday",
                    "Go for it !",
                    R.drawable.thursday),

            new Day("Friday",
                    "Go for it !",
                    R.drawable.friday),

            new Day("Saturday",
                    "Go for it !",
                    R.drawable.saturday),

            new Day("Sunday",
                    "Go for it !",
                    R.drawable.sunday),

    };

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getLink() {
        return link;
    }
}
