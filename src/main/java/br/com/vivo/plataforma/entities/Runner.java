package br.com.vivo.plataforma.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Runner {
    private Date hora;
    private String superHeroi;
    private int volta;
    private Date tempoVolta;
    private Double velocidadeMediaVolta;
}
