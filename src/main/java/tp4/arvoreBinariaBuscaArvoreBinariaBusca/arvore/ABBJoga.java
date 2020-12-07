package tp4.arvoreBinariaBuscaArvoreBinariaBusca.arvore;

public class ABBJoga {
    public int comparacoes = 0;
    private NodoJogador raiz;

    public ABBJoga() {
        raiz = null;
    }

    // Encontra o jogador com base na altura e no nome
    // Retorna o caminho percorrido, incluindo os modulos com 17 das alturas
    // e os nomes dos jogadores
    // Argumentos:
    //   String nome -> nome do jogador a ser encontrado
    //   int altura -> altura do jogador a ser encontrado
    // Exemplo de argumentos e retorno:
    //    Joao, 180 -> 1 12 Joao SIM
    //    Maria, 180 -> 1 3 Nelson NAO
    public String localizar(String nome, int altura) {
        return localizarPorAltura(raiz, nome, altura);
    }

    // Insere um jogador na árvore binária dos módulos das altura e depois
    // na respectiva árvore de nome dos jogadores
    public void inserir(Joga jogadorNovo) {
        this.raiz = inserirPorAltura(this.raiz, jogadorNovo);
    }

    private NodoJogador inserirPorAltura(NodoJogador raizArvore, Joga jogadorNovo) {
        if (raizArvore == null) {
            raizArvore = new NodoJogador(jogadorNovo);
            raizArvore.arvore.raiz = raizArvore.arvore.inserirPorNome(raizArvore.arvore.raiz, jogadorNovo);
        } else {
            int raizAltura = raizArvore.item.getAltura();
            if ((jogadorNovo.getAltura() % 17) < (raizAltura % 17)) {
                raizArvore.esquerda = inserirPorAltura(raizArvore.esquerda, jogadorNovo);
            } else {
                if ((jogadorNovo.getAltura() % 17) > (raizAltura % 17)) {
                    raizArvore.direita = inserirPorAltura(raizArvore.direita, jogadorNovo);
                } else {
                    raizArvore.arvore.raiz = raizArvore.arvore.inserirPorNome(raizArvore.arvore.raiz, jogadorNovo);
                }
            }
        }

        return raizArvore;
    }

    private NodoJogador inserirPorNome(NodoJogador raizArvore, Joga jogadorNovo) {
        if (raizArvore == null){
            raizArvore = new NodoJogador(jogadorNovo);
        } else {
            String raizNome = raizArvore.item.getNome();
            if (raizNome.compareTo(jogadorNovo.getNome()) >= 0) {
                raizArvore.esquerda = inserirPorNome(raizArvore.esquerda, jogadorNovo);

            } else {
                if (raizNome.compareTo(jogadorNovo.getNome()) < 0) {
                    raizArvore.direita = inserirPorNome(raizArvore.direita, jogadorNovo);
                } else
                    System.out.println("O jogador " + jogadorNovo.getNome() + " já foi inserido anteriormente na árvore.");
            }
        }
        return raizArvore;
    }

    private String localizarPorAltura(NodoJogador raizArvore, String nome, int altura) {
        String resultado = "";

        if (raizArvore == null) {
            comparacoes++;
        } else {
            int raizAltura = raizArvore.item.getAltura();
            if ((raizAltura % 17) == (altura % 17)) {
                resultado = raizAltura % 17 + " " + localizarPorNome(raizArvore.arvore.raiz, nome);
            } else if ((raizAltura % 17) < (altura % 17)) {
                resultado = raizAltura % 17 + " " + localizarPorAltura(raizArvore.direita, nome, altura);
            } else {
                resultado = raizAltura % 17 + " " + localizarPorAltura(raizArvore.esquerda, nome, altura);
            }
        }

        return resultado;
    }

    private String localizarPorNome(NodoJogador raizArvore, String nome) {
        String resultado;

        if (raizArvore == null) {
            comparacoes++;
            resultado = "NAO";
        } else {
            String raizNome = raizArvore.item.getNome();
            if (raizNome.compareTo(nome) == 0) {
                comparacoes++;
                resultado = nome + " SIM";
            } else if (raizNome.compareTo(nome) < 0) {
                comparacoes++;
                resultado = raizNome + " " + localizarPorNome(raizArvore.direita, nome);
            } else {
                resultado = raizNome + " " + localizarPorNome(raizArvore.esquerda, nome);
            }
        }

        return resultado;
    }
}
