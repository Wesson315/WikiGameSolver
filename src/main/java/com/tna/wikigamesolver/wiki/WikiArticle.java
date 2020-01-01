package com.tna.wikigamesolver.wiki;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * @author Philip Anderson
 **/
public class WikiArticle {

    private String title;
    private String url;

    public WikiArticle(String title, String url) {
        this.title = title;
        this.url = url;
    }


    public URI toURI() throws URISyntaxException {
        return new URI(getUrl());
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
