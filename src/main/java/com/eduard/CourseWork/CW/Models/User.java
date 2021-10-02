package com.eduard.CourseWork.CW.Models;

import com.eduard.CourseWork.CW.Models.Enums.Role;
import com.eduard.CourseWork.CW.Models.Enums.Status;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "login", length = 40, unique = true)
    private String login;

    @Column(name = "email", length = 40, unique = true)
    private String email;

    @Column(name = "password", length = 200)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", length = 20)
    private Role role;

    @OneToMany(mappedBy = "author_document", fetch = FetchType.LAZY)
    private List<Document> documents;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", length = 20)
    private Status status;

    public User(){

    }

    public User(long id) {
        this.id = id;
    }

    public User(long id, String login, String email, String password, Role role, List<Document> documents, Status status) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
        this.documents = documents;
        this.status = status;
    }

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

