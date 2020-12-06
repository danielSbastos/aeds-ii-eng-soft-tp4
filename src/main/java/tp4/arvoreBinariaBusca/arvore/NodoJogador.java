package tp4.arvoreBinariaBusca.arvore;

public class NodoJogador {
	Joga item;           // contém os dados do jogador armazenado no nodo da árvore.
	NodoJogador direita;    // referência ao nodo armazenado, na árvore, à direita do jogador em questão.
    NodoJogador esquerda;   // referência ao nodo armazenado, na árvore, à esquerda do jogador em questão.

    // Instancia um novo nodo com o jogador
	public NodoJogador(Joga registro) {
    	item = registro;
    	direita = null;
    	esquerda = null;
    }
}
