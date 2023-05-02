package com.estudos.concursos.manager;

import com.theokanning.openai.OpenAiResponse;
import com.theokanning.openai.completion.CompletionRequest;
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



        private static String API_KEY = "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";
        private static String PROMPT = "O que e o chat GPT?";
        private static long MAX_TOKENS = 100;
        private static float TEMPERATURE = 1;
        private static String MODEL = "text-davinci-003";



        CompletionRequest request = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt("Escreva um slogan para uma barraca de açaí.")
                .maxTokens(100)
                .build();

        System.out.println(service.createCompletion(request).getChoices());

    }
    }