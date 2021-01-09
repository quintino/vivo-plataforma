package br.com.vivo.plataforma.services;

import br.com.vivo.plataforma.entities.Bitmap;
import br.com.vivo.plataforma.entities.BitmapResult;
import br.com.vivo.plataforma.entities.Runner;
import br.com.vivo.plataforma.entities.WinnerResult;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

class OrderWinner implements Comparator<WinnerResult> {
    public int compare(WinnerResult a, WinnerResult b) {
        return a.getPosicaoChegada() - b.getPosicaoChegada();
    }
}

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
        final List<Runner> runners = new LinkedList<>();
        for (int i = 1; i < array.length; i++) {
            final String[] row = array[i].split("\\;");
            final Runner runner = new Runner();
            runner.setHora(this.getHora(row[0]));
            final String[] heroi = this.heroi(row[1]);
            runner.setNumeroSuperHeroi(heroi[0]);
            runner.setSuperHeroi(heroi[1]);
            runner.setVolta(Integer.valueOf(row[2]));
            runner.setTempoVolta(this.getTempo(row[3]));
            runner.setVelocidadeMediaVolta(this.getVelocidadeMedia(row[4]));
            runners.add(runner);
        }
        int posicao = 0;
        final Map<String, WinnerResult> result = new HashMap<>();
        for (Runner runner: runners) {
            WinnerResult winner = new WinnerResult();
            if (result.containsKey(runner.getNumeroSuperHeroi())) {
                winner = result.get(runner.getNumeroSuperHeroi());
            }
            if (runner.getVolta() == 4) {
                posicao++;
                winner.setPosicaoChegada(posicao);
            }
            winner.setCodigoSuperHeroi(runner.getNumeroSuperHeroi());
            winner.setNomeSuperHeroi(runner.getSuperHeroi());
            winner.setQuantidadeVoltas(runner.getVolta());
            winner.setTempoTotalProva(this.somarTempo(winner.getTempoTotalProva(), runner.getTempoVolta()));
            result.put(runner.getNumeroSuperHeroi(), winner);
        }
        final List<WinnerResult> list = Arrays.asList (result.values().toArray(new WinnerResult[0]));
        for (WinnerResult winner: list) {
            if (winner.getPosicaoChegada() == 0) {
                posicao++;
                winner.setPosicaoChegada(posicao);
            }
        }
        Collections.sort(list, new OrderWinner());
        return list.toArray(new WinnerResult[0]);
    }

    private Date somarTempo(final Date atual, final Date tempo) {
        atual.setTime(atual.getTime() + tempo.getTime());
        return atual;
    }

    private String[] heroi(final String heroi) {
        final String[] array = new String[2];
        array[0] = heroi.substring(0, 3);
        array[1] = heroi.substring(4);
        return array;
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
