package com.example.DemoGraphQL.repository;

import com.example.DemoGraphQL.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findByFirstNameContainingIgnoreCase(String firstName);

    //List<Author> findByFirstNameContainingIgnoreCaseAndByLastNameContainingIgnoreCase(String firstName, String lastName);

}
