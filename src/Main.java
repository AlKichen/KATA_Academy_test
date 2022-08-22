import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) throws Exception {
        String input = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + calc(input));

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
        char arithmeticSign = 0;                        //вводим переменную для хранения символа арифметического действия
        arithmeticSign = input.charAt(arg[0].length()); // символ арифметического действия стоит на позиции равной длинне первого числа

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
            a = romanToArab(arg[0]);
            b = romanToArab(arg[1]);
            if ((a <= 0 || a > 10) || (b <= 0 || b > 10)) {
                System.out.println("Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.");
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
        if (romanNumCondition){
            result = arabToRoman(resultNum);
        }else {
            result = String.valueOf(resultNum);
        }
        return result;
    }

    private static int romanToArab(String rom) {

        HashMap<Character, Integer> map100 = new HashMap<>();
        map100.put('I', 1);
        map100.put('V', 5);
        map100.put('X', 10);
        map100.put('L', 50);
        map100.put('C', 100);

        int arab = 0;
        int prev = 0;

        for (int i = rom.length() - 1; i >= 0; i--) {
            int actual = map100.get(rom.charAt(i));
            if (actual < prev) {
                arab -= actual;
            } else {
                arab += actual;
            }
            prev = actual;
        }
        System.out.println("roman: " + rom);
        System.out.println("arab: " + arab);
        return arab;
    }

    private static String arabToRoman(int arab) {
        System.out.println("arab: " + arab);
        int[] arabNum = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romNum = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arabNum.length && arab > 0; i++){
            while (arab >= arabNum[i]){
                arab -= arabNum[i];
                sb.append(romNum[i]);
            }
        }
        System.out.println("roman: " + sb.toString());
        return sb.toString();
    }
}
