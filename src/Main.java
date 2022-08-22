import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Реализовал данную задачу
 * с учётом того, что в input приходит строка без пробелов, поскольку в ТЗ явно не указано обратное.
 * Если необходимо учитывать пробелы, то поступил бы так: в регулярное выражение добавил бы возможное наличие пробелов,
 * а в получившемся массиве "arg" (поделённом по арифметическому символу), каждый элемент прокрутил бы через цикл и отбросил бы пробелы,
 * далее все то же самое.
 * <p>
 * Приятной проверки :)
 */


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
        // проверка входного параметра на допустимые символы
        boolean arabianNumCondition = input.matches("\\d{1,2}[+\\-/*]\\d{1,2}");
        boolean romanNumCondition = input.matches("[IVX]{1,4}[+\\-/*][IVX]{1,4}");
        if (!arabianNumCondition && !romanNumCondition) {
            System.out.println("Для ввода допустимы только целые арабские или римские цифры от 1 до 10 включительно " +
                    "и знаки арифметических действий \n" +
                    "или формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *). БЕЗ ПРОБЕЛОВ!");
            throw new Exception();
        }

        String[] arg = input.split("[+\\-/*]");    // делим входную строку по символу арифметического действия
        char arithmeticSign = 0;                        //вводим переменную для хранения символа арифметического действия
        arithmeticSign = input.charAt(arg[0].length()); // символ арифметического действия стоит на позиции равной длинне первого числа

        //проверка что числа лежат в диапазоне от 1 до 10 включительно
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
        if (romanNumCondition) {
            if (resultNum <= 0) {
                System.out.println("Результатом работы калькулятора с римскими числами не могут быть отрицательные числа и ноль.");
                throw new Exception();
            }
            result = arabToRoman(resultNum);
        } else {
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

        int arab = 0;                                                 // вводим переменную для хранения результата
        int prev = 0;                                                // вводим переменную для хранения предидущего римского знака

        for (int i = rom.length() - 1; i >= 0; i--) {
            int actual = map100.get(rom.charAt(i));
            if (actual < prev) {
                arab -= actual;
            } else {
                arab += actual;
            }
            prev = actual;
        }
        return arab;
    }

    private static String arabToRoman(int arab) {
        //поскольку входные параметры ограничены [1,10] то максимальный результат не будет превышать 100
        int[] arabNum = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romNum = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arabNum.length && arab > 0; i++) {
            while (arab >= arabNum[i]) {
                arab -= arabNum[i];
                sb.append(romNum[i]);
            }
        }
        return sb.toString();
    }
}
