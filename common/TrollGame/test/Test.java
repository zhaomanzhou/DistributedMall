import java.util.Random;

public class Test {

    @org.junit.Test
    public void test01() {
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            System.out.println(rand.nextInt(3));
        }
    }
}
