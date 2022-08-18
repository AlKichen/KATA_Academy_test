import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
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
        System.out.println("result: "+calc(input));

    }

    public static String calc(String input) throws Exception {
        String result = "";
        // проверка входного параметра на допустимые символы
        boolean arabianNumCondition = input.matches("\\d{1,2}[+\\-/*]\\d{1,2}");
        boolean romanNumCondition = input.matches("[IVX]{1,3}[+\\-/*][IVX]{1,3}");
        System.out.println("arab: " + arabianNumCondition);
        System.out.println("roman: " + romanNumCondition);
        if (!arabianNumCondition && !romanNumCondition) {
            System.out.println("Для ввода допустимы только целые арабские или римские цифры от 1 до 10 включительно " +
                    "и знаки арифметических действий \n" +
                    "или формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            throw new Exception();
        }

        String[] arg = input.split("[+\\-/*]");    // делим входную строку по символу арифметического действия
        char arithmeticSign = 0;                      //вводим переменную для хранения символа арифметического действия
        if (arg[0].length() == 1) {                   // если первое число в входной строке длинной 1 символ
            arithmeticSign = input.charAt(1);         // то символ арифметического действия стоит на позиции 1
        } else if (arg[0].length() == 2) {             // если первое число в входной строке длинной 2 символа
            arithmeticSign = input.charAt(2);          // то символ арифметического действия стоит на позиции 2
        }

        //Добавление hashMap со значениями римских и арабских чисел
        HashMap<Integer, String> map100 = new HashMap<>();
        map100.put(1, "I");
        map100.put(4,"IV");
        map100.put(5,"V");
        map100.put(9,"IX");
        map100.put(10,"X");
        map100.put(40,"XL");
        map100.put(50,"L");
        map100.put(90,"XC");
        map100.put(100,"C");

        HashMap<Integer, String> mapTen = new HashMap<>();
        mapTen.put(1, "I");
        mapTen.put(2, "II");
        mapTen.put(3, "III");
        mapTen.put(4, "IV");
        mapTen.put(5, "V");
        mapTen.put(6, "VI");
        mapTen.put(7, "VII");
        mapTen.put(8, "VIII");
        mapTen.put(9, "IX");
        mapTen.put(10, "X");

        //проверка на то что числа лежат в диапазоне от 1 до 10 включительно
        int a = 0;
        int b = 0;
        if (arabianNumCondition) {
            a = Integer.parseInt(arg[0]);
            b = Integer.parseInt(arg[1]);
            if ((a <= 0 || a > 10) || (b <= 0 || b > 10)) {
                System.out.println("Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.");
                throw new Exception();
            }
        } else {
             if (mapTen.containsValue(arg[0]) && mapTen.containsValue(arg[1])){

            }else {
                 throw new Exception();
             }

        }

        int resultNum = 0;
        switch (arithmeticSign) {
            case '+':
                resultNum = a + b;
                break;
            case '-':
                resultNum = a - b;
                break;
            case '/':
                resultNum = a / b;
                break;
            case '*':
                resultNum = a * b;
                break;
        }
        System.out.println(String.valueOf(arithmeticSign));
        System.out.println(arg[0]);
        System.out.println(arg[1]);
        result = String.valueOf(resultNum);
        return result;
    }
}
