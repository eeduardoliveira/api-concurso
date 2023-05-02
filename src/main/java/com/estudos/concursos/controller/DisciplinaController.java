package com.estudos.concursos.controller;

import com.estudos.concursos.domain.model.DisciplinaResponse;
import com.estudos.concursos.manager.DisciplinaManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("disciplina")
@CrossOrigin
public class DisciplinaController {

    @Autowired
    private DisciplinaManager disciplinaManager;



    @GetMapping("/sugestoes/{nomeDisciplina}")
    public ResponseEntity<DisciplinaResponse> gerarSugestoesDeEstudo(@PathVariable String nomeDisciplina) {
        String testev = "teste";
        try {
            disciplinaManager.gerarSugestoesDeEstudo(nomeDisciplina);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

