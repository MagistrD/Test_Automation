import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;


public class FactoryTest {

    @DataProvider
    public Object[][] isSlimFriendNotBecameFatTest() {
        return new Object[][]{
                {false, false},
                {true, true},
                {true, false}
        };
    }

    @Factory(dataProvider = "isSlimFriendNotBecameFatTest")
    public Object[] createTest(boolean pretty, boolean isFat) {
        return new Object[]{new GirlTest(pretty, isFat)};
    }

}
