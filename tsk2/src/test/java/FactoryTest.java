import org.testng.annotations.DataProvider;
import com.epam.gomel.homework.Month;
import org.testng.annotations.Factory;

public class FactoryTest {

    @DataProvider
    public Object[][] data() {
        return new Object[][]{
                {Month.JULY, 1_500_000, true},
                {Month.JUNE, 1_500_000, true},
        };
    }

    @Factory(dataProvider = "data")
    public Object[] createTest(Month month, double w, boolean b) {
        return new Object[]{new GirlTest(month, w, b)};
    }
}
