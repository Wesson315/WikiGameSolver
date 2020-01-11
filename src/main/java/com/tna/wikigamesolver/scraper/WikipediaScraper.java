package com.tna.wikigamesolver.scraper;


import com.tna.wikigamesolver.wiki.WikiArticle;

import java.util.HashSet;
import java.util.concurrent.*;

/**
 * Scrapes the entirety of Wikipedia für all articles and their links.
 * Essentially builds the graph.
 *
 * @author Wesson Snyder
 */
public class WikipediaScraper {

    private final WikiArticle rootArticle;

    public WikipediaScraper() {
        this.rootArticle = new WikiArticle("Big Bang", "Big_Bang");

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new WikipediaScraper().scrapeAll();
    }

    public void scrapeAll() throws ExecutionException, InterruptedException {
        ArticleScraper rootScraper = new ArticleScraper(this.rootArticle);
        ExecutorService service = Executors.newCachedThreadPool();
        scrape(service, rootScraper);
        service.shutdown();


    }

    private void scrape(ExecutorService service, ArticleScraper scraper) throws ExecutionException, InterruptedException {
        Callable<HashSet<WikiArticle>> callable = scraper::scrape;
        Future<HashSet<WikiArticle>> future = service.submit(callable);
        HashSet<WikiArticle> articles = future.get();
        for (WikiArticle article : articles) {
            ArticleScraper nextScraper = new ArticleScraper(article);
            scrape(service, nextScraper);
        }
        System.out.println("Finished scraping " + scraper.getArticle().getUrl());
    }

}
