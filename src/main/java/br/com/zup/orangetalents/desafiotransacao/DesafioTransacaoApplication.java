package br.com.zup.orangetalents.desafiotransacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DesafioTransacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioTransacaoApplication.class, args);
	}

}
