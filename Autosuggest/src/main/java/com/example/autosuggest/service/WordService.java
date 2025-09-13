package com.example.autosuggest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.autosuggest.model.Word;

@Service
public class WordService {
	public WordService() {}
	
	/**
	 * Add a new word to DB, Trie and Cache
	 */
	public Word addWord(String text) {
		return null;
	}
	
	/**
	 * Get word from Cache -> DB (if not present)
	 * Updates cache and trie
	 */
	
	public Word getWord(String text) {
		return null;
	}
	
	/**
	 * Get auto-suggestions from Trie
	 */
	
	public List<String> getSuggestions(String prefix){
		return null;
	}

}
