package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.model.Comment;
import com.blog.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired 
	CommentService commentService;
	
	@PostMapping("/{id}")
	public String submitComment(@PathVariable Long id, @ModelAttribute Comment comment, RedirectAttributes redirectAttributes) {
	    // Handle comment saving logic (e.g., save to the database)
	    commentService.saveCommentForPost(id,comment);
	    
	    // Optional: add a success message
	    redirectAttributes.addFlashAttribute("successMessage", "Your comment has been posted!");
	    
	    return "redirect:/";
	}

	
}
