package com.blog.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.blog.model.Comment;
import com.blog.model.Posts;
import com.blog.service.PostService;

@Controller
@RequestMapping("/posts")
public class PostController{
	
	@Autowired
	PostService postService;
	
	@GetMapping("/create")
	public String showCreatePostForm(Model model) {
		model.addAttribute("post",new Posts());
		return "postForm";
	}
	@PostMapping("/create")
	public String createThePosts(@ModelAttribute Posts post,
								 @RequestParam ("imageFile") MultipartFile imageFile) throws IOException 
	{
								
		if(!imageFile.isEmpty()) {
		byte[]	image=imageFile.getBytes();
		post.setImage(image);
		}
		if(post.getDate()==null) {
			post.setDate(LocalDate.now());
		}
		postService.createPost(post);
		
	return "redirect:/posts/retrive";
		
	}
	@GetMapping("/retrive")
	public String retriveAll(Model model) {
	List<Posts>postList=	postService.getAllPosts();
		
		
		model.addAttribute("posts",postList);
		return "list.html";
	}
	
	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
	    Posts post = postService.getPostById(id);  // Fetch post by ID
	    if (post == null || post.getImage() == null) {
	        return ResponseEntity.notFound().build(); // Return 404 if image not found
	    }

	    byte[] imageBytes = post.getImage();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG); // Or MediaType.IMAGE_PNG if appropriate

	    return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public String getIndividualPost(@PathVariable Long id,Model model,@ModelAttribute Comment comment) {
	Posts posts=	postService.getPostById(id);
	model.addAttribute("post",posts);
	model.addAttribute("comment",comment);
		System.out.println(id);
		return "singlePost.html";
		
	}
	
	@PostMapping
	public String postComment(@ModelAttribute Comment comment) {
		
		return "redirect:/posts/retrive";
	}
	
	  
	
	

	
	
}
