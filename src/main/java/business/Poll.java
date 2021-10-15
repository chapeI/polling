package business;

import java.util.ArrayList;
import java.util.List;

public class Poll {
    private String name;
    private String question;
    private List<Choice> choices;
    private Status status;

    public Poll() {
    }

    public Poll(String name, String question, List<Choice> choices) {
        this.name = name;
        this.question = question;
        this.choices = choices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    int red_count = 0;
    int blue_count = 0;

    Poll(String n, String q) {
        super();
        this.name = n;
        this.question = q;
    }

//    public void setFakePoll() {
//        this.name ="Favorite Color";
//        question = "pick a color";
//        choice.add("blue");
//        choice.add("red");
//    }

    public void set_status_to_running() {
        this.status = Status.running;
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


    public void set_status_to_created() {
        this.status = Status.created;
//        System.out.println("status changed to created");
    }

    public void set_status_to_released() {
        this.status = Status.released;
//        System.out.println("status changed to created");
    }

    public void set_status_to_null() {
        this.status = null;
    }



}
