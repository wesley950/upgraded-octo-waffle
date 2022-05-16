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

import com.example.chan.models.ThreadModel;
import com.example.chan.repositories.ThreadRepository;

@RestController
@RequestMapping("/t/")
public class ThreadController {

	@Autowired
	private ThreadRepository threadRepository;
	
	@CrossOrigin
	@GetMapping
	public ThreadModel getThread(@RequestParam Long threadID) {
		return threadRepository.findById(threadID).get();
	}
	
	@CrossOrigin
	@GetMapping("popular/")
	public List<ThreadModel> getPopular() {
		return threadRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "points"))).getContent();
	}
	
	@CrossOrigin
	@GetMapping("new/")
	public List<ThreadModel> getNew() {
		return threadRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"))).getContent();
	}

	@CrossOrigin
	@PostMapping("submit/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ThreadModel createThread(@RequestBody ThreadModel thread) {
		return threadRepository.save(thread);
	}
	
	@CrossOrigin
	@PostMapping("like/")
	public ThreadModel likeThread(@RequestParam Long threadID) {
		ThreadModel thread = threadRepository.getById(threadID);
		thread.setPoints(thread.getPoints() + 1);
		return threadRepository.save(thread);
	}
}
