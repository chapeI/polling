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
}
