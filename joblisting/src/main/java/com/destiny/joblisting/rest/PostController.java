package com.destiny.joblisting.rest;

import com.destiny.joblisting.entity.Joblisting;
import com.destiny.joblisting.repository.JoblistingI;
import com.destiny.joblisting.repository.SearchRepository;
import com.destiny.joblisting.repository.SearchRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PostController {
    @Autowired
    JoblistingI repo;
    @Autowired
    SearchRepository searchRepository;
    @GetMapping("/jobs")
    public List<Joblisting> getAllJobListing(){
        return repo.findAll();

    }
    @GetMapping("/jobs/{text}")
    public List<Joblisting> search(@PathVariable String text){
    return searchRepository.findByText(text);
    }
    @PostMapping("/job")
    public Joblisting postJob(@RequestBody Joblisting joblisting){
        return repo.save(joblisting);
    }
}
