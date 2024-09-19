package com.blog.service;


import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.model.Comment;
import com.blog.model.Posts;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
@Service
public class CommentService {

	private static final Supplier<Posts> Posts = null;

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	PostRepository postRepository;
	
	public void saveCommentForPost(Long id,Comment comment) {
	
	Posts post=	postRepository.findById(id).get();
		comment.setPosts(post);
		commentRepository.save(comment);
		
		
	}

}
