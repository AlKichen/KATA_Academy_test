import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        String input = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(calc(input));
    }

    public static String calc(String input) {
        String result = "";
        String [] arg = input.split("[+-/*]");
        char arithmeticSign = 0;
        if (arg[0].length() == 1){
            arithmeticSign = input.charAt(1);
        } else if (arg[0].length() == 2){
            arithmeticSign = input.charAt(2);
        }
        //System.out.println(String.valueOf(arithmeticSign));
        
        return result;
    }
}
