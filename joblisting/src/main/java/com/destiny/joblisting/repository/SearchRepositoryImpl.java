package com.destiny.joblisting.repository;

import com.destiny.joblisting.entity.Joblisting;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SearchRepositoryImpl implements SearchRepository{
    @Autowired
    MongoClient client;
    @Autowired
    MongoConverter mongoConverter;
    @Override
    public List<Joblisting> findByText(String text) {
        List<Joblisting> joblistings=new ArrayList<>();
        MongoDatabase database = client.getDatabase("Destiny");
        MongoCollection<Document> collection = database.getCollection("joblisting");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                        new Document("$search",
                        new Document("index", "default")
                        .append("text",
                        new Document("query", text)
                        .append("path", Arrays.asList("techs", "desc", "profile")))),
                        new Document("$sort",
                        new Document("exp", 1L)),
                        new Document("$limit", 5L)));
        result.forEach(doc -> joblistings.add(mongoConverter.read(Joblisting.class,doc)));
                        return joblistings;
    }
}
