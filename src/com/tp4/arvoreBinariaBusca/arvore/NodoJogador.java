package com.tp4.arvoreBinariaBusca.arvore;

public class NodoJogador {

	Joga item;           // contém os dados do aluno armazenado no nodo da árvore.
	NodoJogador direita;    // referência ao nodo armazenado, na árvore, à direita do aluno em questão.
    NodoJogador esquerda;   // referência ao nodo armazenado, na árvore, à esquerda do aluno em questão.

    public NodoJogador(Joga registro) {
    	item = registro;
    	direita = null;
    	esquerda = null;
    }
}
