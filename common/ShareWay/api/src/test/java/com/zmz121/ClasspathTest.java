package com.zmz121;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
public class ClasspathTest
{
    @Test
    public void test01()
    {
        System.out.println(System.getProperty("java.class.path"));
        Properties properties = System.getProperties();
        Enumeration<Object> keys = properties.keys();
        while (keys.hasMoreElements())
        {
            String o = (String)keys.nextElement();
            //System.out.println(o + " **:** " + properties.get(o));
        }
    }

    @Test
    public void test02()
    {
        //ClassLoader parent = getClass().getClassLoader().getParent();
        URL resource = getClass().getClassLoader().getResource("mybatis/mapper/UserMapper.xml");
        Assert.assertNotNull(resource);
    }

    @Test
    public void test03() throws URISyntaxException
    {
        File f = new File(getClass().getResource("/").toURI());
        System.out.println(f.getAbsolutePath());
    }

    @Test
    public void test04() throws URISyntaxException
    {
        File f = new File(getClass().getResource("").toURI());
        System.out.println(f.getAbsolutePath());
    }

    @Test
    public void test05()
    {
        URL xmlpath = this.getClass().getClassLoader().getResource("/");
        System.out.println(xmlpath);
    }
}
