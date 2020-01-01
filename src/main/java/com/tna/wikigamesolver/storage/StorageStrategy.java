package com.tna.wikigamesolver.storage;

/**
 * Strategy-Interface for the various persistence methods.
 * Generified to allow flexible saving and loading.<br>
 * <b>I</b> specifies the specifier / identifier of the object (eg. the path to the file, the primary key in a database, etc)<br>
 * <b>D</b> specifies the datatype of the data to be persisted.
 *
 * @author Philip Anderson
 **/
public interface StorageStrategy<I, D> {

    /**
     * Method to persists a data point.
     *
     * @param data The data to be persisted.
     * @return <code>true</code> if the data was persisted correctly.
     */
    boolean save(D data);


    /**
     * Loads a data point over the given identifier.
     *
     * @param identifier The identifier to load the data from.
     * @return The datapoint, of one was found and correctly loaded, else <code>null</code>
     */
    D load(I identifier);


}
