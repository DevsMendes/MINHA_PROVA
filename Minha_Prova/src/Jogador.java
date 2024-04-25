import java.util.ArrayList;
import java.util.Scanner;
class Jogador {
    private String nome;
    private int votos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public void incrementaUmVoto() {
        this.votos = getVotos() + 1;
    }
}
