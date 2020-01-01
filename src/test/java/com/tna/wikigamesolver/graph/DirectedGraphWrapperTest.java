package com.tna.wikigamesolver.graph;

import com.tna.wikigamesolver.wiki.WikiArticle;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Philip Anderson
 **/
class DirectedGraphWrapperTest {


    private DirectedGraphWrapper wrapper;


    @BeforeEach
    void setUp() {
        wrapper = new DirectedGraphWrapper();
        WikiArticle jesus = new WikiArticle("Jesus", "de.wikipedia.org/wiki/Jesus");
        WikiArticle christianity = new WikiArticle("Christianity", "de.wikipedia.org/wiki/Christianity");
        WikiArticle media = new WikiArticle("Media", "de.wikipedia.org/wiki/Media");
        WikiArticle religion = new WikiArticle("Religion", "de.wikipedia.org/wiki/Religion");
        WikiArticle tshirt = new WikiArticle("T-Shirt", "de.wikipedia.org/wiki/T-Shirt");
        wrapper.addVertex(jesus);
        wrapper.addVertex(christianity);
        wrapper.addVertex(media);
        wrapper.addVertex(religion);
        wrapper.addVertex(tshirt);

        wrapper.addEdge(media, religion);
        wrapper.addEdge(religion, christianity);
        wrapper.addEdge(christianity, jesus);
        wrapper.addEdge(jesus, christianity);
        wrapper.addEdge(jesus, religion);
    }

    @Test
    void addVertex() {
        WikiArticle chocolate = new WikiArticle("Chocolate", "de.wikipedia.org/wiki/Chocolate");
        WikiArticle book = new WikiArticle("Book", "de.wikipedia.org/wiki/Book");
        wrapper.addVertex(chocolate);
        wrapper.addVertex(book);
        assertTrue(getGraph().containsVertex(chocolate));
        assertTrue(getGraph().containsVertex(book));
    }

    @Test
    void removeVertex() {

        WikiArticle religion = new WikiArticle("Jesus", "de.wikipedia.org/wiki/Religion");
        wrapper.removeVertex(religion);
        assertFalse(getGraph().containsVertex(religion));
    }

    @Test
    void containsVertex() {
        WikiArticle religion = new WikiArticle("Jesus", "de.wikipedia.org/wiki/Religion");
        assertTrue(wrapper.containsVertex(religion));
    }
    /*

    @Test
    void addEdge() {
    }

    @Test
    void removeEdge() {
    }

    @Test
    void containsEdge() {
    }

    @Test
    void getConnectedVerticies() {
    }

    @Test
    void setStorageStrategy() {
    }

    @Test
    void saveGraph() {
    }

    @Test
    void loadGraph() {
    }*/


    DirectedMultigraph<WikiArticle, DefaultEdge> getGraph() {
        try {
            Field f = wrapper.getClass().getDeclaredField("graph");
            f.setAccessible(true);
            DirectedMultigraph<WikiArticle, DefaultEdge> graph = (DirectedMultigraph<WikiArticle, DefaultEdge>) f.get(wrapper);
            return graph;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}