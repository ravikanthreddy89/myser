package me.raviaknth.myser.resourcemanager;

import me.ravikanth.myser.model.Doc;
import me.ravikanth.myser.model.IndexResource;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.*;

/**
 * Created by ragudipati on 11/23/15.
 */
public class IndexResourceManagerImpl implements IIndexResourceManager {

    /* *
     * Index method
     */
    public void indexResource(IndexResource indexResource) {
        Analyzer analyzer ;
        IndexWriter indexWriter;
        IndexWriterConfig writerConfig;
        Directory fsDirectory ;
        try {
            analyzer = new StandardAnalyzer();
            fsDirectory = FSDirectory.open(FileSystems.getDefault().getPath("/Users/ragudipati/IdeaProjects3/luceneIndex", "urlsIndex.lucene"));
            writerConfig = new IndexWriterConfig(analyzer);
            indexWriter = new IndexWriter(fsDirectory, writerConfig);

            // iterate over the indexResource documents and index them
            for(Doc doc : indexResource.getDocList()){
                Document document = new Document();
                for(String key : doc.getFields().keySet()){
                    document.add(new TextField(key, doc.getFields().get(key), Field.Store.YES));
                }
                indexWriter.addDocument(document);
            }
            indexWriter.commit();
            indexWriter.close();
            fsDirectory.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Search method
     */
    public IndexResource getResource(String query) {

        IndexResource indexResource = null;
        Analyzer analyzer;
        Directory directory;
        IndexReader indexReader;
        QueryParser queryParser;
        IndexSearcher indexSearcher;


        try{
            directory = FSDirectory.open(FileSystems.getDefault().getPath("/Users/ragudipati/IdeaProjects3/luceneIndex", "urlsIndex.lucene"));
            analyzer = new StandardAnalyzer();
            indexReader = DirectoryReader.open(directory);

            indexSearcher = new IndexSearcher(indexReader);

            queryParser = new QueryParser("tags",analyzer);
            Query q = queryParser.parse(query);

            TopScoreDocCollector topScoreDocCollector = TopScoreDocCollector.create(3);
            indexSearcher.search(q, topScoreDocCollector);

            ScoreDoc [] hits = topScoreDocCollector.topDocs().scoreDocs;

            indexResource = new IndexResource();
            List<Doc> docList = new ArrayList<Doc>();

            for(int i=0;i<hits.length;i++){
                int docId = hits[i].doc;

                Document document = indexSearcher.doc(docId);
                Map<String , String> fields = new HashMap<String, String>();
                Iterator<IndexableField> indexableFieldIterator = document.iterator();
                while(indexableFieldIterator.hasNext()){
                    IndexableField indexableField = indexableFieldIterator.next();
                    fields.put(indexableField.name(), indexableField.stringValue());
                }
                Doc doc = new Doc();
                doc.setFields(fields);
                docList.add(doc);

            }
            indexResource.setDocList(docList);
            indexReader.close();

        }catch (IOException ioe){
            ioe.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return indexResource;
    }
}
