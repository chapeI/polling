package business;

public class Poll {
    String name;
    String question;
    PollStatus status;

    public int red_count = 0;
    int blue_count = 0;

    Poll() {
        super();
    }

    public void increment(int x) {
        if(x == 1) {
            this.red_count += 1;
        }
        else if (x == 2) {
            this.blue_count += 1;
        }
    }

    public void get_red_count() {
        System.out.println("red count: " + red_count);
    }
}
