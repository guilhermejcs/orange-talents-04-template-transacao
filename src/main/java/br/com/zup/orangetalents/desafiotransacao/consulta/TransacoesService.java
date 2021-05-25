package br.com.zup.orangetalents.desafiotransacao.consulta;

import br.com.zup.orangetalents.desafiotransacao.transacao.Cartao;
import br.com.zup.orangetalents.desafiotransacao.transacao.Estabelecimento;
import br.com.zup.orangetalents.desafiotransacao.transacao.Transacao;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TransacoesService {

    public static List<Transacao> obterTransacoes(String IdCartao, int quant) throws JSONException {

        List<JSONObject> mensagensKafka;
        Set<Transacao> listaTransacoes = new HashSet<>();
        mensagensKafka = consumeMessages("transacoes");
        for (JSONObject test:mensagensKafka) {
            Transacao trans = new Transacao(test.getString("id"), new BigDecimal(test.getString("valor")),
                    new Estabelecimento(test.getJSONObject("estabelecimento").getString("nome"),
                            test.getJSONObject("estabelecimento").getString("cidade"),
                            test.getJSONObject("estabelecimento").getString("endereco")),
                    new Cartao(test.getJSONObject("cartao").getString("id"),
                            test.getJSONObject("cartao").getString("email")),
                    LocalDateTime.parse(test.getString("efetivadaEm")));
            if(trans.getCartao().getId().equals(IdCartao)){
                listaTransacoes.add(trans);
            }
        }
        List<Transacao> listaPorIdCartao = listaTransacoes.stream()
                .filter(t -> t.getCartao().getId().equals(IdCartao))
                .sorted(Comparator.comparing(Transacao::getEfetivadaEm).reversed())
                .collect(Collectors.toList());
        List<Transacao> retornoTransacoes = new ArrayList<>();
        int q = listaPorIdCartao.size();
        if(q<quant){
           quant = q;
        }

        for (int i = 0; i < quant; i++ ) {
            retornoTransacoes.add(listaPorIdCartao.get(i));
        }
        return retornoTransacoes;
    }

    public static List<JSONObject> consumeMessages(String kafkaTopicName) throws JSONException {
        KafkaConsumer<String, String> kafkaConsumer = null;
        boolean flag = true;
        List<JSONObject> messagesFromKafka = new ArrayList<>();
        int recordCount = 0;

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "project.group.id");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaConsumer = new KafkaConsumer<>(props);

        kafkaConsumer.subscribe(Arrays.asList(kafkaTopicName));
        TopicPartition topicPartition = new TopicPartition(kafkaTopicName, 0);
        while (flag) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(10000));
            kafkaConsumer.seekToBeginning(Collections.singleton(topicPartition));

            recordCount = records.count();
            for (ConsumerRecord<String, String> record : records) {
                messagesFromKafka.add(new JSONObject(record.value()));
                if(record.value() != null) {
                       messagesFromKafka.add(new JSONObject(record.value()));
                }
            }
            if (recordCount > 0) {
                flag = false;
            }
        }
        kafkaConsumer.close();
        return messagesFromKafka;
    }
}