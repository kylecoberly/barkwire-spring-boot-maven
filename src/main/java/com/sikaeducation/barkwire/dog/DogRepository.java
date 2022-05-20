package com.sikaeducation.barkwire.dog;

import com.sikaeducation.barkwire.dog.Dog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends CrudRepository<Dog, Long> {}
