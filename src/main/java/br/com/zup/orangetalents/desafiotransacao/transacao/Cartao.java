package br.com.zup.orangetalents.desafiotransacao.transacao;

public class Cartao {
    private String id;
    private String email;

    public Cartao() {
    }

    public Cartao(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
