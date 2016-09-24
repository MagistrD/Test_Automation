import com.epam.gomel.homework.Boy;
import com.epam.gomel.homework.Girl;
import com.epam.gomel.homework.Month;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by vladl on 23.09.2016.
 */
public class GirlTest {
    Girl girl;
    Boy boy;

    @Test
    public void firstConstructor() {
        boy = new Boy(Month.AUGUST, 1_000_000, girl);
        girl = new Girl(true, true, boy);
        boolean b = true;
        if (!girl.isPretty() || !girl.isSlimFriendGotAFewKilos() || girl.getBoyFriend() == null) {
            b = false;
        }
        Assert.assertTrue(b, "Expected true");
    }

    @Test
    public void secondConstructor() {
        girl = new Girl(true, true);
        boolean b = true;
        if (!girl.isPretty() || !girl.isSlimFriendGotAFewKilos() || girl.getBoyFriend() != null) {
            b = false;
        }
        Assert.assertTrue(b, "Expected true");
    }

    @Test
    public void thirdConstructor() {
        girl = new Girl(true);
        boolean b = true;
        if (!girl.isPretty() || girl.isSlimFriendGotAFewKilos() || girl.getBoyFriend() != null) {
            b = false;
        }
        Assert.assertTrue(b, "Expected true");
    }

    @Test
    public void fourthConstructor() {
        girl = new Girl();
        boolean b = true;
        if (girl.isPretty() || !girl.isSlimFriendGotAFewKilos() || girl.getBoyFriend() != null) {
            b = false;
        }
        Assert.assertTrue(b, "Expected true");
    }

    @Test
    public void isBoyFriendWillBuyNewShoesTest() {
        boy = new Boy(Month.AUGUST, 1_500_000, girl);
        girl = new Girl(true, true, boy);
        Assert.assertTrue(girl.isBoyFriendWillBuyNewShoes(), "Expected true");
    }

    @DataProvider(name = "Data for boy will not buy new shoes")
    public Object[][] dataForBoyFriendWillNotBuyNewShoesTest() {
        return new Object[][]{
                {500_000, true},
                {1_000_100, false}
        };
    }

    @Test(dataProvider = "Data for boy will not buy new shoes")
    public void isBoyFriendWillNotBuyNewShoesTest(double wealth, boolean pretty) {
        boy = new Boy(Month.AUGUST, wealth, girl);
        girl = new Girl(pretty);
        Assert.assertFalse(girl.isBoyFriendWillBuyNewShoes(), "Excepted false");
    }

}
