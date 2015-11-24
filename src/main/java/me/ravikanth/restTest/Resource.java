package me.ravikanth.restTest;

import me.ravikanth.myser.model.Doc;
import me.ravikanth.myser.model.IndexResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by ragudipati on 11/22/15.
 */

@Path("/resource")
public class Resource {


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public User addUser(User user){
        System.out.println("Name "+user.getName());
        System.out.println("LastName : "+user.getLastName());
        System.out.println("DOB : "+user.getDob());
        System.out.println("Age : "+user.getAge());

        for(String key : user.getAliases().keySet()){
            System.out.println( key+" : "+user.getAliases().get(key));
        }

        for(Pojo pojo : user.getPojos()){
            System.out.println("Pojo Name : "+pojo.getName());
            for(String key   : pojo.getMap().keySet()){
                System.out.println("Pojo : "+key + " >> "+pojo.getMap().get(key));
            }
        }

        user.setUid(Math.random());
        return user;
    }

    @Path("/index")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public IndexResource indexResource(IndexResource indexResource){

        for(Doc doc : indexResource.getDocList()){

            for(String string : doc.getFields().keySet()){
                System.out.println(string+" : "+doc.getFields().get(string));
            }
        }

        return indexResource;
    }
}
