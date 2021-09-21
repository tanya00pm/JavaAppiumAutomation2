package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ElementPresentTests extends CoreTestCase
{
    /* Ex6 */
    @Test
    public void testArticleTitlePresent()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Osho");
        SearchPageObject.clickByArticleWithSubstring("Rajneesh");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.assertArticleTitleExists();
    }
    /* Ex6 */
}
