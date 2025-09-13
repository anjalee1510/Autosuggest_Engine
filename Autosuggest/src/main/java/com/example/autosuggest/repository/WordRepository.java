package com.example.autosuggest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.autosuggest.model.Word;

@Repository
public interface WordRepository extends JpaRepository<Word,Long>{
	Optional<Word> findByText(String text);

}
