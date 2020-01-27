package com.systembackpoc.resources;

import com.systembackpoc.entities.Users;
import com.systembackpoc.services.UsersService;
import com.systembackpoc.utils.FormattUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersResource {
    @Autowired
    private UsersService service;

    @GetMapping
    public ResponseEntity<List<Users>> findAll(){
        return ResponseEntity.ok().body(service.findAllFull());
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Users> findById(Long id){
        return ResponseEntity.ok().body(service.findById(id)); }

    @RequestMapping(value = "/{name}", method= RequestMethod.GET)
    public ResponseEntity<Users> findByName(String name){
        name = FormattUtils.decodeParam(name);
        return ResponseEntity.ok().body(service.findByName(name));
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<Users> findByUserName(String userName){
        userName = FormattUtils.decodeParam(userName);
        return ResponseEntity.ok().body(service.findByUserName(userName));
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Users findByLogin(
            @RequestParam(value = "username", defaultValue = "") String userName,
            @RequestParam(value = "password", defaultValue = "") String passWord){
        userName = FormattUtils.decodeParam(userName);
        passWord = FormattUtils.decodeParam(passWord);
        return service.findByLogin(userName, passWord);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Users user){
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(@RequestBody Users user){
        service.delete(user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody Users user, @PathVariable Long id) throws Exception {
        user.setId(id);
        service.update(user);
        return ResponseEntity.noContent().build();
    }

}
