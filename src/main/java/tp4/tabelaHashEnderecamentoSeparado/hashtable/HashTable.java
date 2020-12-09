package tp4.tabelaHashEnderecamentoSeparado.hashtable;

public class HashTable {
	Lista[] table;
	int size;
	int comparisonCount;

	public HashTable(int initSize) {
		this.table = new Lista[initSize];
		this.size = initSize;
		this.comparisonCount = 0;
		
		for(int i = 0; i < initSize; i++) {
			this.table[i] = new Lista();
		}
	}

	public void addComparison() {
		this.comparisonCount++;
	}

	public int getComparisonCount() {
		return this.comparisonCount;
	}

	public int getHashPosition(Jogador jogador) {
		// Retorna a posição da tabela em que um jogador deve ser inserido
		return jogador.getAltura() % this.size;
	}

	public void insertJogador(Jogador jogador) {
		// Insere um jogador na tabela
		int tablePosition = this.getHashPosition(jogador);
		this.table[tablePosition].inserirInicio(jogador);
	}

	public String searchJogador(String name) {
		// Busca por um jogador em toda a tabela e retorna a posição dele seguida de SIM, ou apenas NAO, caso não seja encontrado
		String response = "NAO";
		for(int i = 0; i < this.size; i++) {
			Lista listAtCell = this.table[i];
			this.comparisonCount += listAtCell.getComparisonCount(name);
			if(!listAtCell.listaVazia() && listAtCell.jogadorIsInList(name)) {
				response = i + " SIM";
				break;
			}
		}
		return response;
	}
	
}
