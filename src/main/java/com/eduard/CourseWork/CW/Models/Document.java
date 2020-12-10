package com.eduard.CourseWork.CW.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_document")
    private Long id;

    @Column(name = "name_document", nullable = false, length = 40)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_document", nullable = false)
    private User author_document;

    @Column(name = "current_version_document", nullable = false)
    private String currentVersion;

    @OneToMany(mappedBy = "currentDocument", fetch = FetchType.LAZY)
    private List<Previous_document> previous_versions_document;

    @Column(name = "path_document", nullable = false, length = 200)
    private String path;

    public Document(){

    }

    public Document(Long id) {
        this.id = id;
    }

    public Document(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Document(Long id, String name, User author_document) {
        this.id = id;
        this.name = name;
        this.author_document = author_document;
    }

    public Document(String name, User author_document, String currentVersion, String path) {
        this.name = name;
        this.author_document = author_document;
        this.currentVersion = currentVersion;
        this.path = path;
    }

    public Document(Long id, String name, User author_document, String currentVersion, String path) {
        this.id = id;
        this.name = name;
        this.author_document = author_document;
        this.currentVersion = currentVersion;
        this.path = path;
    }

    public Document(Long id, String name, User author_document, String currentVersion, List<Previous_document> previous_versions_document, String path) {
        this.id = id;
        this.name = name;
        this.author_document = author_document;
        this.currentVersion = currentVersion;
        this.previous_versions_document = previous_versions_document;
        this.path = path;
    }

    public Document(String name, User author_document, String currentVersion, List<Previous_document> previous_versions_document, String path) {
        this.name = name;
        this.author_document = author_document;
        this.currentVersion = currentVersion;
        this.previous_versions_document = previous_versions_document;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id_document) {
        this.id = id_document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name_document) {
        this.name = name_document;
    }

    public User getAuthor_document() {
        return author_document;
    }

    public void setAuthor_document(User author_document) {
        this.author_document = author_document;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String current_version_document) {
        this.currentVersion = current_version_document;
    }

    public List<Previous_document> getPrevious_versions_document() {
        return previous_versions_document;
    }

    public void setPrevious_versions_document(List<Previous_document> previous_versions_document) {
        this.previous_versions_document = previous_versions_document;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path_document) {
        this.path = path_document;
    }
}

