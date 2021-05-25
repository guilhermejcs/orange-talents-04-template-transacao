package br.com.zup.orangetalents.desafiotransacao.consulta;

public class Cartao {
    String id;
    String emitidoEm;
    String titular;

    public Cartao() {
    }

    public Cartao(String id, String emitidoEm, String titular) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
    }
}
