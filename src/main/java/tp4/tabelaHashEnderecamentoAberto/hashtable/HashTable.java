package tp4.tabelaHashEnderecamentoAberto.hashtable;

public class HashTable {
	Jogador[] table;
	int size;
	int comparisonCount;

	public HashTable(int initSize) {
		this.table = new Jogador[initSize];
		this.size = initSize;
	}

	public void addComparison() {
		this.comparisonCount++;
	}

	public int getComparisonCount() {
		return this.comparisonCount;
	}

	public int getFreeHashPosition(Jogador jogador) {
		// Retorna a posição no array em que um jogador deverá ser inserido
		int jHeight = jogador.getAltura();
		boolean positionFound = false;
		int k = 0;
		int tablePosition = 0;
		while(!positionFound) {
			tablePosition = (jHeight % this.size + k * (jHeight % 23)) % this.size;
			if(this.table[tablePosition] == null) {
				positionFound = true;
			}
			else {
				k++;
			}
		}
		return tablePosition;
	}

	public void insertJogador(Jogador jogador) {
		// Insere um jogador na tabela
		int playerPosition = this.getFreeHashPosition(jogador);
		this.table[playerPosition] = jogador;
	}

	public String searchJogador(String name) {
		// Busca um jogador na tabela, pelo nome, e retorna se ele existe e, se sim, a posição na tabela ocupada por ele
		String response = "NAO";
		for(int i = 0; i < this.table.length; i++) {
			Jogador loopJogador = this.table[i];
			this.comparisonCount++;
			if(loopJogador != null && loopJogador.getNome().equals(name)) {
				response = i + " SIM";
				break;
			}
		}
		return response;
	}

}
