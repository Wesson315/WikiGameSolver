package com.tna.wikigamesolver.wiki;

import com.tna.wikigamesolver.other.Constants;

import java.util.Objects;

/**
 * Data class which contains the very basic informations of an article.
 * These informations include the title of the article and the url to the article.
 *
 * @author Philip Anderson
 **/
public class WikiArticle {

    private String title;
    private String url;

    public WikiArticle(String title, String url) {
        this.title = title;
        this.url = Constants.WIKIPEDIA_URL + url;
    }


    @Override
    public String toString() {
        return "WikiArticle{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WikiArticle that = (WikiArticle) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
