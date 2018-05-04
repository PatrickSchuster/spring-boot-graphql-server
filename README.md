# graphql-java-spring-boot-example
Stripped down Spring Boot Application based on this tutorial: [Building a GraphQL Server with Spring Boot](https://www.pluralsight.com/guides/java-and-j2ee/building-a-graphql-server-with-spring-boot). 


To run, execute `com.example.DemoGraphQL.DemoGraphQlApplication`.

Access graphql by visiting [http://localhost:8080/graphql](http://localhost:8080/graphql) in e.g. POSTMAN or visit [http://localhost:8080/graphiql](http://localhost:8080/graphiql) for the graphiql browser interface.

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

```
{
  findAuthors(filter: [
    	{firstProperty: "firstName", operator: "=", secondProperty: "Stephen"}
  	]) {
    firstName
    lastName
    id
  }
}
```
will yield all authors with firstName = "Stephen".

# Jooq:
The experimental branch uses [JOOQ](https://www.jooq.org/) for building SQL queries instead of using Spring Data repositories.
