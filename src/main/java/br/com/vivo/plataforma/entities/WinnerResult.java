package br.com.vivo.plataforma.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WinnerResult {
    private int posicaoChegada;
    private String codigoSuperHeroi;
    private String nomeSuperHeroi;
    private int quantidadeVoltas;
    private double tempoTotalProva;
}
