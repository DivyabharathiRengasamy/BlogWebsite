package com.blog.model;

import java.time.LocalDate;
import java.util.*;
import jakarta.persistence.*;
import com.blog.model.Comment;
@Entity
@Table(name = "posts")
public class Posts{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate date;

    @Lob
    @Column(nullable = false,columnDefinition = "TEXT")
    private String content;

    @Lob // Large Object for storing image data
    @Column(name="image")
    private byte[] image; // Blob for image
    
    @ElementCollection
    @CollectionTable(name = "post_tags", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name="tags")
    private List<String> tags;
    
    @OneToMany
    (mappedBy = "posts",cascade = CascadeType.ALL,orphanRemoval = true)
   private List<Comment>commentsList=new   ArrayList<>();
    
     public Posts() {
		// TODO Auto-generated constructor stub
	
	}



	public Posts(Long id, String title, LocalDate date, String content, List<String> tags) {
		super();
		this.id = id;
		this.title = title;
		this.date = date;
		this.content = content;
		this.tags = tags;
	}



	public Posts(Long id, String title, LocalDate date, String content, byte[] image, List<String> tags) {
		super();
		this.id = id;
		this.title = title;
		this.date = date;
		this.content = content;
		this.image = image;
		this.tags = tags;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public byte[] getImage() {
		return image;
	}



	public void setImage(byte[] image) {
		this.image = image;
	}



	public List<String> getTags() {
		return tags;
	}



	public void setTags(List<String> tags) {
		this.tags = tags;
	}
     
	public List<Comment> getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(List<Comment> commentsList) {
		this.commentsList = commentsList;
	}
	}