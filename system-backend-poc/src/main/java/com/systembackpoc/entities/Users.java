package com.systembackpoc.entities;

import com.systembackpoc.utils.Security64;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "t_users")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Boolean active;
    private String userName;
    private String passWord;

    public Users(){}
    public Users(Clients client, String userName, String passWord){
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.active = client.getActive();
        this.userName = userName;
        this.passWord = Security64.encoderBase64(passWord);
    }

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name;  }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive(){ return this.active; }
    public void setActive(Boolean active){ this.active = active; }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord(){
        return Security64.decoderBase64(passWord);
    }
    public void setPassWord(String passWord) {
        this.passWord = Security64.encoderBase64(passWord);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return getId().equals(users.getId()) &&
                getEmail().equals(users.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }
}
