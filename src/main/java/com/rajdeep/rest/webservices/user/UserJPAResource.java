package com.rajdeep.rest.webservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/jpa")
public class UserJPAResource {

	@Autowired
	private UserRepository UserRepository;

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return UserRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		return UserRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = UserRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		UserRepository.deleteById(id);
	}

	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveAllPosts(@PathVariable int id) {
		User user = UserRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
		return user.getPosts();
	}

	@PostMapping("/users/{id}/posts")
	public Post createPost(@PathVariable int id, @RequestBody Post post) {
		User user = UserRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found " + id));
		post.setUser(user);
		return postRepository.save(post);
	}
}
