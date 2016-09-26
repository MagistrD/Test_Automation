import com.epam.gomel.homework.Boy;
import com.epam.gomel.homework.Girl;
import com.epam.gomel.homework.Month;
import com.epam.gomel.homework.Mood;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class GirlTest {
    private Girl girl;
    private Boy boy;


    @Test(groups = "constructors")
    public void firstConstructorTest() {
        boy = new Boy(Month.AUGUST, 1_000_000, girl);
        girl = new Girl(true, true, boy);
        boolean b = true;
        if (!girl.isPretty() || !girl.isSlimFriendGotAFewKilos() || girl.getBoyFriend() == null) {
            b = false;
        }
        Assert.assertTrue(b, "Expected true");
    }

    @Test(groups = "constructors")
    public void secondConstructorTest() {
        girl = new Girl(true, true);
        boolean b = true;
        if (!girl.isPretty() || !girl.isSlimFriendGotAFewKilos() || girl.getBoyFriend() != null) {
            b = false;
        }
        Assert.assertTrue(b, "Expected true");
    }

    @Test(groups = "constructors")
    public void thirdConstructorTest() {
        girl = new Girl(true);
        boolean b = true;
        if (!girl.isPretty() || girl.isSlimFriendGotAFewKilos() || girl.getBoyFriend() != null) {
            b = false;
        }
        Assert.assertTrue(b, "Expected true");
    }

    @Test(groups = "constructors")
    public void fourthConstructorTest() {
        girl = new Girl();
        boolean b = true;
        if (girl.isPretty() || !girl.isSlimFriendGotAFewKilos() || girl.getBoyFriend() != null) {
            b = false;
        }
        Assert.assertTrue(b, "Expected true");
    }

    private Month monthFab;
    private double wealthFab;
    private boolean prettyFab;

    GirlTest(Month monthFab, double wealthFab, boolean prettyFab) {
        this.monthFab = monthFab;
        this.wealthFab = wealthFab;
        this.prettyFab = prettyFab;
    }

    @Test
    public void spendBoyFriendMoneyTest() {
        boy = new Boy(monthFab, wealthFab, girl);
        girl = new Girl(prettyFab, true, boy);
        double startingBalance = boy.getWealth();
        double amountForSpending = 500_000;
        girl.spendBoyFriendMoney(amountForSpending);
        boolean result = true;
        if (startingBalance <= boy.getWealth()) {
            result = false;
        }
        Assert.assertTrue(result, "Excepted false");
    }

    @Test
    public void isBoyFriendWillBuyNewShoesTest() {
        boy = new Boy(Month.AUGUST, 1_500_000, girl);
        girl = new Girl(true, true, boy);
        Assert.assertTrue(girl.isBoyFriendWillBuyNewShoes(), "Expected true");
    }

    @DataProvider(name = "Data for boy will not buy new shoes")
    public Object[][] dataForBoyFriendWillNotBuyNewShoes() {
        return new Object[][]{
                {500_000.0, true},
                {1_000_100.0, false}
        };
    }

    @Test(dataProvider = "Data for boy will not buy new shoes")
    public void isBoyFriendWillNotBuyNewShoesTest(double wealth, boolean pretty) {
        boy = new Boy(Month.AUGUST, wealth, girl);
        girl = new Girl(pretty);
        Assert.assertFalse(girl.isBoyFriendWillBuyNewShoes(), "Excepted false");
    }


    @Test
    public void isBoyfriendRichTest() {
        boy = new Boy(Month.AUGUST, 1_500_000, girl);
        girl = new Girl(true, true, boy);
        Assert.assertTrue(girl.isBoyfriendRich(), "Excepted True");
    }

    @DataProvider(name = "Data for boy is not rich")
    public Object[][] dataForIsBoyfriendNotRich() {
        return new Object[][]{
                {(double) 500_000, true},
                {(double) 0, false}
        };
    }

    @Test(dataProvider = "Data for boy is not rich")
    public void isBoyfriendNotRichTest(double wealth, boolean haveBoy) {
        if (haveBoy) {
            boy = new Boy(Month.AUGUST, wealth);
        } else boy = null;
        girl = new Girl(true, true, boy);
        Assert.assertFalse(girl.isBoyfriendRich(), "Expected false");
    }

    @Test
    public void isSlimFriendNotBecameFatTest() {
        girl = new Girl(true, false);
        Assert.assertFalse(girl.isSlimFriendBecameFat(), "Excepted false");
    }

    @Test
    public void ExcellentGirlMoodTest() {
        boy = new Boy(Month.AUGUST, 1_500_500, girl);
        girl = new Girl(true, true, boy);
        Assert.assertEquals(Mood.EXCELLENT, girl.getMood(), "Excepted Excellent mood");
    }

    @Test
    public void GoodGirlMoodTest() {
        boy = new Boy(Month.AUGUST, 0, girl);
        girl = new Girl(true, true, boy);
        Assert.assertEquals(Mood.GOOD, girl.getMood(), "Excepted Good mood");
    }

    @Test
    public void NeutralGirlMoodTest() {
        girl = new Girl(false, true);
        Assert.assertEquals(Mood.NEUTRAL, girl.getMood(), "Excepted Neutral mood");
    }

    @Test
    public void HateGirlMoodTest() {
        girl = new Girl(false, false);
        Assert.assertEquals(Mood.I_HATE_THEM_ALL, girl.getMood(), "Excepted I Hate Them All mood");
    }
}
