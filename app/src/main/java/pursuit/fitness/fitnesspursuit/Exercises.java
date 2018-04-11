package pursuit.fitness.fitnesspursuit;

/**
 * Created by a17268192 on 10/04/2018.
 */

public class Exercises {
    private String name_exercises;
    private String description_exercises;
    private String link_exercises;
    private int imageResourceId_exercises;


    //here need to do the request for pull the exercises from the database



    public Exercises(String name, String description, String link, int imageResourceId){

        this.name_exercises = name;
        this.description_exercises = description;
        this.link_exercises = link;
        this.imageResourceId_exercises= imageResourceId;


    }

    public String getName() {
        return name_exercises;
    }

    public String getDescription() {
        return description_exercises;
    }

    public String getLink() {
        return link_exercises;
    }

    public int getImageResourceId() {return imageResourceId_exercises;
    }
}
