package com.example.autosuggest.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.autosuggest.model.Word;
import com.example.autosuggest.service.WordService;

@RestController
@RequestMapping("/api")
public class WordController {
	private final WordService wordService;
	 public WordController(WordService wordService) {
		 this.wordService=wordService;
	 }
	 
	 /**
	  * Add a new word
	  * POST /api/word
	  * Body: { "text": "apple"}
	  */
	 
	 @PostMapping("/words")
	 public ResponseEntity<?> addWord(@RequestBody Word word){
		 if(word==null || word.getText() == null || word.getText().trim().isEmpty()) {
			 return ResponseEntity.badRequest().body(Map.of("error","text must be provided"));
		 }
		 Word saved= wordService.addWord(word.getText());
		 return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	 }
	 
	 /**
	  * Get a word (returns Word entity if present)
	  * GET /api/words/{text}
	  */
	 
	 public ResponseEntity<Word> getWord(@PathVariable String text){
		 Word word=wordService.getWord(text);
		 if(word!= null) {
			 return ResponseEntity.ok(word);
			 
		 }else {
			 return ResponseEntity.notFound().build();
		 }
		 
	 }
}
