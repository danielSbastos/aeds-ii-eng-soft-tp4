package tp4.arvoreBinariaBusca.arvore;

public class ABBJoga {
    public int comparacoes = 0;
	private NodoJogador raiz;

    public ABBJoga()
    {
        raiz = null;
    }

    // Localiza o jogador pelo nome e retorna o caminho percorrido até o encontrar ou não.
    // Argumentos
    //    String name -> nome do jogador a ser procurado
    // Exemplos de entrada e retorno
    //    Maria ->  Beatriz João Maria SIM
    //    Mario ->  Beatriz João Maria NÃO
    public String localizar(String nome) {
        return localizar(raiz, nome);
    }

    // Insere o objeto do Jogador na árvore binária de busca e não retorna nada
    public void inserir(Joga jogadorNovo) {
        this.raiz = adicionar(this.raiz, jogadorNovo);
    }

    private NodoJogador adicionar(NodoJogador raizArvore, Joga jogadorNovo) {
        if (raizArvore == null){
            raizArvore = new NodoJogador(jogadorNovo);
        } else {
            String raizNome = raizArvore.item.getNome();
            if (raizNome.compareTo(jogadorNovo.getNome()) >= 0) {
                raizArvore.esquerda = adicionar(raizArvore.esquerda, jogadorNovo);

            } else {
                if (raizNome.compareTo(jogadorNovo.getNome()) < 0) {
                    raizArvore.direita = adicionar(raizArvore.direita, jogadorNovo);

                } else
                    System.out.println("O jogador " + jogadorNovo.getNome() + " já foi inserido anteriormente na árvore.");
            }
        }
        return raizArvore;
    }

    private String localizar(NodoJogador raizArvore, String nome) {
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
                resultado = raizNome + " " + localizar(raizArvore.direita, nome);
            } else {
                resultado = raizNome + " " + localizar(raizArvore.esquerda, nome);
            }
        }

        return resultado;
    }
}
