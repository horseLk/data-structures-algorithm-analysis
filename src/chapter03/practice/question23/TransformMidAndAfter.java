package chapter03.practice.question23;

import java.util.ArrayList;
import java.util.List;

/**
 * 3.23 实现中缀表达式和后缀表达式的互相转换
 */
public class TransformMidAndAfter {
    public String midToAfter(String midVal) {
        StringBuilder builder = new StringBuilder();
        List<Character> operators = new ArrayList<>();
        char chs[] = midVal.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            char ch =chs[i];
            if (ch >= 'a' && ch <= 'z') {
                builder.append(ch);
                continue;
            }
            if (operators.size() == 0) {
                operators.add(ch);
                continue;
            }
            char pre = operators.get(operators.size() - 1);
            if (ch == ')') {
                while (pre != '(') {
                    builder.append(pre);
                    operators.remove(operators.size() - 1);
                    pre = operators.get(operators.size() - 1);
                }
                operators.remove(operators.size() - 1);
                continue;
            }
            if (ch == '(') {
                operators.add(ch);
                continue;
            }
            while (operators.size() > 0 && (isSame(pre, ch) || greater(pre, ch))) {
                builder.append(pre);
                operators.remove(operators.size() - 1);
                if (operators.size() == 0) break;
                pre = operators.get(operators.size() - 1);
            }
            operators.add(ch);
        }
        for (int i = operators.size() - 1; i >= 0; i--) {
            builder.append(operators.get(i));
        }
        return builder.toString();
    }

    public String afterToMid(String afterVal) {
        char chs[] = afterVal.toCharArray();
        List<String> nums = new ArrayList<>();
        for (char ch : chs) {
            if (ch >= 'a' && ch <= 'z') {
                nums.add(String.valueOf(ch));
                continue;
            }
            String val1 = nums.remove(nums.size() - 1);
            String val2 = nums.remove(nums.size() - 1);

            if (val1.length() > 1) {
                val1 = "(" + val1 + ")";
            }
            if (val2.length() > 1) {
                val2 = "(" + val2 + ")";
            }
            nums.add(val2 + ch + val1);
        }

        if (nums.size() != 1) throw new ArithmeticException();
        return nums.get(0);
    }

    private boolean isSame(char pre, char ch) {
        if (pre == '+' && ch == '+') return true;
        if (pre == '-' && ch == '-') return true;
        if (pre == '*' && ch == '*') return true;
        if (pre == '/' && ch == '/') return true;

        if (pre == '+' && ch == '-') return true;
        if (pre == '-' && ch == '+') return true;
        if (pre == '*' && ch == '/') return true;
        if (pre == '/' && ch == '*') return true;
        return false;
    }

    private boolean greater(char pre, char ch) {
        if (pre == '/' && (ch == '+' || ch =='-')) return true;
        if (pre == '*' && (ch == '+' || ch =='-')) return true;
        return false;
    }
}
