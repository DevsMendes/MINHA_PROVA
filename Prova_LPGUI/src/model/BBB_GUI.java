import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BBB_GUI extends JFrame {
    private ArrayList<Jogador> jogadores = new ArrayList<>();
    private JTextField textFieldVoto;
    private JTextArea textAreaResultado;

    public BBB_GUI() {
        setTitle("Big Brother Brasil");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel labelVoto = new JLabel("Em quem você vota para sair da casa?");
        labelVoto.setBounds(20, 20, 300, 25);
        panel.add(labelVoto);

        textFieldVoto = new JTextField();
        textFieldVoto.setBounds(20, 50, 200, 25);
        panel.add(textFieldVoto);

        JButton buttonVotar = new JButton("Votar");
        buttonVotar.setBounds(230, 50, 100, 25);
        buttonVotar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String voto = textFieldVoto.getText();
                if (!voto.isEmpty()) {
                    contabilizarVoto(voto);
                    textFieldVoto.setText("");
                }
            }
        });
        panel.add(buttonVotar);

        textAreaResultado = new JTextArea();
        textAreaResultado.setBounds(20, 90, 350, 150);
        textAreaResultado.setEditable(false);
        panel.add(textAreaResultado);

        add(panel);
    }

    private void cadastrarJogadores() {
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
            Jogador jogador = new Jogador(nome);
            jogadores.add(jogador);
        }
    }

    private void contabilizarVoto(String voto) {
        boolean jogadorEncontrado = false;
        for (Jogador jogador : jogadores) {
            if (jogador.getNome().equalsIgnoreCase(voto)) {
                jogador.incrementaUmVoto();
                jogadorEncontrado = true;
                break;
            }
        }
        if (jogadorEncontrado) {
            atualizarResultado();
        } else {
            JOptionPane.showMessageDialog(this, "Jogador não encontrado. Voto não computado.");
        }
    }

    private void atualizarResultado() {
        Jogador eliminado = encontrarEliminado();
        if (eliminado != null) {
            String mensagem = "Se eu conseguir mover montanhas, se eu conseguir surfar um tsunami, se eu conseguir domar o sol, se eu conseguir fazer o mar virar sertão, e o sertão virar mar, se eu conseguir dizer o que eu nunca vou conseguir dizer, aí terá chegado o dia em que eu vou conseguir te eliminar com alegria. Com " + eliminado.getVotos() + " votos, é você quem sai " + eliminado.getNome();
            textAreaResultado.setText(mensagem);
        } else {
            textAreaResultado.setText("Nenhum eliminado encontrado.");
        }
    }

    private Jogador encontrarEliminado() {
        Jogador eliminado = null;
        for (Jogador jogador : jogadores) {
            if (eliminado == null || jogador.getVotos() > eliminado.getVotos()) {
                eliminado = jogador;
            }
        }
        return eliminado;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BBB_GUI bbbGui = new BBB_GUI();
                bbbGui.cadastrarJogadores();
                bbbGui.setVisible(true);
            }
        });
    }
}

class Jogador {
    private String nome;
    private int votos;

    public Jogador(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getVotos() {
        return votos;
    }

    public void incrementaUmVoto() {
        this.votos++;
    }
}
