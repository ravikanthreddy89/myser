package me.ravikanth.myser.model;

import me.ravikanth.myser.model.Doc;

import java.util.List;

/**
 * Created by ragudipati on 11/22/15.
 */
public class IndexResource {

    private List<Doc> docList;


    public List<Doc> getDocList() {
        return docList;
    }

    public void setDocList(List<Doc> docList) {
        this.docList = docList;
    }
}
