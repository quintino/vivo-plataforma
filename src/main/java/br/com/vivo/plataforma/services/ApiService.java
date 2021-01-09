package br.com.vivo.plataforma.services;

import br.com.vivo.plataforma.entities.Bitmap;
import br.com.vivo.plataforma.entities.BitmapResult;
import br.com.vivo.plataforma.entities.WinnerResult;

public interface ApiService {

    BitmapResult bitmap(final Bitmap value) throws Exception;

    WinnerResult[] winner(final String value) throws Exception;

}
