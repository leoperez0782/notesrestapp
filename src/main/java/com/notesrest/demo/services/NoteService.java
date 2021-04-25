package com.notesrest.demo.services;

import java.util.Optional;

import com.notesrest.demo.models.Note;

public interface NoteService {
	Iterable<Note> findAll();
	Long save(Note note);
	void deleteById(Long id);
	Optional<Note> findById(Long id);
}
