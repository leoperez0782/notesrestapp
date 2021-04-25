package com.notesrest.demo.dto;

import java.util.List;

public class UserDTO {
	private String email;
    private Long id;
    private boolean isValidated;
    private List<NoteDTO> notes;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isValidated() {
		return isValidated;
	}
	public void setValidated(boolean isValidated) {
		this.isValidated = isValidated;
	}
	public List<NoteDTO> getNotes() {
		return notes;
	}
	public void setNotes(List<NoteDTO> notes) {
		this.notes = notes;
	}
    
        
}
