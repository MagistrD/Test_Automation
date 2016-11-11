package aliexpress.service;

import aliexpress.page.ItemPage;

public class ItemPageService {
    private static final String BRAND_NAME = "Lenovo";

    private ItemPage itemPage = new ItemPage();

    public void searchByBrand() {
        itemPage.typeBrandToSearch();
        itemPage.clickSearchButton();
    }

    public void selectProduct() {
        itemPage.clickProduct();
    }

    public boolean isCorrectBrandPage() {
        String title = itemPage.getTitle();
        return title.contains(BRAND_NAME);
    }
}
