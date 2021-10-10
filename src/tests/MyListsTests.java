package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private static final String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);;
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
        ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleTitleToMySaved();
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()) {
        MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    /* Ex5 */
    @Test
    public void testAddDeleteArticlesInMyFolder()
    {
        String folder_name = "My folder";
        String search_input = "Kotlin";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_input);
        SearchPageObject.clickByArticleWithSubstring("Class of Soviet cold-war destroyers");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);;
        ArticlePageObject.waitForTitleElement();
        String article_title_for_deletion = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToMyList(folder_name);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_input);
        SearchPageObject.clickByArticleWithSubstring("Programming language");
        ArticlePageObject.waitForTitleElement();
        String article_title_to_test_in_folder = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToExistingFolder(folder_name);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();
        NavigationUI.waitForMyListsPageToRender();
        NavigationUI.clickFolderNameInMyLists(folder_name);

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.swipeByArticleToDelete(article_title_for_deletion);
        MyListsPageObject.waitForArticleToDisappearByTitle(article_title_for_deletion);
        MyListsPageObject.waitForArticleToAppearByTitle(article_title_to_test_in_folder);

        MyListsPageObject.clickArticleByTitle(article_title_to_test_in_folder);
        ArticlePageObject.waitForTitleElement();
        String result_article_title = ArticlePageObject.getArticleTitle();

        assertEquals("Article title is wrong :'" + result_article_title + "'",
                article_title_to_test_in_folder,
                result_article_title);
    }
    /* Ex5 */
}
