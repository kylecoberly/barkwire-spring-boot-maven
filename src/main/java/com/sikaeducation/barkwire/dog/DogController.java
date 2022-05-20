package com.sikaeducation.barkwire.dog;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.sikaeducation.barkwire.dog.Dog;
import com.sikaeducation.barkwire.dog.DogService;

import com.sikaeducation.barkwire.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("api/dogs")
public class DogController {
  @Autowired
  private DogService dogService;

  @GetMapping
  public Map<String, List<Dog>> index(){
    List<Dog> dogs = dogService.all();
    Map response = new HashMap<String, List<Dog>>();
    response.put("dogs", dogs);
    return response;
  }

  @GetMapping("/{id}")
  public Map<String, Dog> show(@PathVariable Long id) throws ResourceNotFoundException {
    Dog dog = dogService
      .find(id)
      .orElseThrow(() -> new ResourceNotFoundException("No dog with that ID"));
    Map response = new HashMap<String, Dog>();
    response.put("dog", dog);
    return response;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Dog> create(@Validated @RequestBody Dog dog) {
    Dog createdDog = dogService.create(dog);
    Map response = new HashMap<String, Dog>();
    response.put("dog", createdDog);
    return response;
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Dog> update(@RequestBody Dog dog, @PathVariable Long id) throws ResourceNotFoundException {
    Dog updatedDog = dogService
      .update(dog, dog.getId())
      .orElseThrow(() -> new ResourceNotFoundException("No dog with that ID"));
    Map response = new HashMap<String, Dog>();
    response.put("dog", updatedDog);
    return response;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void destroy(@PathVariable Long id) {
    dogService.delete(id);
  }
}
