package com.asu_tp.repo;

import com.asu_tp.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    public List<Post> findByTitle(String title);
}
