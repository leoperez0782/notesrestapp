package com.notesrest.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notesrest.demo.daos.NotesRepository;
import com.notesrest.demo.models.Note;

@Service
public class NoteServiceImp implements NoteService {

	@Autowired
	private NotesRepository repo;
	
	@Override
	public Iterable<Note> findAll() {
		return repo.findAll();
	}

	@Override
	public Long save(Note note) {
		Note saved = repo.save(note);
		return saved.getId();
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<Note> findById(Long id) {
		return repo.findById(id);
	}

	
}
