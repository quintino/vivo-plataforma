package br.com.vivo.plataforma.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Bitmap {

    /**
     * Number of cols
     */
    private int cols;

    /**
     * Number of rows
     */
    private int rows;

    /**
     * Stringify valued array.
     *
     * Separated rows by § and cols by ,
     */
    private String value;

}
