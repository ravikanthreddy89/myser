package me.ravikanth.myser.service;

import me.ravikanth.myser.model.Doc;
import me.ravikanth.myser.model.IndexResource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

/**
 * Created by ragudipati on 11/23/15.
 */

@Path("/index")
public class IndexingService {

    @GET
    @Produces("application/json")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World";
    }


    @POST
    @Produces("application/json")
    public IndexResource indexResource(IndexResource indexResource){
        for(Doc doc : indexResource.getDocList()){
            for(String key : doc.getFields().keySet()){
                System.out.println(key+" : "+doc.getFields().get(key));
            }
        }
        return indexResource;
    }

}
