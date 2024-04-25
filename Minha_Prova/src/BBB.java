import java.util.ArrayList;
import java.util.Scanner;

public class BBB {
    public static void main(String[] args) {
        ArrayList<Jogador> jogadores = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        cadastrarJogadores(jogadores);

        System.out.println("Votação (digite 'sair' para encerrar):");
        while (true) {
            System.out.print("Em quem você vota para sair da casa? ");
            String voto = scanner.nextLine();
            if (voto.equalsIgnoreCase("sair")) {
                break;
            }
            contabilizarVoto(jogadores, voto);
        }

        Jogador eliminado = encontrarEliminado(jogadores);
        if (eliminado != null) {
            System.out.println("Se eu conseguir mover montanhas, se eu conseguir surfar um tsunami, se eu conseguir domar o sol, se eu conseguir fazer o mar virar sertão, e o sertão virar mar, se eu conseguir dizer o que eu nunca vou conseguir dizer, aí terá chegado o dia em que eu vou conseguir te eliminar com alegria. Com " + eliminado.getVotos() + " votos, é você quem sai " + eliminado.getNome());
        } else {
            System.out.println("Nenhum eliminado encontrado.");
        }
    }

    private static void cadastrarJogadores(ArrayList<Jogador> jogadores) {
        String[] nomes = {
                "Alane Dias", "Beatriz Reis", "Davi Brito", "Deniziane Ferreira",
                "Fernanda Bande", "Giovanna Lima", "Giovanna Pitel", "Isabelle Nogueira",
                "Juninho", "Leidy Elin", "Lucas Henrique", "Lucas Luigi", "Lucas Pizane",
                "Marcus Vinicius", "Matteus Amaral", "Maycon Cosmer", "MC Bin Laden",
                "Michel Nogueira", "Nizam", "Raquele Cardozo", "Rodriguinho",
                "Thalyta Alves", "Vanessa Lopes", "Vinicius Rodrigues", "Wanessa Camargo",
                "Yasmin Brunet"
        };

        for (String nome : nomes) {
            Jogador jogador = new Jogador();
            jogador.setNome(nome);
            jogadores.add(jogador);
        }
    }

    private static void contabilizarVoto(ArrayList<Jogador> jogadores, String voto) {
        for (Jogador jogador : jogadores) {
            if (jogador.getNome().equalsIgnoreCase(voto)) {
                jogador.incrementaUmVoto();
                System.out.println("Voto computado para " + jogador.getNome());
                return;
            }
        }
        System.out.println("Jogador não encontrado. Voto não computado.");
    }

    private static Jogador encontrarEliminado(ArrayList<Jogador> jogadores) {
        Jogador eliminado = null;
        for (Jogador jogador : jogadores) {
            if (eliminado == null || jogador.getVotos() > eliminado.getVotos()) {
                eliminado = jogador;
            }
        }
        return eliminado;
    }
}