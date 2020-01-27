    package com.systembackpoc.entities.DTO;

    import com.systembackpoc.entities.Users;

    import java.io.Serializable;

    public class UsersDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        private Long id;
        private String name;
        private String email;
        private Boolean active;
        private String userName;
        private String passWord;

        public UsersDTO(){}
        public UsersDTO(Users user) throws Exception {
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.active = user.getActive();
            this.userName = user.getUserName();
            this.passWord = user.getPassWord();
        }

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

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

        public String getPassWord() {
            return passWord;
        }
        public void setPassWord(String passWord) {
            this.passWord = passWord;
        }
    }
