package business;

public class Poll {
    String name;
    String question;
    String status = "CREATED";

    int red_count = 0;
    int blue_count = 0;

    Poll(String n, String q) {
        super();
        this.name = n;
        this.question = q;
    }

    public void increment(int x) {
        if(x == 1) {
            this.red_count += 1;
        }
        else if (x == 2) {
            this.blue_count += 1;
        }
    }

    public void show_red_count() {
        System.out.println("red count: " + red_count);
    }

    public String get_status() {
        return this.status;
    }

}
