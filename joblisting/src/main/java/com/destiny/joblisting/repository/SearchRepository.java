package com.destiny.joblisting.repository;

import com.destiny.joblisting.entity.Joblisting;

import java.util.List;

public interface SearchRepository {
    List<Joblisting> findByText(String text);
}
