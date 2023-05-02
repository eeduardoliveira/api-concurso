package com.estudos.concursos.manager;

import com.estudos.concursos.domain.model.DisciplinaResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

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
        String sugestoesConteudo = extrairSugestoesConteudo(respostaApi).toString();
//        String passoAPassoEstudo = extrairPassoAPassoEstudo(respostaApi);

        // adiciona as sugestões de conteúdo programático e o passo a passo de estudo a uma lista e retorna
//        List<String> sugestoesDeEstudo = new ArrayList<>();
//        sugestoesDeEstudo.addAll(sugestoesConteudo);
//        sugestoesDeEstudo.add(passoAPassoEstudo);
        return Collections.singletonList(sugestoesConteudo);
    }

    private String chamarApiGpt3(String nomeDisciplina) {
        gptManager.generateText(nomeDisciplina);
        return "Sugestões de conteúdo programático e passo a passo de estudo para " + nomeDisciplina;
    }

    private DisciplinaResponse extrairSugestoesConteudo(String respostaApi)  {

        return new DisciplinaResponse(
                respostaApi
        );
    }


    private String extrairPassoAPassoEstudo(String respostaApi) {
        // extrai o passo a passo de estudo da resposta da API
        // o código para extrair o passo a passo deve ser implementado aqui
        return "Passo a passo de estudo para " + respostaApi;
    }
}
