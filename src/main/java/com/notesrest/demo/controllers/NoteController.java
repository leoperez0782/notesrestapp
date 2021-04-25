package com.notesrest.demo.controllers;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.notesrest.demo.dto.NoteDTO;
import com.notesrest.demo.models.Note;
import com.notesrest.demo.services.NoteService;

@RestController
@RequestMapping(value = "/notes")
public class NoteController {

	private Logger logger = LoggerFactory.getLogger(NoteController.class);
	@Autowired
	private NoteService service;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/all")
	public ResponseEntity<Iterable<NoteDTO>> findAll() {
		Iterable<Note> notes = service.findAll();
		Iterable<NoteDTO> notesDTOIterable = StreamSupport.stream(notes.spliterator(), false)
				.map(this::convertToDTO)
				.collect(Collectors.toList());
		return new ResponseEntity<Iterable<NoteDTO>>(notesDTOIterable, HttpStatus.OK);
	}

	@PostMapping(value = "/save")
	@ResponseBody
	public ResponseEntity<Long> saveNote(@RequestBody NoteDTO note) {
		return saveOrUpdate(note, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update")
	@ResponseBody
	public ResponseEntity<Long> updateNote(@RequestBody NoteDTO note) {
		return saveOrUpdate(note, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Object> delete(@RequestBody NoteDTO note) {

		try {
			if (note == null || service.findById(note.getId()).isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			service.deleteById(note.getId());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.noContent().build();
	}

	private ResponseEntity<Long> saveOrUpdate(NoteDTO note, HttpStatus status) {
		try {
			return new ResponseEntity<Long>(service.save(convertToNote(note)), status);
		}catch(Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
	}

	private NoteDTO convertToDTO(Note model) {
		return modelMapper.map(model, NoteDTO.class);
	}

	private Note convertToNote(NoteDTO dto) {
		return modelMapper.map(dto, Note.class);
	}

}
