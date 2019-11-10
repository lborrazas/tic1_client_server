package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tic1.commons.business.exceptions.ResourceNotFoundException;

import tic1.server.entities.User;
import tic1.server.persistence.UserRepository;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserMgr {
    @Autowired
    UserRepository userRepository;
    public List<User> findAll(){ return userRepository.findAll(); }

    public  void save(User user){
        userRepository.save(user);

    }
    public User getOne(@PathVariable("id") Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
    }
    public void updateUser(@PathVariable("id") Long id, @Valid @RequestBody User tempUser){
       User existingUser= userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        existingUser.setPassword(tempUser.getPassword());
        existingUser.setUsername(tempUser.getUsername());//falta el catcheo de exepciones ("SKERE")
        userRepository.save(existingUser);
    }

    public ResponseEntity<?> deleteMovie(@PathVariable("id") Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }

    public List<User> getByname(String username){
        return userRepository.findAllByUsername(username);
    }



}
