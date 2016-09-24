import com.epam.gomel.homework.Boy;
import com.epam.gomel.homework.Girl;
import com.epam.gomel.homework.Month;
import com.epam.gomel.homework.Mood;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GirlTest {
    private Girl girl;
    private Boy boy;

    private boolean isFat;
    private boolean pretty;

    public GirlTest(boolean isFat, boolean pretty) {
        this.isFat = isFat;
        this.pretty = pretty;
    }

    @Test(groups = "constructors", alwaysRun = true)
    public void firstConstructor() {
        boy = new Boy(Month.AUGUST, 1_000_000, girl);
        girl = new Girl(true, true, boy);
        boolean b = true;
        if (!girl.isPretty() || !girl.isSlimFriendGotAFewKilos() || girl.getBoyFriend() == null) {
            b = false;
        }
        Assert.assertTrue(b, "Expected true");
    }

    @Test(groups = "constructors", alwaysRun = true)
    public void secondConstructor() {
        girl = new Girl(true, true);
        boolean b = true;
        if (!girl.isPretty() || !girl.isSlimFriendGotAFewKilos() || girl.getBoyFriend() != null) {
            b = false;
        }
        Assert.assertTrue(b, "Expected true");
    }

    @Test(groups = "constructors", alwaysRun = true)
    public void thirdConstructor() {
        girl = new Girl(true);
        boolean b = true;
        if (!girl.isPretty() || girl.isSlimFriendGotAFewKilos() || girl.getBoyFriend() != null) {
            b = false;
        }
        Assert.assertTrue(b, "Expected true");
    }

    @Test(groups = "constructors", alwaysRun = true)
    public void fourthConstructor() {
        girl = new Girl();
        boolean b = true;
        if (girl.isPretty() || !girl.isSlimFriendGotAFewKilos() || girl.getBoyFriend() != null) {
            b = false;
        }
        Assert.assertTrue(b, "Expected true");
    }

    @Test(dependsOnGroups = "constructors")
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

    @Test
    public void spendBoyFriendMoneyTest() {
        boy = new Boy(Month.AUGUST, 1_500_000, girl);
        girl = new Girl(true, true, boy);
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
    public void isBoyfriendRichTest() {
        boy = new Boy(Month.AUGUST, 1_500_000, girl);
        girl = new Girl(true, true, boy);
        Assert.assertTrue(girl.isBoyfriendRich(), "Excepted True");
    }

    @DataProvider(name = "Data for boy is not rich")
    public Object[][] dataForIsBoyfriendNotRich() {
        return new Object[][]{
                {500_000, true},
                {0, false}
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
    @Parameters({"pretty", "becameFat"})
    public void isSlimFriendBecameFatTest(boolean pretty, boolean fat) {
        girl = new Girl(pretty, fat);
        Assert.assertTrue(girl.isSlimFriendBecameFat(), "Excepted true");
    }

    /*@DataProvider(name = "Data for is slim friend not became fat")
    public Object[][] dataForIsSlimFriendNotBecameFat() {
        return new Object[][]{
                {false, false},
                {true, true},
                {true, false}
        };
    }*/

    @Test
    public void isSlimFriendNotBecameFatTest() {
        girl = new Girl(pretty, isFat);
        Assert.assertFalse(girl.isSlimFriendBecameFat(), "Excepted false");
    }

    @Test(groups = "mood")
    public void ExcellentGirlMoodTest() {
        boy = new Boy(Month.AUGUST, 1_500_500, girl);
        girl = new Girl(true, true, boy);
        Assert.assertEquals(Mood.EXCELLENT, girl.getMood(), "Excepted Excellent mood");
    }

    @Test(groups = "mood")
    public void GoodGirlMoodTest() {
        boy = new Boy(Month.AUGUST, 0, girl);
        girl = new Girl(true, true, boy);
        Assert.assertEquals(Mood.GOOD, girl.getMood(), "Excepted Good mood");
    }

    @Test(groups = "mood")
    public void NeutralGirlMoodTest() {
        girl = new Girl(false, true);
        Assert.assertEquals(Mood.NEUTRAL, girl.getMood(), "Excepted Neutral mood");
    }

    @Test(groups = "mood")
    public void HateGirlMoodTest() {
        girl = new Girl(false, false);
        Assert.assertEquals(Mood.I_HATE_THEM_ALL, girl.getMood(), "Excepted I Hate Them All mood");
    }
}
