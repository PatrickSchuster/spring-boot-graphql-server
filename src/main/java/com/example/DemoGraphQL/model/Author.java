package com.example.DemoGraphQL.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Author {

    @Id
    @Column(name = "book_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Column(name = "author_first_name", nullable = false)
    @Getter
    @Setter
    private String firstName;

    @Column(name = "author_last_name", nullable = false)
    @Getter
    @Setter
    private String lastName;

    public Author(Long id) {
        this.id = id;
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return id.equals(author.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
