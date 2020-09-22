package chapter04.example.example03;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 需求：从一个词典中找出可以通过替换单个字母变成至少15个单词的单词
 * 实现：将这些单词存储到两个TreeMap中，通过Map进行查找
 */
public class ComputeWord {
    public static Map<String, List<String>> computeAdjacentWords(List<String> words) {
        // 保存某个单词为键，其改变一个字母后能够形成的单词集合为值的键值对
        Map<String, List<String>> adjWords = new TreeMap<>();
        // 保存某个长度对应的单词集合
        Map<Integer, List<String>> wordsByLength = new TreeMap<>();

        for (String w : words) {
            update(wordsByLength, w.length(), w);
        }

        for (Map.Entry<Integer, List<String>> enrty : wordsByLength.entrySet()) {
            List<String> groupWords = enrty.getValue();
            int groupNum = enrty.getKey();

            for (int i = 0; i < groupNum; i++) {
                Map<String, List<String>> repoToWord = new TreeMap<>();

                for (String str : groupWords) {
                    String rep = str.substring(0, i) + str.substring(i + 1);
                    update(repoToWord, rep, str);
                }

                for (List<String> wordClique : repoToWord.values()) {
                    if (wordClique.size() >= 2)
                        for (String s1 : wordClique)
                            for (String s2 : wordClique)
                                if (s1 != s2)
                                    update(adjWords, s1, s2);
                }
            }

        }
        return adjWords;
    }

    private static <T> void update(Map<T, List<String>> map, T key, String word) {
        List<String> list = map.get(key);
        if (key == null) {
            list = new ArrayList<>();
        }
        list.add(word);
        map.put(key, list);
    }
}
