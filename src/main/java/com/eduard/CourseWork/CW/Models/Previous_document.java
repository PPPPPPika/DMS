package com.eduard.CourseWork.CW.Models;

import javax.persistence.*;

@Entity
@Table(name = "previous_documents")
public class Previous_document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_previous_document")
    private long id_previous_document;

    @Column(name = "name_previous_document", nullable = false, length = 40)
    private String name_previous_document;

    @Column(name = "author_previous_document", nullable = false, length = 40)
    private String author_previous_document;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "previous_version_document", nullable = false)
    private Document previous_version_document;

    @Column(name = "path_document", nullable = false, length = 200)
    private String path_document;

    public Previous_document(){

    }

    public Previous_document(long id_previous_document) {
        this.id_previous_document = id_previous_document;
    }

    public Previous_document(long id_previous_document, String name_previous_document) {
        this.id_previous_document = id_previous_document;
        this.name_previous_document = name_previous_document;
    }

    public Previous_document(long id_previous_document, String name_previous_document, String author_previous_document) {
        this.id_previous_document = id_previous_document;
        this.name_previous_document = name_previous_document;
        this.author_previous_document = author_previous_document;
    }

    public Previous_document(long id_previous_document, String name_previous_document, String author_previous_document, String path_document) {
        this.id_previous_document = id_previous_document;
        this.name_previous_document = name_previous_document;
        this.author_previous_document = author_previous_document;
        this.path_document = path_document;
    }

    public Previous_document(long id_previous_document, String name_previous_document, String author_previous_document, Document previous_version_document, String path_document) {
        this.id_previous_document = id_previous_document;
        this.name_previous_document = name_previous_document;
        this.author_previous_document = author_previous_document;
        this.previous_version_document = previous_version_document;
        this.path_document = path_document;
    }

    public long getId_previous_document() {
        return id_previous_document;
    }

    public void setId_previous_document(long id_previous_document) {
        this.id_previous_document = id_previous_document;
    }

    public String getName_previous_document() {
        return name_previous_document;
    }

    public void setName_previous_document(String name_previous_document) {
        this.name_previous_document = name_previous_document;
    }

    public String getAuthor_previous_document() {
        return author_previous_document;
    }

    public void setAuthor_previous_document(String author_previous_document) {
        this.author_previous_document = author_previous_document;
    }

    public Document getPrevious_version_document() {
        return previous_version_document;
    }

    public void setPrevious_version_document(Document previous_version_document) {
        this.previous_version_document = previous_version_document;
    }

    public String getPath_document() {
        return path_document;
    }

    public void setPath_document(String path_document) {
        this.path_document = path_document;
    }
}
