package com.example.autosuggest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.autosuggest.model.LRUCache;
import com.example.autosuggest.model.Trie;
import com.example.autosuggest.model.Word;
import com.example.autosuggest.repository.WordRepository;

@Service
public class WordService {
	private final WordRepository wordRepository;
	private final LRUCache<String,Word> cache;
	private final Trie trie;
	public WordService(WordRepository wordRepository) {
		this.wordRepository=wordRepository;
		this.cache=new LRUCache<>(100);
		this.trie=new Trie();
	}
	
	/**
	 * Add a new word to DB, Trie and Cache
	 */
	public Word addWord(String text) {
		//1. Check if word already exists in DB
		Optional<Word> existing=wordRepository.findByText(text);
		if(existing.isPresent()) {
			return existing.get();
		}
		//2. Save in DB
		Word word=new Word();
		word.setText(text);
		Word saved=wordRepository.save(word);
		
		//3. Insert into Trie
		trie.insert(text);
		
		//4. put in Cache
		cache.put(text, saved);
		
		
		return saved;
	}
	
	/**
	 * Get word from Cache -> DB (if not present)
	 * Updates cache and trie
	 */
	
	public Word getWord(String text) {
		//1. Check in Cache
		if(cache.containsKey(text)) {
			return cache.get(text);
		}
		
		//2. If not in cache, check DB
		Optional<Word> wordOpt=wordRepository.findByText(text);
		if(wordOpt.isPresent()) {
			Word word=wordOpt.get();
			
			//3. Put in cache
			cache.put(text, word);
			
			//4. Also insert into Trie if not already
			trie.insert(text);
			 return word;
		}
		return null;
	}
	
	/**
	 * Get auto-suggestions from Trie
	 */
	
	public List<String> getSuggestions(String prefix){
		return trie.getWordsWithPrefix(prefix);
	}

}
