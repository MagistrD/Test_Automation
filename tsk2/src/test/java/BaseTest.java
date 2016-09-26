import com.epam.gomel.homework.Boy;
import com.epam.gomel.homework.Girl;
import com.epam.gomel.homework.Month;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseTest {
    private Girl girl;
    private Boy boy;

    @Test(dependsOnMethods = {"secondConstructorTest"}, groups = "constructors")
    public void firstConstructorTest() {
        girl = new Girl(true, true, boy);
        boy = new Boy(Month.JUNE, 1_100_000, girl);
        boolean b = true;
        if (boy.getBirthdayMonth() != Month.JUNE || boy.getWealth() != 1_100_000 || boy.getGirlFriend() == null) {
            b = false;
        }
        Assert.assertTrue(b, "Constructor incorrect");
    }

    @Test(dependsOnMethods = {"thirdConstructorTest"}, groups = "constructors", alwaysRun = true)
    public void secondConstructorTest() {
        Boy boy = new Boy(Month.JUNE, 1_100_000);
        boolean b = true;
        if (boy.getBirthdayMonth() != Month.JUNE || boy.getWealth() != 1_100_000 || boy.getGirlFriend() != null) {
            b = false;
        }
        Assert.assertTrue(b, "Constructor incorrect");
    }

    @Test(groups = "constructors")
    public void thirdConstructorTest() {
        boy = new Boy(Month.JUNE);
        boolean b = true;
        if (boy.getBirthdayMonth() != Month.JUNE || boy.getWealth() != 0 || boy.getGirlFriend() != null) {
            b = false;
        }
        Assert.assertTrue(b, "Constructor has bug");
    }
}
