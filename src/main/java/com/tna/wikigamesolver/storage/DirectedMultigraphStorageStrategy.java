package com.tna.wikigamesolver.storage;

import com.tna.wikigamesolver.wiki.WikiArticle;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;

/**
 * @author Philip Anderson
 **/
public interface DirectedMultigraphStorageStrategy extends StorageStrategy<String, DirectedMultigraph<WikiArticle, DefaultEdge>> {


}
