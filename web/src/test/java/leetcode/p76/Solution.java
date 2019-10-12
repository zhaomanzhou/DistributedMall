package leetcode.p76;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution
{
    public String minWindow(String s, String t) {

        String res = s;

        Map<Character, Integer> map = new HashMap<>();
        for(Character c: t.toCharArray())
        {
            if(map.get(c) == null)
                map.put(c, -1);
            else
                map.put(c, map.get(c) - 1);

        }



        int begin = -1, end = -1;

        while (notComplete(map) && begin < s.length() - 1)
        {
            Character c = s.charAt(++begin);
            if(map.get(c) != null)
            {
                map.put(c, map.get(c) + 1);
            }

        }

        if(notComplete(map))
            return "";

        while (end < s.length()-1)
        {

            Character c = null;
            while (!notComplete(map))
            {
                c = s.charAt(++end);
                if(map.containsKey(c))
                {
                    map.put(c, map.get(c)-1);
                }
            }
            end--;
            map.put(c, map.get(c) + 1);
            if(begin - end < res.length())
            {
                res = s.substring(end+1, begin + 1);
            }

            if(begin == s.length() -1)
                break;
            while (begin < s.length() -1)
            {
                c = s.charAt(++begin);

                if(map.containsKey(c))
                {
                    map.put(c, map.get(c)+1);
                    break;
                }

            }

        }










        return res;
    }

    private boolean notComplete(Map<Character, Integer> map)
    {

        for(Integer i: map.values())
        {
            if( i < 0)
                return true;
        }


        return false;
    }



    @Test
    public void testWindow()
    {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }


    @Test
    public void test()
    {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.remove(Integer.valueOf(1));
        System.out.println(list);
    }

}
