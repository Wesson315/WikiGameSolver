package com.tna.wikigamesolver.scraper;

import com.tna.wikigamesolver.other.Constants;
import com.tna.wikigamesolver.wiki.WikiArticle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashSet;


/**
 * Class which scrapes one Wikipedia Article for all links, that lead to another article.
 *
 * @author Wesson Snyder
 */
public class ArticleScraper {


    private WikiArticle article;


    public ArticleScraper(WikiArticle article) {
        this.article = article;
    }

    /**
     * Performs the scraping.
     *
     * @return A Hashset of all uniquie articles, which this article links to.
     */
    public HashSet<WikiArticle> scrape() throws IOException {
        if (article == null) throw new NullPointerException("Article cannot be null");
        HashSet<WikiArticle> result = new HashSet<>();
        Document articleDocument = Jsoup.connect(article.getUrl()).userAgent("Mozilla").get();
        for (Element a : articleDocument.select("a")) {
            if (!isValidWikipediaArticle(a)) continue;
            String url = a.attr("abs:href");
            String keyword = url.substring(Constants.WIKIPEDIA_URL.length());
            WikiArticle article = new WikiArticle(keyword, keyword);
            result.add(article);
        }
        return result;
    }


    private boolean isValidWikipediaArticle(Element a) {
        String href = a.attr("abs:href");
        if (a.text() == null || a.text().length() < 1) return false;
        //Links have to be wiki articles
        if (!href.startsWith(Constants.WIKIPEDIA_URL)) return false;
        //Articles cannot be disambiguation pages
        if (href.endsWith("(disambiguation)")) return false;

        return !href.contains("#") && !href.contains("Portal:");

        //More checks to come
        // TODO: 01/01/2020 Add Checks
    }


    public WikiArticle getArticle() {
        return article;
    }

    public void setArticle(WikiArticle article) {
        this.article = article;
    }
}
