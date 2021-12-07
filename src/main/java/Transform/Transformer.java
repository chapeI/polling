package Transform;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Transformer {
    public static String transform(Message message, String templatePath){
        if (message.getUserID()==null){
            return "";
        }
        String result = "";
        try {
            File myObj = new File(templatePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.contains("<userid>")){
                    data = data.replace("<userid>", message.getUserID());
                }
                if (data.contains("<link>")){
                    data = data.replace("<link>", message.getLink());
                }
                if (data.contains("<token>")){
                    data = data.replace("<token>", message.getToken());
                }
                if (data.isBlank()){
                    data = "\n";
                }
                result  += data + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result;
    }
}
