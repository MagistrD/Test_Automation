import com.epam.gomel.homework.Boy;
import com.epam.gomel.homework.Girl;
import com.epam.gomel.homework.Month;
import com.epam.gomel.homework.Mood;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class BoyTest extends BaseTest {
    private Girl girl;
    private Boy boy;

    @DataProvider(name = "Data for mood")
    public Object[][] dataForMood() {
        return new Object[][]{
                {Month.JULY, 1_000_000, girl},
                {girl, false}
        };
    }

    @Test
    @Parameters({"pretty"})
    public void ExcellentMoodTest(@Optional(value = "true") boolean pretty) {
        girl = new Girl(pretty);
        boy = new Boy(Month.AUGUST, 1_500_500, girl);
        Assert.assertEquals(Mood.EXCELLENT, boy.getMood(), "Excepted Excellent mood");
    }

    @Test
    public void GoodMoodTest() {
        girl = new Girl(true);
        boy = new Boy(Month.JANUARY, 1_500_500, girl);
        Assert.assertEquals(Mood.GOOD, boy.getMood(), "Excepted Good mood");
    }

    @Test
    public void NeutralMoodTest() {
        boy = new Boy(Month.JUNE, 1_500_500);
        Assert.assertEquals(Mood.NEUTRAL, boy.getMood(), "Excepted Neutral mood");
    }

    @DataProvider(name = "Data for bad mood")
    public Object[][] dataForBadMood() {
        return new Object[][]{
                {Month.JULY, 500_000, false},
                {Month.JANUARY, 1_000_001, false},
                {Month.JANUARY, 500_000, true}
        };
    }

    @Test(dataProvider = "Data for bad mood")
    public void BadMoodTest(Month month, double wealth, boolean pretty) {
        girl = new Girl(pretty);
        boy = new Boy(month, wealth, girl);
        Assert.assertEquals(Mood.BAD, boy.getMood(), "Excepted Bad mood");
    }

    @Test
    public void HorribleMoodTest() {
        boy = new Boy(Month.JANUARY, 500_500);
        Assert.assertEquals(Mood.HORRIBLE, boy.getMood(), "Excepted Horrible mood");
    }

    @DataProvider(name = "Data for spend some money")
    public Object[][] dataSpendSomeMoney() {
        return new Object[][]{
                {0, 0, 0},
                {1_000_000, 600_000, 400_000},
                {1_000_000, 1_000_000, 0}
        };
    }

    @Test(dataProvider = "Data for spend some money")
    public void spendSomeMoneyTest(double wealth, double amountForSpending, double remainder) {
        boy = new Boy(Month.JUNE, wealth, null);
        boy.spendSomeMoney(amountForSpending);
        Assert.assertTrue(boy.getWealth() == remainder, "spendSomeMoneyTest incorrect");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void exceptionSpendSomeMoneyTest() {
        boy = new Boy(Month.AUGUST, 500_000);
        boy.spendSomeMoney(1_000_000);
    }

    @DataProvider(name = "Data for is summer month")
    public Object[][] dataIsSummerMonth() {
        return new Object[][]{
                {Month.JUNE},
                {Month.JULY},
                {Month.AUGUST}
        };
    }

    @Test(dataProvider = "Data for is summer month")
    public void isSummerMonthTest(Month month) {
        boy = new Boy(month, 0, null);
        Assert.assertTrue(boy.isSummerMonth(), "Expected summer month");
    }

    @DataProvider(name = "Data for not summer month")
    public Object[][] dataNotSummerMonth() {
        return new Object[][]{
                {Month.JANUARY},
                {Month.FEBRUARY},
                {Month.MARCH},
                {Month.APRIL},
                {Month.MAY},
                {Month.SEPTEMBER},
                {Month.OCTOBER},
                {Month.NOVEMBER},
                {Month.DECEMBER},
        };
    }

    @Test(dataProvider = "Data for not summer month")
    public void isNotSummerMonthTest(Month month) {
        boy = new Boy(month, 0, null);
        Assert.assertFalse(boy.isSummerMonth(), "Expected not summer month");
    }


    @DataProvider(name = "Data for is rich")
    public Object[][] dataForIsRich() {
        return new Object[][]{
                {1_000_000},
                {1_000_001},
        };
    }

    @Test(dataProvider = "Data for is rich")
    public void isRichTest(double wealth) {
        boy = new Boy(Month.JUNE, wealth, null);
        assertThat("Expected boy is rich", boy.isRich(), is(true));
    }

    @DataProvider(name = "Data for is not rich")
    public Object[][] dataForNotIsRich() {
        return new Object[][]{
                {0},
                {999_999},
        };
    }

    @Test(dataProvider = "Data for is not rich")
    public void isNotRichTest(double wealth) {
        boy = new Boy(Month.JUNE, wealth, null);
        assertThat("Expected boy is not rich", boy.isRich(), is(false));
    }

    @Test
    public void isPrettyGirlFriendTest() {
        girl = new Girl(true);
        boy = new Boy(Month.APRIL, 1_000_000, girl);
        Assert.assertTrue(boy.isPrettyGirlFriend());
    }

    @Test
    public void isNotPrettyGirlFriendTest() {
        Girl g = new Girl(false);
        boy = new Boy(Month.APRIL, 1_000_000, g);
        Assert.assertFalse(boy.isPrettyGirlFriend(), "Excepted false");
    }
}
