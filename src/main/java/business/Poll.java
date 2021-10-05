package business;

import java.util.ArrayList;

public class Poll {
    String name;
    String question;
    ArrayList<String> choice = new ArrayList<String>();  // Hashmap<Choice, int>
    private String status = null;

    int red_count = 0;
    int blue_count = 0;

    Poll(String n, String q) {
        super();
        this.name = n;
        this.question = q;
    }

    public void setFakePoll() {
        this.name ="Favorite Color";
        question = "pick a color";
        choice.add("blue");
        choice.add("red");
    }

    public void set_status_to_running() {
        this.status = "RUNNING";
    }

    public void increment(int x) {
        if(x == 1) {
            this.red_count += 1;
        }
        else if (x == 2) {
            this.blue_count += 1;
        }
    }

    public int show_red_count() {
        System.out.println("red count: " + red_count);
        return this.red_count;
    }

    public String get_status() {
        return this.status;
    }

    public void set_status_to_created() {
        this.status = "CREATED";
//        System.out.println("status changed to created");
    }

    public void set_status_to_released() {
        this.status = "RELEASED";
//        System.out.println("status changed to created");
    }



}
