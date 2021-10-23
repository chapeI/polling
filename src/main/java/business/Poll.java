package business;

import java.util.ArrayList;
import java.util.List;

public class Poll {
    private String name;
    private String question;
    private List<Choice> choices;

    public Poll() {
    }

    /**
     * Default Parameterized Constructor
     */
    public Poll(String name, String question, List<String> choicesList, List<String> descriptionsList) {
        this.name = name;
        this.question = question;
        choices = new ArrayList<>();
        for (int i =0; i<choicesList.size(); i++){
            choices.add(new Choice(choicesList.get(i), descriptionsList.get(i)));
        }
    }
    /**
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return question
     */
    public String getQuestion() {
        return question;
    }
    /**
     * @param question
     */
    public void setQuestion(String question) {
        this.question = question;
    }
    /**
     * @return choices
     */
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * replace the current choice list by the provided choice list
     * @param choicesList
     * @param descriptionsList
     */
    public void setChoices(List<String> choicesList, List<String> descriptionsList) {
        choices.clear();
        for (int i =0; i<choicesList.size(); i++){
            choices.add(new Choice(choicesList.get(i), descriptionsList.get(i)));
        }
    }

    /**
     * add the provided choice list to the current choice list
     * @param choicesList
     * @param descriptionsList
     */
    public void addChoices(List<String> choicesList, List<String> descriptionsList) {
        for (int i =0; i<choicesList.size(); i++){
            choices.add(new Choice(choicesList.get(i), descriptionsList.get(i)));
        }
    }
}
