package leetcode.p30;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {

    private int unitSize;
    private HashMap<String, Integer> baseMap;
    int unitNum;
    String s;
    public List<Integer> findSubstring(String s, String[] words) {
        if(words.length == 0)
            return new ArrayList<>();


        this.s = s;
        List<Integer> answer = new ArrayList<>();
        unitNum = words.length;
        //比较字符串长度
        unitSize = words[0].length();



        int begin = 0;

        //存放words
        baseMap = new HashMap<>();
        for(int i = 0; i < words.length; i++)
        {
            baseMap.put(words[i], baseMap.getOrDefault(words[i], 0) - 1);
        }





        while (begin + words.length * unitSize - 1 < s.length())
        {
            if(baseMap.containsKey(s.substring(begin, begin+ unitSize)))
            {
                if(search(begin));
                    answer.add(begin);
            }
            begin+= unitSize;
        }


        return answer;

    }

    public boolean search(int begin)
    {
//        Map<String, Integer> map = baseMap.entrySet().stream()
//                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() + 0));
        HashMap<String, Integer> map = new HashMap<>();
        for(Map.Entry<String, Integer> entry: baseMap.entrySet())
        {
            map.put(entry.getKey(), entry.getValue());
        }
        int finded = 0;
        while (finded < unitNum)
        {
            String substring = s.substring(begin, begin + unitSize);
            Integer orDefault = map.getOrDefault(substring, 0);
            if(orDefault == 0)
            {
                return false;
            }

            map.put(substring, orDefault+1);
            finded++;
            begin += unitSize;

        }
        return true;
    }

    @Test
    public void test()
    {
        String[] words = {"foo","bar"};
        System.out.println(findSubstring("barfoothefoobarman", words));
    }
}
