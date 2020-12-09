package tp4.tabelaHashEnderecamentoSeparado.hashtable;

public class CelulaJogador {

	Jogador item;
	CelulaJogador proximo;

	public CelulaJogador() {

		item = null;
		proximo = null;
	}

	public CelulaJogador(Jogador jogador) {

		item = jogador;
		proximo = null;
	}
}
