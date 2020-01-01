package com.tna.wikigamesolver.graph;

import com.tna.wikigamesolver.other.Constants;
import com.tna.wikigamesolver.storage.DirectedMultigraphStorageStrategy;
import com.tna.wikigamesolver.storage.StorageStrategy;
import com.tna.wikigamesolver.wiki.WikiArticle;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of a Graph Wrapper for a Directed Graph.
 *
 * @author Philip Anderson
 **/
public class DirectedGraphWrapper implements WikiGraphAPI<WikiArticle>, Serializable {

    private static final long serialVersionUID = 1L;
    private DirectedMultigraph<WikiArticle, DefaultEdge> graph;
    private DirectedMultigraphStorageStrategy storageStrategy;

    public DirectedGraphWrapper() {
        clearAll();
    }

    public boolean addVertex(WikiArticle article) {
        return graph.addVertex(article);
    }

    public boolean removeVertex(WikiArticle article) {
        return graph.removeVertex(article);
    }

    public boolean containsVertex(WikiArticle article) {
        return graph.containsVertex(article);
    }

    public void clearAll() {
        graph = new DirectedMultigraph<>(DefaultEdge.class);
    }

    public boolean addEdge(WikiArticle article1, WikiArticle article2) {
        if (!containsVertex(article1) || !containsVertex(article2)) return false;
        return graph.addEdge(article1, article2) != null;
    }


    public boolean removeEdge(WikiArticle article1, WikiArticle article2) {
        return graph.removeEdge(article1, article2) != null;
    }

    public boolean containsEdge(WikiArticle article1, WikiArticle article2) {
        return graph.containsEdge(article1, article2);
    }

    public Set<WikiArticle> getConnectedVerticies(WikiArticle article) {
        if (article == null) return null;
        HashSet<DefaultEdge> edges = new HashSet<>(graph.edgesOf(article));
        HashSet<WikiArticle> connetedVerticies = new HashSet<>();
        for (DefaultEdge edge : edges) {
            WikiArticle target = graph.getEdgeTarget(edge);
            if (target != null) connetedVerticies.add(target);
        }
        return connetedVerticies;
    }

    @Override
    public void setStorageStrategy(StorageStrategy strategy) {
        if (strategy instanceof DirectedMultigraphStorageStrategy) {
            this.storageStrategy = (DirectedMultigraphStorageStrategy) strategy;
        }
    }

    @Override
    public boolean saveGraph() {
        return this.storageStrategy.save(this.graph);
    }

    @Override
    public boolean loadGraph() {
        DirectedMultigraph<WikiArticle, DefaultEdge> graph = storageStrategy.load(Constants.GRAPH_FILE_NAME);
        if (graph == null) return false;
        this.graph = graph;
        return true;
    }
}
