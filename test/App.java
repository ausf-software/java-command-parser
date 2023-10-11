import io.github.ausf_software.jcp.CommandParser;
import io.github.ausf_software.jcp.CommandToken;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<CommandToken> ttokens = null;
        try {
            ttokens = CommandParser.parse(args, "res/rules.properties");
            System.out.println(ttokens);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
