package br.com.vivo.plataforma.controllers;

import br.com.vivo.plataforma.entities.Bitmap;
import br.com.vivo.plataforma.entities.BitmapResult;
import br.com.vivo.plataforma.entities.WinnerResult;
import br.com.vivo.plataforma.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @PostMapping("/bitmap")
    public BitmapResult bitmap(@RequestBody Bitmap value) {
        try {
            return this.apiService.bitmap(value);
        } catch (Exception exp) {
            final BitmapResult result = new BitmapResult();
            result.setResult(new HashMap<>());
            result.setErrorMessage(exp.getMessage());
            return result;
        }
    }

    @PostMapping(value = "/winner", consumes = "text/plain", produces = "application/json")
    public WinnerResult[] winner(@RequestBody String csv) {
        try {
            return this.apiService.winner(csv);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return null;
    }
}
