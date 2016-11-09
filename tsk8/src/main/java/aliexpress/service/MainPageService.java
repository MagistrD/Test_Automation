package aliexpress.service;

import aliexpress.page.CategoryPage;
import aliexpress.page.MainPage;

public class MainPageService {
    private MainPage mainPage = new MainPage();
    private CategoryPage categoryPage = new CategoryPage();

    public void clickItem() {
        mainPage.clickAllCategories();
        categoryPage.selectCategories();
        categoryPage.selectItem();
    }

    public boolean isLogin() {
        return mainPage.isUserNamePresent();
    }
}
