# graphql-java-spring-boot-example
Stripped down Spring Boot Application based on this tutorial: [Building a GraphQL Server with Spring Boot](https://www.pluralsight.com/guides/java-and-j2ee/building-a-graphql-server-with-spring-boot). 


To run, execute `com.example.DemoGraphQL.DemoGraphQlApplication`.

Access graphql by visiting [http://localhost:8080/graphql](http://localhost:8080/graphql) in e.g. POSTMAN or visit [http://localhost:8080/graphiql](http://localhost:8080/graphiql) for the graphiql browser interface.

# JOOQ
Check out my [jooq_experiments](https://github.com/PatrickSchuster/spring-boot-graphql-server/tree/jooq_experiments) branch where I have set up JOOQ to work with graphQL queries.

# Sample queries:

```javascript
{
  findAllBooks {
    id
    title
    author {
      firstName
      lastName
    }
  }
}
```
will yield all books (with their id, title and author), where the author's first and last name will be shown.
