package com.tna.wikigamesolver.graph;


import com.tna.wikigamesolver.storage.StorageStrategy;

import java.util.Set;
/**
 * Interface to communicate between Anchors and the Wikipedia Graph.
 *
 * @author Philip Anderson
 **/
public interface WikiGraphAPI<T> {

    /**
     * Adds a data point to the graph.
     *
     * @param data The data to be added.
     * @return <code>true</code> if the data was added successfully.
     */
    boolean addVertex(T data);

    /**
     * Removes a data point from the graph.
     *
     * @param data The data to be added.
     * @return <code>true</code> if the data was removed successfully.
     */
    boolean removeVertex(T data);


    /**
     * Returns <code>true</code> if the specified data is already a vertex in the graph.
     *
     * @param data The data to check.
     * @return <code>true</code> if the data is contained.
     */
    boolean containsVertex(T data);


    /**
     * Removes all vertices and edges from the graph. Essentially resetting it.
     */
    void clearAll();

    /**
     * Adds an edge connection between two data points.
     *
     * @param data1 Data point 1.
     * @param data2 Data point 2.
     * @return <code>true</code> if both data points are contained in the graph and the connection could be made.
     */
    boolean addEdge(T data1, T data2);


    /**
     * Removes an edge connection between two data points.
     *
     * @param data1 Data point 1.
     * @param data2 Data point 2.
     * @return <code>true</code> if the connection was removed.
     */
    boolean removeEdge(T data1, T data2);

    /**
     * Returns true if an edge connection between two points exist.<br>
     * <b>Note: </b><br>
     * In a directed graph, such as in this project, it will check
     * if a connection from <code>data1</code> to <code>data2</code> exists.
     * If there is a connection from <code>data2</code> to <code>data1</code>, it will still return <code>false</code>
     *
     * @param data1 Data point 1.
     * @param data2 Data point 2.
     * @return <code>true</code> if a connection exists between those two points.
     */
    boolean containsEdge(T data1, T data2);


    /**
     * Returns a list of all verticies, which are connected to this data specified data point.
     *
     * @param data The data point.
     * @return The list of all connected data point or <code>null</code> if the data is null.
     */
    Set<T> getConnectedVerticies(T data);


    /**
     * Sets the used storage strategy for a wrapper.<br>
     * See {@link StorageStrategy} for more information about storage strategies.
     *
     * @param strategy The strategy to use by the wrapper.
     */
    void setStorageStrategy(StorageStrategy strategy);


    /**
     * Tells the Wrapper to tell the specified storage strategy to persist the graph.<br>
     * See {@link StorageStrategy} for more information about storage strategies.
     *
     * @return <code>true</code> if the graph was persisted correctly.
     */
    boolean saveGraph();


    /**
     * Tells the Wrapper to tell the specified storage strategy to load the persited graph and set it as its iternal graph.
     * This also means that any graph which was prior held by the wrapper will be deleted.<br>
     * See {@link StorageStrategy} for more information about storage strategies.
     *
     * @return <code>true</code> if the graph was loaded successfully.
     */
    boolean loadGraph();

}
