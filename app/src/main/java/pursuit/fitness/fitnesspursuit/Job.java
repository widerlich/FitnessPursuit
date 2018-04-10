package pursuit.fitness.fitnesspursuit;

/**
 * Created by a17268192 on 10/04/2018.
 */

public class Job {
    private String name;
    private String description;
    private String link;
    private int imageResourceId;


    //here need to do the request for pull the exercises from the database



    public Job(String name, String description, String link, int imageResourceId){

        this.name = name;
        this.description = description;
        this.link = link;
        this.imageResourceId= imageResourceId;


    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
