package aliexpress.service;

import aliexpress.page.CategoryPage;

public class CategoryPageService {
    private static final String CATEGORY = "Компьютеры и сетевое оборудование";

    private CategoryPage categoryPage = new CategoryPage();

    public String getTitle() {
        return categoryPage.getTitle();
    }

    public boolean isCorrectTitle() {
        String title = getTitle();
        return title.contains(CATEGORY);
    }
}
