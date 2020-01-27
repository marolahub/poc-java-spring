package com.systembackpoc.services;

import com.systembackpoc.entities.Users;
import com.systembackpoc.exceptions.ExceptionsPoc;
import com.systembackpoc.repositories.UsersRepository;
import com.systembackpoc.utils.Security64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository repository;

    public List<Users> findAllFull(){ return repository.findAll(); }
    public Users findById(Long id){  Optional<Users> user = repository.findById(id);
        return user.orElseThrow(() -> new ExceptionsPoc("Usuário não encontrado!"));
    }
    public Users findByName(String name){ return repository.findByName(name); }
    public Users findByUserName(String userName){ return repository.findByUserName(userName); }
    public Users findByLogin(String userName, String passWord){
        return repository.findByLogin(userName, Security64.encoderBase64(passWord));
    }

    public Users insert(Users user){ return repository.save(user); }
    public void deleteById(Long id){ repository.deleteById(id); }
    public void delete(Users user){ repository.delete(user); }
    public void update(Users user) throws Exception {
        Users newUser = findById(user.getId());
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setUserName(user.getUserName());
        newUser.setPassWord(user.getPassWord());
        repository.save(newUser);
    }
}
