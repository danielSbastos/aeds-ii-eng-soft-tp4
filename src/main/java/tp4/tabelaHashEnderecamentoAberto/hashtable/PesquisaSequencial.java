package tp4.tabelaHashEnderecamentoAberto.hashtable;


public class PesquisaSequencial {

	public Jogador buscarId(Jogador[] listaJogadores, int id) {

		for (int i = 0; i < listaJogadores.length; i++)
			if (listaJogadores[i].getId() == id)
				return listaJogadores[i];

		return null;
	}
}
