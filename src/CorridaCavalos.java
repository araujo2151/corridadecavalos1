import java.util.ArrayList;
import java.util.Scanner;

class Cavalo {
    private String nome;
    private double chanceVitoria;

    public Cavalo(String nome, double chanceVitoria) {
        this.nome = nome;
        this.chanceVitoria = chanceVitoria;
    }

    public String getNome() {
        return nome;
    }

    public double getChanceVitoria() {
        return chanceVitoria;
    }

    public void setChanceVitoria(double chanceVitoria) {
        this.chanceVitoria = chanceVitoria;
    }
}

public class CorridaCavalos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo à Grande Corrida de Cavalos DO Haras Vieira ");

        Cavalo[] cavalos = {
            new Cavalo("Granite Lake", 0.255),
            new Cavalo("Dick Brumel", 0.27),
            new Cavalo("I Love HRV", 0.24),
            new Cavalo("Bling The Bring", 0.235),
        };

        System.out.println("Distância da corrida: 320 metros");
        System.out.println("   1. Feno C (Colhido cedo )");
        System.out.println("   2. Feno T (Colhido tarde )");
        System.out.println("Quantos jogadores (entre 2 e 4)? ");

        int numJogadores = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer após a leitura do número de jogadores

        if (numJogadores >= 2 && numJogadores <= 4) {
            ArrayList<Cavalo> cavalosEscolhidos = new ArrayList<>();

            for (int i = 0; i < numJogadores; i++) {
                System.out.print("    Jogador " + (i + 1) + ", digite seu nome: ");
                String nomeJogador = scanner.nextLine();
                System.out.println("    Bem-vindo, " + nomeJogador + "!");

                // Mostra os cavalos disponíveis
                System.out.println("    Cavalos Disponíveis:");
                for (int j = 0; j < cavalos.length; j++) {
                    System.out.println("       " + (j + 1) + ". " + cavalos[j].getNome());
                }

                final int[] escolhaCavalo = new int[1];  // Variável final para armazenar a escolha do cavalo
                do {
                    System.out.print("    Escolha seu cavalo (1 a 4): ");
                    escolhaCavalo[0] = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer após a leitura da escolha do cavalo

                    if (escolhaCavalo[0] < 1 || escolhaCavalo[0] > 4) {
                        System.out.println("    Escolha inválida. Tente novamente.");
                    } else if (cavalosEscolhidos.stream().anyMatch(cavalo -> cavalo.getNome().equals(cavalos[escolhaCavalo[0] - 1].getNome()))) {
                        System.out.println("    Este cavalo já foi escolhido. Escolha outro.");
                    }
                } while (escolhaCavalo[0] < 1 || escolhaCavalo[0] > 4 || cavalosEscolhidos.stream().anyMatch(cavalo -> cavalo.getNome().equals(cavalos[escolhaCavalo[0] - 1].getNome())));

                Cavalo cavaloEscolhido = cavalos[escolhaCavalo[0] - 1];

                System.out.print("    Escolha Feno (C ou T): ");
                String escolhaFeno = scanner.next();

                double chanceVitoria = cavaloEscolhido.getChanceVitoria();
                if (escolhaFeno.equalsIgnoreCase("C")) {
                    chanceVitoria *= 1.15;
                } else if (escolhaFeno.equalsIgnoreCase("T")) {
                    chanceVitoria *= 1.01;
                }
                cavaloEscolhido.setChanceVitoria(chanceVitoria);

                cavalosEscolhidos.add(cavaloEscolhido);
                scanner.nextLine(); // Limpa o buffer após a leitura da escolha do feno
            }

            cavalosEscolhidos.sort((c1, c2) -> Double.compare(c2.getChanceVitoria(), c1.getChanceVitoria()));

            Cavalo vencedor = cavalosEscolhidos.get(0);
            System.out.println("O cavalo vencedor é: " + vencedor.getNome() + " com " + vencedor.getChanceVitoria() * 100 + "% de chance de vitória.");

            // Conclusão da corrida
            System.out.println("\nE a corrida começa!");
            System.out.println("Os cavalos galopam com toda a força e velocidade, levantando nuvens de poeira.");
            System.out.println("É uma disputa muito acirrada, qualquer um pode ser o campeão!");

            // Adicione mais elementos narrativos para descrever a emoção da corrida

            // Simulação do tempo de espera antes de revelar o vencedor
            try {
                Thread.sleep(2000);  // Aguarda por 2 segundos (pode ajustar conforme necessário)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Revelação do vencedor
            System.out.println("\nE o vencedor é...");
            System.out.println(vencedor.getNome() + " com uma vitória incrível diante dos seus adversários!");

            // Adicione a premiação para o vencedor
            System.out.println("\nParabéns, Jogador " + (cavalosEscolhidos.indexOf(vencedor) + 1) + "!");
            System.out.println("Você e " + vencedor.getNome() + " são verdadeiros campeões da pista!");

            // Adicione a premiação em dinheiro
            System.out.println("Como prêmio, você recebe 200 mil em dinheiro!");
            System.out.println("Deus te abençoe, campeão, e continue treinando para as futuras corridas.");

        } else {
            System.out.println("Número de jogadores inválido.");
        }

        scanner.close();
    }
}