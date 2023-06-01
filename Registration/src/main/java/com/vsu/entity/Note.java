package com.vsu.entity;

import java.util.Objects;

public class Note {
    private Long id;
    private String text;
    private String timeCreation;
    private Long idUser;

    public Note() {
    }

    public Note(String text, String timeCreation, Long idUser) {
        this.text = text;
        this.timeCreation = timeCreation;
        this.idUser = idUser;
    }

    public Note(Long id, String text, String timeCreation, Long idUser) {
        this.id = id;
        this.text = text;
        this.timeCreation = timeCreation;
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimeCreation() {
        return timeCreation;
    }

    public void setTimeCreation(String timeCreation) {
        this.timeCreation = timeCreation;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(getId(), note.getId()) && Objects.equals(getText(), note.getText()) && Objects.equals(getTimeCreation(), note.getTimeCreation()) && Objects.equals(getIdUser(), note.getIdUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), getTimeCreation(), getIdUser());
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", timeCreation='" + timeCreation + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
