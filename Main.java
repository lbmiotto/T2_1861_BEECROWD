import java.util.*;

class Assassinos {
    String nome;
    int qtsMortes;

    Assassinos(String nome) {
        this.nome = nome;
        this.qtsMortes = 1;
    }
}

class ArvHall {
    Assassinos dado;
    ArvHall esquerdo;
    ArvHall direito;

    ArvHall(Assassinos dado) {
        this.dado = dado;
        this.esquerdo = null;
        this.direito = null;
    }
}

class ArvMortos {
    String morto;
    ArvMortos esquerdo;
    ArvMortos direito;

    ArvMortos(String morto) {
        this.morto = morto;
        this.esquerdo = null;
        this.direito = null;
    }
}

public class Main {

    static ArvHall insereAssassinos(ArvHall hall, Assassinos assassino) {
        if (hall == null) {
            hall = new ArvHall(assassino);
        } else if (hall.dado.nome.compareTo(assassino.nome) > 0) {
            hall.esquerdo = insereAssassinos(hall.esquerdo, assassino);
        } else if (hall.dado.nome.compareTo(assassino.nome) < 0) {
            hall.direito = insereAssassinos(hall.direito, assassino);
        } else {
            hall.dado.qtsMortes++;
        }
        return hall;
    }

    static ArvMortos insereMortos(ArvMortos mortos, String morto) {
        if (mortos == null) {
            mortos = new ArvMortos(morto);
        } else if (mortos.morto.compareTo(morto) > 0) {
            mortos.esquerdo = insereMortos(mortos.esquerdo, morto);
        } else {
            mortos.direito = insereMortos(mortos.direito, morto);
        }
        return mortos;
    }

    static boolean busca(ArvMortos mortos, String chave) {
        if (mortos == null) {
            return false;
        }
        if (mortos.morto.equals(chave)) {
            return true;
        }
        if (mortos.morto.compareTo(chave) > 0) {
            return busca(mortos.esquerdo, chave);
        } else {
            return busca(mortos.direito, chave);
        }
    }

    static void show(ArvHall hall, ArvMortos mortos) {
        if (hall != null) {
            show(hall.esquerdo, mortos);
            if (!busca(mortos, hall.dado.nome)) {
                System.out.println(hall.dado.nome + " " + hall.dado.qtsMortes);
            }
            show(hall.direito, mortos);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String matou, morreu;
        ArvHall hall = null;
        ArvMortos mortos = null;

        while (scanner.hasNext()) {
            matou = scanner.next();
            morreu = scanner.next();

            Assassinos assassino = new Assassinos(matou);
            hall = insereAssassinos(hall, assassino);
            mortos = insereMortos(mortos, morreu);
        }

        System.out.println("HALL OF MURDERERS");
        show(hall, mortos);
    }
}
