import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(calc(input));

    }

    public static String calc(String input) throws Exception {
        String result = "";
        Pattern pattern = Pattern.compile("[^1234567890+\\-/*IVX]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        boolean condition = matcher.find();
        if (condition) {
            System.out.println("Для ввода допустимы только целые арабские или римские цифры от 1 до 10 включительно");
            throw new Exception();
        }


        String[] arg = input.split("[+\\-/*]");    // делим входную строку по символу арифметического действия
        char arithmeticSign = 0;                      //вводим переменную для хранения символа арифметического действия
        if (arg[0].length() == 1) {                   // если первое число в входной строке длинной 1 символ
            arithmeticSign = input.charAt(1);         // то символ арифметического действия стоит на позиции 1
        } else if (arg[0].length() == 2) {             // если первое число в входной строке длинной 2 символа
            arithmeticSign = input.charAt(2);          // то символ арифметического действия стоит на позиции 2
        }

        switch (arithmeticSign) {
            case '+':

                break;
            case '-':

                break;
            case '/':

                break;

            case '*':

                break;
        }
        System.out.println(String.valueOf(arithmeticSign));
        System.out.println(arg[0]);
        System.out.println(arg[1]);

        return result;
    }
}
