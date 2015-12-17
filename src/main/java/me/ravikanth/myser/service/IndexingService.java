package me.ravikanth.myser.service;

import me.raviaknth.myser.resourcemanager.IIndexResourceManager;
import me.raviaknth.myser.resourcemanager.IndexResourceManagerImpl;
import me.ravikanth.myser.model.Doc;
import me.ravikanth.myser.model.IndexResource;

import javax.ws.rs.*;

/**
 * Created by ragudipati on 11/23/15.
 */

@Path("/index")
public class IndexingService {

    @GET
    @Produces("application/json")
    public IndexResource searchResource(@QueryParam("searchString") String searchString){
        IndexResource indexResource ;
        IIndexResourceManager iIndexResourceManager = new IndexResourceManagerImpl();
        indexResource = iIndexResourceManager.getResource(searchString);
        return  indexResource;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public IndexResource indexResource(IndexResource indexResource){
        for(Doc doc : indexResource.getDocList()){
            for(String key : doc.getFields().keySet()){
                System.out.println(key+" : "+doc.getFields().get(key));
            }
        }
        IIndexResourceManager iIndexResourceManager = new IndexResourceManagerImpl();
        iIndexResourceManager.indexResource(indexResource);
        return indexResource;
    }

}
