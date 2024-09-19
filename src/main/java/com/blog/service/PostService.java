package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.repository.PostRepository;
import com.blog.model.*;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Posts createPost(Posts post) {
        return postRepository.save(post);
    }

    public List<Posts> getAllPosts() {
        return postRepository.findAll();
    }

    public Posts getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Posts updatePost(Posts post) {
        return postRepository.save(post);
    }

    public boolean deletePost(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

//	public List<Posts> searchPosts(String query) {
//		// TODO Auto-generated method stub
//		return postRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(query, query);
//	}
}
