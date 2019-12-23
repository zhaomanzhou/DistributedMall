package util;

import org.junit.Test;

import java.util.UUID;

public class TestUUid
{
    @Test
    public void test()
    {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }
}
