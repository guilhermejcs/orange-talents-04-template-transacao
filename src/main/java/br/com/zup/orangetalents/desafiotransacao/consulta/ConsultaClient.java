package br.com.zup.orangetalents.desafiotransacao.consulta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "consulta", url = "${CARTAO_CLIENT}")
public interface ConsultaClient {
    @GetMapping(value = "/api/cartoes/{id}")
    Cartao cartao(@PathVariable("id")String id);
}
