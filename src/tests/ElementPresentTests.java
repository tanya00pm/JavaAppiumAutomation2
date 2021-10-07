package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ElementPresentTests extends CoreTestCase
{
    /* Ex6 */
    @Test
    public void testArticleTitlePresent()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Osho");
        SearchPageObject.clickByArticleWithSubstring("Rajneesh");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.assertArticleTitleExists();
    }
    /* Ex6 */
}
