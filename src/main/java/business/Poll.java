package business;


import java.util.List;

public class Poll {
    private String name;
    private String question;
    private String status;
    private List<Choice> choices;

    public Poll(String name, String question, String status, List<Choice> choices) {
        this.name = name;
        this.question = question;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
  
  
      // stuff to delete START

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
      
      
          public void set_status_to_created() {
        this.status = "CREATED";
//        System.out.println("status changed to created");
    }

    public void set_status_to_released() {
        this.status = "RELEASED";
//        System.out.println("status changed to created");
    }

    public void set_status_to_null() {
        this.status = null;
    }
    
      // stuff to delete END 
}
