package aliexpress.page;

import ui.Browser;

public class CategoryPage extends Browser {
    private static String CATEGORY_LOCATOR = "//*[contains(text(), 'Компьютеры и сетевое оборудование')]";
    private static String ITEM_LOCATOR = "//*[contains(text(), 'Ноутбуки')]";

    public String getTitle() {
        return getPageTitle();
    }

    public void selectCategories() {
        waitForElementVisible(CATEGORY_LOCATOR);
        click(CATEGORY_LOCATOR);
    }

    public void selectItem() {
        waitForElementIsPresent(ITEM_LOCATOR);
        click(ITEM_LOCATOR);
    }
}
