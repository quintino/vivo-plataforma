package br.com.vivo.plataforma.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WinnerResult {

    private int posicaoChegada;

    private String codigoSuperHeroi;

    private String nomeSuperHeroi;

    private int quantidadeVoltas;

    @JsonFormat(pattern = "mm:ss.SSS")
    private Date tempoTotalProva;

    public WinnerResult() {
        this.setPosicaoChegada(0);
        this.setCodigoSuperHeroi("");
        this.setNomeSuperHeroi("");
        this.setQuantidadeVoltas(0);
        this.setTempoTotalProva(new Date(0));
    }
}
