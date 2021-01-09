package br.com.vivo.plataforma.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class BitmapResult {

    private HashMap<Integer, Integer> result;

    private String errorMessage;

    public BitmapResult() {
        this.setErrorMessage("");
        this.setResult(new HashMap<>());
        for (int i = 0; i < 16; i++) {
            this.getResult().put(i, 0);
        }
    }

}
