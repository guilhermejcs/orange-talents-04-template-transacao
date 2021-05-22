package br.com.zup.orangetalents.desafiotransacao.transacao;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerDeTransacao {

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(Transacao transacao) {
        System.out.println(transacao.toString());
    }
}
