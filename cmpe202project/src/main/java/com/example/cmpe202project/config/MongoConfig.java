package com.example.cmpe202project.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.cmpe202project.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "LMS"; // Change this to your actual database name
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://CMPE202:202@ec2-3-19-246-17.us-east-2.compute.amazonaws.com:27017/LMS"); // Change this to your MongoDB connection string
    }
}
/*db.dropUser("your_username")
db.createUser(
{
    user: "CMPE202",
            pwd: "202",
        roles: [ { role: "userAdminAnyDatabase", db: "admin" }, "readWriteAnyDatabase" ]
}
)*/