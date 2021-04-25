package com.notesrest.demo.daos;

import org.springframework.data.repository.CrudRepository;

import com.notesrest.demo.models.Note;

public interface NotesRepository extends CrudRepository<Note, Long>{

}
