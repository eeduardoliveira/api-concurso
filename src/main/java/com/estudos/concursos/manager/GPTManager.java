package com.estudos.concursos.manager;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@Service
public class GPTManager {
    private static final String API_ENDPOINT = "https://api.openai.com/v1/engines/davinci-codex/completions";
    private final String apiKey = "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";



    public CompletableFuture<String> generateText(String prompt) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(API_ENDPOINT))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString("{\"prompt\": \"" + prompt + "\", \"max_tokens\": 100, \"n\": 1, \"stop\": \"###\"}"))
                    .build();
        } catch (URISyntaxException e) {
            CompletableFuture<String> future = new CompletableFuture<>();
            future.completeExceptionally(e);
            return future;
        }

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(e -> {
                    if (e instanceof IOException) {
                        // IOExceptions are caused by problems with the network.
                        // Wrap them in a RuntimeException so they can be caught by the calling code.
                        throw new RuntimeException(e);
                    }
                    throw new RuntimeException("Failed to generate text: " + e.getMessage(), e);
                });
    }
}
