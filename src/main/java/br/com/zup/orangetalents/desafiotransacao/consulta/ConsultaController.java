package br.com.zup.orangetalents.desafiotransacao.consulta;

import br.com.zup.orangetalents.desafiotransacao.transacao.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.StyledEditorKit;
import java.util.List;

@RestController
public class ConsultaController {

    @Autowired
    ConsultaClient consultaClient;

    @GetMapping(value ="/consulta/{idCartao}")
    public ResponseEntity<?>  getMethodName(@PathVariable("idCartao") String idCartao) throws JSONException {
        try {
            consultaClient.cartao(idCartao);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        List<Transacao> body = TransacoesService.obterTransacoes(idCartao, 10);
        return ResponseEntity.ok().body(body);
    }
}
