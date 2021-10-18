package business;

import java.util.ArrayList;
import java.util.List;

public class Poll {
    private String name;
    private String question;
    private List<Choice> choices;

    public Poll() {
    }

    public Poll(String name, String question, List<String> choicesList, List<String> descriptionsList) {
        this.name = name;
        this.question = question;
        choices = new ArrayList<>();
        for (int i =0; i<choicesList.size(); i++){
            choices.add(new Choice(choicesList.get(i), descriptionsList.get(i)));
        }
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

    public void setChoices(List<String> choicesList, List<String> descriptionsList) {
        choices.clear();
        for (int i =0; i<choicesList.size(); i++){
            choices.add(new Choice(choicesList.get(i), descriptionsList.get(i)));
        }
    }

    public void addChoices(List<String> choicesList, List<String> descriptionsList) {
        for (int i =0; i<choicesList.size(); i++){
            choices.add(new Choice(choicesList.get(i), descriptionsList.get(i)));
        }
    }
}
