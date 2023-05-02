package com.estudos.concursos.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author eduardooliveira
 */

@Service
public class DisciplinaManager {

    @Autowired
    private GPTManager gptManager;
    public List<String> gerarSugestoesDeEstudo(String nomeDisciplina) throws JsonProcessingException {
        // faz a chamada para a API do GPT-3.5-turbo usando OpenAI
        String respostaApi = chamarApiGpt3(nomeDisciplina);

        // extrai as sugestões de conteúdo programático e o passo a passo de estudo da resposta da API
        List<String> sugestoesConteudo = extrairSugestoesConteudo(respostaApi);
//        String passoAPassoEstudo = extrairPassoAPassoEstudo(respostaApi);

        // adiciona as sugestões de conteúdo programático e o passo a passo de estudo a uma lista e retorna
        List<String> sugestoesDeEstudo = new ArrayList<>();
        sugestoesDeEstudo.addAll(sugestoesConteudo);
//        sugestoesDeEstudo.add(passoAPassoEstudo);
        return sugestoesDeEstudo;
    }

    private String chamarApiGpt3(String nomeDisciplina) {
        gptManager.generateText(nomeDisciplina);
        return "Sugestões de conteúdo programático e passo a passo de estudo para " + nomeDisciplina;
    }

    private List<String> extrairSugestoesConteudo(String respostaApi) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(respostaApi);
        JsonNode choicesNode = rootNode.get("choices").get(0);
        String text = choicesNode.get("text").asText();
        String[] sugestoes = text.split(";");
        return Arrays.asList(sugestoes);
    }


    private String extrairPassoAPassoEstudo(String respostaApi) {
        // extrai o passo a passo de estudo da resposta da API
        // o código para extrair o passo a passo deve ser implementado aqui
        return "Passo a passo de estudo para " + respostaApi;
    }
}
