package tp4.arvoreBinariaBusca.arvore;

public class ABBJoga {
    public int comparacoes = 0;
	private NodoJogador raiz;

    public ABBJoga()
    {
        raiz = null;
    }

    public Boolean arvoreVazia() {
        return this.raiz == null;
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

    public void inserir(Joga jogadorNovo) {
        this.raiz = adicionar(this.raiz, jogadorNovo);
    }

    public String localizar(String nome) {
        return localizar(raiz, nome);
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
