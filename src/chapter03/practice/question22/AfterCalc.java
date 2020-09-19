package chapter03.practice.question22;

import java.util.ArrayList;
import java.util.List;

/**
 * 3.22 后缀表达式的值计算
 */
public class AfterCalc {
    public int calc(String s) {
        /*
            42 - 乘号 、 43 - 加号
            45 - 减号 、 47 - 除号
         */
        List<Integer> list = new ArrayList<>();
        char chs[] = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] >= '0' && chs[i] <= '9') {
                list.add(chs[i] - 48);
                continue;
            }
            int val1 = list.remove(list.size() - 1);
            int val2 = list.remove(list.size() - 1);
            int val = 0;
            if (chs[i] == '+') val = val1 + val2;
            if (chs[i] == '-') val = val1 - val2;
            if (chs[i] == '*') val = val1 * val2;
            if (chs[i] == '/') val = val1 / val2;
            list.add(val);
        }
        if (list.size() != 1) throw new ArithmeticException();
        return list.get(0);
    }
}
