package com.example.autosuggest.model;

import java.util.ArrayList;
import java.util.List;

public class Trie {
	private final TrieNode root=new TrieNode();
	
	public void insert(String word) {
		TrieNode node=root;
		for(char c:word.toCharArray()) {
			node.children.putIfAbsent(c, new TrieNode());
			node=node.children.get(c);
		}
		node.isEndOfWord=true;
	}
	
	public List<String> getWordsWithPrefix(String prefix){
		List<String> results=new ArrayList<>();
		TrieNode node=root;
		 for(char c:prefix.toCharArray()) {
			 if(!node.children.containsKey(c)) {
				 return results; // This means no word with the given prefix- empty result set
			 }
			 node=node.children.get(c);
		 }
		 dfs(node, prefix, results);
		 return results;
	}
	
	private void dfs(TrieNode node, String word, List<String> results) {
		if(node.isEndOfWord) results.add(word);
		for(char c : node.children.keySet()) {
			dfs(node.children.get(c),word+c,results);
		}
	}

}
