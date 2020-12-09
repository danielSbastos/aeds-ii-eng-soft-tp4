package tp4.tabelaHashEnderecamentoSeparado.hashtable;

public class Lista {

	private CelulaJogador primeiro;
	private CelulaJogador ultimo;

	public Lista() {

		CelulaJogador sentinela = new CelulaJogador();
		primeiro = ultimo = sentinela;
	}

	public boolean listaVazia() {

		boolean resp;

		if (primeiro == ultimo)
			resp = true;
		else
			resp = false;

		return resp;
	}

	public void inserirInicio(Jogador jogador) {

		CelulaJogador aux = primeiro.proximo;
		CelulaJogador novaCelula = new CelulaJogador(jogador);

		primeiro.proximo = novaCelula;
		novaCelula.proximo = aux;

		if (primeiro == ultimo)
			ultimo = novaCelula;
	}

	public void inserirFim(Jogador jogador) {

		CelulaJogador novaCelula = new CelulaJogador(jogador);

		ultimo.proximo = novaCelula;
		ultimo = novaCelula;
	}

	public void inserir(Jogador jogador, int posicao) throws Exception {

		int i;
		CelulaJogador anterior, aux, novaCelula;

		i = 0;
		aux = primeiro.proximo;
		anterior = primeiro;

		while ((aux != null) && (i < posicao)) {
			aux = aux.proximo;
			anterior = anterior.proximo;
			i++;
		}

		if (i == posicao) {
			novaCelula = new CelulaJogador(jogador);
			anterior.proximo = novaCelula;
			novaCelula.proximo = aux;

			if (primeiro == ultimo)
				ultimo = novaCelula;
		} else
			throw new Exception("Erro ao tentar inserir um item na lista: posição de inserção inválida!");

	}

	public Jogador removerInicio() throws Exception {

		Jogador jogador = null;
		CelulaJogador aux;

		if (!listaVazia()) {
			aux = primeiro.proximo;
			primeiro.proximo = aux.proximo;

			aux.proximo = null;

			if (aux == ultimo)
				ultimo = primeiro;

			jogador = aux.item;
		} else
			throw new Exception ("Erro ao tentar remover um item da lista. A lista está vazia!");

		return jogador;
	}

	public Jogador removerFim() throws Exception {

		Jogador jogador = null;
		CelulaJogador anterior;

		if (!listaVazia()) {
			anterior = primeiro;

			while(anterior.proximo != ultimo)
				anterior = anterior.proximo;

			anterior.proximo = null;
			jogador = ultimo.item;

			if (anterior == primeiro)
				ultimo = primeiro;
			else
				ultimo = anterior;

		} else
			throw new Exception ("Erro ao tentar remover um item da lista. A lista está vazia!");

		return jogador;
	}

	public Jogador remover(int posicao) throws Exception {

		Jogador jogador = null;
		int i;
		CelulaJogador anterior, aux;

		if (!listaVazia()) {
			anterior = primeiro;
			aux = primeiro.proximo;
			i = 0;

			while ((aux != null) && (i < posicao)) {
				aux = aux.proximo;
				anterior = anterior.proximo;
				i++;
			}

			if (i == posicao) {
				anterior.proximo = aux.proximo;
				aux.proximo = null;
				jogador = aux.item;

				if (aux == ultimo)
					ultimo = anterior;
			} else
				throw new Exception ("Erro ao tentar remover um item da lista: posição de remoção inválida!");
		} else
			throw new Exception ("Erro ao tentar remover um item da lista. A lista está vazia!");

		return jogador;
	}

	public void mostrar() {

		int i = 0;
		CelulaJogador aux = primeiro.proximo;

		while (aux != null) {
			System.out.print("[" + i + "]");
			aux.item.imprimir();
			aux = aux.proximo;
			i++;
		}
	}

	public boolean jogadorIsInList(String name) {
		//Retorna se um jogador existe na lista
		CelulaJogador currentCell = this.primeiro;
		boolean jogadorFound = false;

		if(currentCell.item != null && currentCell.item.getNome().equals(name)) {
			jogadorFound = true;
		}
		while(!jogadorFound && currentCell != this.ultimo) {
			currentCell = currentCell.proximo;
			if(currentCell.item != null && currentCell.item.getNome().equals(name)) {
				jogadorFound = true;
			}
		}

		return jogadorFound;
	}

	public int getComparisonCount(String name) {
		//Retorna a quantidade de comparações feitas na busca por um jogador numa lista
		CelulaJogador currentCell = this.primeiro;
		boolean jogadorFound = false;
		int comparisonCount = 0;

		if(currentCell.item != null && currentCell.item.getNome().equals(name)) {
			jogadorFound = true;
		}
		comparisonCount++;
		while(!jogadorFound && currentCell != this.ultimo) {
			currentCell = currentCell.proximo;
			if(currentCell.item != null && currentCell.item.getNome().equals(name)) {
				jogadorFound = true;
			}
			comparisonCount++;
		}
		return comparisonCount;
	}
}
