package com.project.routorybackend.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.lang.NonNull;

import java.util.concurrent.TimeUnit;


@Slf4j
@Configuration
@TraceIdLog
@RequiredArgsConstructor
public class MongoDBConfig extends AbstractMongoClientConfiguration {
    private final MongoProperties mongoProperties;

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        builder.applyConnectionString(new ConnectionString(mongoProperties.getUri()))
                .applyToSocketSettings(socketBuilder ->
                        socketBuilder.connectTimeout(5, TimeUnit.SECONDS)
                )
                .applyToClusterSettings(clusterBuilder ->
                        clusterBuilder.serverSelectionTimeout(5, TimeUnit.SECONDS)
                )
                .build();
    }

    @NonNull
    @Override
    protected String getDatabaseName() {
        return mongoProperties.getDatabase();
    }

    @Bean
    public ApplicationRunner mongoConnectionRunner() {
        return _ -> {
            try (final MongoClient mongoClient = mongoClient()) {
                mongoClient.getDatabase(mongoProperties.getDatabase()).runCommand(new Document("ping", 1));
                log.info("MongoDB 연결 성공");
            } catch (Exception e) {
                log.error("MongoDB 연결 실패", e);
                throw new IllegalStateException("MongoDB 연결 실패", e);
            }
        };
    }
}
