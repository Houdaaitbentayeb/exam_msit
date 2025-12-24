package org.example.mediaserver.dao.repositories;

import org.example.mediaserver.dao.entities.CreatorEntity;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CreatorRepository {

    private final Map<String, CreatorEntity> creators = new ConcurrentHashMap<>();

    public CreatorEntity save(CreatorEntity creator) {
        if (creator.getId() == null || creator.getId().isEmpty()) {
            creator.setId(UUID.randomUUID().toString());
        }
        creators.put(creator.getId(), creator);
        return creator;
    }

    public CreatorEntity findById(String id) {
        return creators.get(id);
    }

    public List<CreatorEntity> findAll() {
        return new ArrayList<>(creators.values());
    }

    public boolean existsById(String id) {
        return creators.containsKey(id);
    }
}