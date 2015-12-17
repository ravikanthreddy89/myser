package me.raviaknth.myser.resourcemanager;

import me.ravikanth.myser.model.IndexResource;

/**
 * Created by ragudipati on 11/23/15.
 */
public interface IIndexResourceManager {

    public void indexResource(IndexResource indexResource);

    public IndexResource getResource(String query);
}
