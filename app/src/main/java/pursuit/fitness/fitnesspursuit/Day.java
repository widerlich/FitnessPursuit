package pursuit.fitness.fitnesspursuit;

/**
 * Created by a17268192 on 10/04/2018.
 */

public class Day {

    private String name_day;
    private String link_day;


    public Day(String name, String link){
        this.name_day = name;
        this.link_day = link;

    }

    public static final Day[] days = {

            new Day("Monday",
                    "Go for it !"),

            new Day("Tuesday",
                    "Just do it !"),

            new Day("Wednesday",
                    "Don't let your dream be dream !"),

            new Day("Thursday",
                    "Nothing is impossible !"),

            new Day("Friday",
                    "You can do it !"),

            new Day("Saturday",
                    "Just do it !"),

            new Day("Sunday",
                    "Go for it !"),

    };

    public String getName() {
        return name_day;
    }
    public String getLink() {
        return link_day;
    }
}
