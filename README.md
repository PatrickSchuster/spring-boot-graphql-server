# graphql-java-spring-boot-example
Spring Boot Application implementing a GraphQL server and querying an internal H2 database using JOOQ.

# Running
To build the JOOQ schema generated from the schema.sql, execute this maven command:

```$ mvn clean generate-sources -Pgenerate```

This will generate the tables in the gensrc directory.

To run the Spring Boot Application itself, execute `com.example.DemoGraphQL.DemoGraphQlApplication`.

Access graphql by visiting [http://localhost:8080/graphql](http://localhost:8080/graphql) in e.g. POSTMAN or visit [http://localhost:8080/graphiql](http://localhost:8080/graphiql) for the graphiql browser interface.

# Sample queries:

```javascript
{
  findAllBooks{
    title
    isbn
    id
    pageCount
    author{
      id
      firstName
      lastName
    }
  }
}
```
Will yield all books (with their title, isbn, id and pageCount) as well as their author (with their id, first and last name).


```javascript
{
  findBooks(where:{
  or:[
      {eq:["id","10"]},
      {like: ["title", "Illum%"]},
      {and:[
        {eq:["page_count", "900"]},
        {eq:["isbn", "2222"]},
      ]}
    ]
  }) {
    id
    title
    author{
      firstName
      lastName
    }
  }
}
```

Will yield all books (with their id, title and author (first and lastname)) WHERE (id = 10 OR title = Illum%) OR (page_count = 900 AND ISBN = 2222).
