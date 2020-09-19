package chapter03.practice.question21;

import java.util.ArrayList;
import java.util.List;

/**
 * 3.21 平衡符号
 */
public class BalanceSymbol {
    public boolean isBalance(String s) {
        List<Character> list = new ArrayList<>();
        char[] chs = s.toCharArray();
        for (char ch : chs) {
            if (ch == '{' || ch == '(' || ch == ']') {
                list.add(ch);
            } else {
                char pre = list.get(list.size() - 1);
                if ((pre == '{' && ch == '}') || (pre == '(' && ch == ')') || (pre == '[' && ch == ']')) {
                    list.remove(list.size() - 1);
                    continue;
                } else {
                    return false;
                }
            }
        }
        return list.size() == 0;
    }
}
