package com.destiny.joblisting.repository;

import com.destiny.joblisting.entity.Joblisting;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JoblistingI extends MongoRepository<Joblisting,String> {
}
