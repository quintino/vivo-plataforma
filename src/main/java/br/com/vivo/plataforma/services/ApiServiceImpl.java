package br.com.vivo.plataforma.services;

import br.com.vivo.plataforma.entities.Bitmap;
import br.com.vivo.plataforma.entities.BitmapResult;
import br.com.vivo.plataforma.entities.Runner;
import br.com.vivo.plataforma.entities.WinnerResult;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Override
    public BitmapResult bitmap(final Bitmap value) throws Exception {
        if (value == null || value.getValue() == null) {
            throw new Exception("Objeto invalido.");
        }
        final String[] rows = value.getValue().split("\\§");
        if (rows.length != value.getRows()) {
            throw new Exception("Quantidade de linhas invalida.");
        }
        final BitmapResult result = new BitmapResult();
        for (int i = 0; i < rows.length; i++) {
            final String row = rows[i];
            if (row == null) {
                throw new Exception("Objeto invalido.");
            }
            final String[] cols = row.split("\\,");
            if (cols.length != value.getCols()) {
                throw new Exception("Quantidade de colunas invalida.");
            }
            for (int j = 0; j < cols.length; j++) {
                final int col = Integer.valueOf(cols[j]);
                if (result.getResult().containsKey(col)) {
                    final int update = result.getResult().get(col) + 1;
//                        result.getResult().remove(colValue.intValue());
                    result.getResult().put(col, update);
                }
            }
        }
        return result;
    }

    @Override
    public WinnerResult[] winner(final String value) throws Exception {
        String[] array = value.split("\\\n");
        final List<Runner> runners = new ArrayList<>();
        for (int i = 1; i < array.length; i++) {
            final String[] row = array[i].split("\\;");
            final Runner runner = new Runner();
            runner.setHora(this.getHora(row[0]));
            runner.setSuperHeroi(row[1]);
            runner.setVolta(Integer.valueOf(row[2]));
            runner.setTempoVolta(this.getTempo(row[3]));
            runner.setVelocidadeMediaVolta(this.getVelocidadeMedia(row[4]));
            runners.add(runner);
        }
        return new WinnerResult[0];
    }

    private Date getTimer(final String hora, final String format) throws Exception {
        final SimpleDateFormat sdFormat = new SimpleDateFormat(format);
        return sdFormat.parse(hora);
    }

    private Date getHora(final String hora) throws Exception {
        return this.getTimer(hora, "HH:mm:ss.SSS");
    }

    private Date getTempo(final String hora) throws Exception {
        return this.getTimer(hora, "mm:ss.SSS");
    }

    private Double getVelocidadeMedia(final String velocidade) {
        return Double.parseDouble(velocidade.replace(",", "."));
    }

}
