package com.zmz121;

import org.junit.Test;

public class TestString
{

    String s = "file:/c/d";


    @Test
    public void test01()
    {
        System.out.println(s.indexOf(':'));
        System.out.println(s.substring(s.indexOf(':')));
    }
}
