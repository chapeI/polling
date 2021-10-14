package business;
import java.io.PrintWriter;
import java.util.List;

public class PollManager {
    private Poll poll;
    private String pollCreator;

    public void CreatePoll(String name, String question, List<Choice> choices){

    };

    public void UpdatePoll(String name, String question, List<Choice> choices){};

    public void ClearPoll(){};

    public void ClosePoll(){};

    public void RunPoll(){};

    // Khoa
    // ----------------------
    // Alek

    public void ReleasePoll(){};

    public void UnreleasePoll(){};

    public void Vote(String participant, String choice){};

    public void GetPollResults(){};

    public void DOwnloadPollDetails(PrintWriter output, String filename){};
}
