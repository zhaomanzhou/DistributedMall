import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDataSource
{
    @Autowired
    private DataSource dataSource;

    @Test
    public void test1()
    {
        Assert.assertNotNull(dataSource);
    }
}
