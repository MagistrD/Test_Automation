package aliexpress.page;

import ui.Browser;

public class ItemPage extends Browser {

    private static final String BRAND_SEARCH_LOCATOR = "//input[@id='ipt-kwd']";
    private static final String SEARCH_BUTTON = "//*[@id='add-keyword-btn']";
    private static final String BRAND_NAME = "Lenovo";
    private static final String PRODUCT_LOCATOR = "//a[contains(@title, 'Lenovo LEMON K3')]";

    public void typeBrandToSearch() {
        waitForElementVisible(BRAND_SEARCH_LOCATOR);
        typeValue(BRAND_SEARCH_LOCATOR, BRAND_NAME);
    }

    public void clickSearchButton() {
        waitForElementVisible(SEARCH_BUTTON);
        click(SEARCH_BUTTON);
    }

    public void clickProduct() {
        waitForElementVisible(PRODUCT_LOCATOR);
        click(PRODUCT_LOCATOR);
    }

    public String getTitle() {
        return getPageTitle();
    }

}
