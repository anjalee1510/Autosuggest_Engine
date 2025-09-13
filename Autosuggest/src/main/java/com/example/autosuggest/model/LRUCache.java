package com.example.autosuggest.model;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K,V> {
	private final int capacity;
	private final Map<K,Node<K,V>> cache;
	private final Node<K,V> head,tail;
	
	public LRUCache(int capacity) {
		this.capacity=capacity;
		this.cache=new HashMap<>();
		this.head=new Node<>(null,null);
		this.tail=new Node<>(null,null);
		head.next=tail;
		tail.prev=head;
	}
	
	public boolean containsKey(K key) {
		return cache.containsKey(key);
	}
	public V get(K key) {
		if(!cache.containsKey(key)) {
			return null;
		}
		Node<K,V> node=cache.get(key);
		moveToHead(node); // recently used
		return node.value;
	}
	
	public void put(K key,V value) {
		if(cache.containsKey(key)) {
			Node<K,V> node = cache.get(key);
			node.value=value;
			moveToHead(node);
		} else {
			Node<K,V> newNode = new Node<>(key,value);
			cache.put(key,  newNode);
			addNode(newNode);
			
			if(cache.size() > capacity) {
				Node<K,V> lru=popTail();
				cache.remove(lru.key);
				
			}
		}
	}
	private Node<K, V> popTail() {
		Node<K,V> res= tail.prev;
		removeNode(res);
		return res;
	}
	private void addNode(Node<K, V> newNode) {
		newNode.prev=head;
		newNode.next=head.next;
		head.next.prev=newNode;
		head.next=newNode;
		
	}
	private void moveToHead(Node<K, V> node) {
	 removeNode(node);
	 addNode(node);
	}
	private void removeNode(Node<K, V> node) {
		Node<K,V> prev= node.prev;
		Node<K,V> next= node.next;
		prev.next=next;
		next.prev=prev;
	}
	
}
