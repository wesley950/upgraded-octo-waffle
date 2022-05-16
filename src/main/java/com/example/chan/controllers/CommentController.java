package com.example.chan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.chan.models.CommentModel;
import com.example.chan.repositories.CommentRepository;

@RestController
@RequestMapping("/c/")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;

	@CrossOrigin
	@GetMapping
	public List<CommentModel> getComments() {
		return commentRepository.findAll(PageRequest.of(0, 10)).getContent();
	}
	
	@CrossOrigin
	@GetMapping("new/")
	public List<CommentModel> getNewComments(@RequestParam Long threadID) {
		return commentRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"))).getContent();
	}
	
	@CrossOrigin
	@GetMapping("popular/")
	public List<CommentModel> getPopularComments(@RequestParam Long threadID) {
		return commentRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "points"))).getContent();
	}
	
	@CrossOrigin
	@PostMapping("submit/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CommentModel createComment(@RequestBody CommentModel comment) {
		return commentRepository.save(comment);
	}
	
	@CrossOrigin
	@PostMapping("like/")
	public CommentModel likeComment(@RequestParam Long commentID) {
		CommentModel comment = commentRepository.getById(commentID);
		comment.setPoints(comment.getPoints() + 1);
		return commentRepository.save(comment);
	}
	
}
