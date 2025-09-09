package com.example.autosuggest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.autosuggest.model.Word;

public interface WordRepository extends JpaRepository<Word,Long>{

}
