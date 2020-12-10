package com.eduard.CourseWork.CW.Models;

import javax.persistence.*;

@Entity
@Table(name = "previous_documents")
public class Previous_document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_previous_document")
    private long id;

    @Column(name = "name_previous_document", nullable = false, length = 40)
    private String name;

    @Column(name = "author_previous_document", nullable = false, length = 40)
    private String author;

    @Column(name = "version_previous_document", nullable = false, length = 5)
    private String version;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "previous_version_document")
    private Document currentDocument;

    @Column(name = "path_document", nullable = false, length = 200)
    private String path_document;

    public Previous_document(){

    }

    public Previous_document(long id) {
        this.id = id;
    }

    public Previous_document(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Previous_document(long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public Previous_document(long id, String name, String author, String path_document) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.path_document = path_document;
    }

    public Previous_document(String name, String author, String version, String path_document) {
        this.name = name;
        this.author = author;
        this.version = version;
        this.path_document = path_document;
    }

    public Previous_document(long id, String name, String author, Document currentDocument, String path_document) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.currentDocument = currentDocument;
        this.path_document = path_document;
    }

    public Previous_document(String name, String author, String version, Document currentDocument, String path_document) {
        this.name = name;
        this.author = author;
        this.version = version;
        this.currentDocument = currentDocument;
        this.path_document = path_document;
    }

    public long getId() {
        return id;
    }

    public void setId(long id_previous_document) {
        this.id = id_previous_document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name_previous_document) {
        this.name = name_previous_document;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author_previous_document) {
        this.author = author_previous_document;
    }

    public Document getCurrentDocument() {
        return currentDocument;
    }

    public void setCurrentDocument(Document previous_version_document) {
        this.currentDocument = previous_version_document;
    }

    public String getPath_document() {
        return path_document;
    }

    public void setPath_document(String path_document) {
        this.path_document = path_document;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
