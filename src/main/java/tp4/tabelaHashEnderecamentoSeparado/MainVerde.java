import java.io.*;


// ========================
// OPERAÇÕES E CLASSES BASE
//
//

// =========== IO
class ArquivoTextoLeitura {

	private BufferedReader entrada;

	public void abrirArquivo(String nomeArquivo){

		try {
			entrada = new BufferedReader(new FileReader(nomeArquivo));
		}
		catch (FileNotFoundException excecao) {
			System.out.println("Arquivo não encontrado");
		}
	}

	public void fecharArquivo() {

		try {
			entrada.close();
		}
		catch (IOException excecao) {
			System.out.println("Erro no fechamento do arquivo de leitura: " + excecao);
		}
	}

	public String ler() {

		String textoEntrada;

		try {
			textoEntrada = entrada.readLine();
		}
		catch (EOFException excecao) { //Exceção de final de arquivo.
			return null;
		}
		catch (IOException excecao) {
			System.out.println("Erro de leitura: " + excecao);
			return null;
		}
		return textoEntrada;
	}
}

// =========== Jogador
class Jogador {

	private int id;
	private String nome;
	private int altura;
	private int peso;
	private String universidade;
	private int anoNascimento;
	private String cidadeNascimento;
	private String estadoNascimento;

	public Jogador() {

	}

	public Jogador(String dados) {

		String campos[] = dados.split(",");

		this.id = Integer.parseInt(campos[0]);

		this.nome = campos[1];

		this.altura = Integer.parseInt(campos[2]);

		this.peso = Integer.parseInt(campos[3]);

		if (campos[4].isEmpty())
			this.universidade = "nao informado";
		else
			this.universidade = campos[4];

		this.anoNascimento = Integer.parseInt(campos[5]);

		if (campos.length > 6) {
			if (campos[6].isEmpty())
				this.cidadeNascimento = "nao informado";
			else
				this.cidadeNascimento = campos[6];

			if (campos.length < 8) {
				this.estadoNascimento = "nao informado";
			} else {
				this.estadoNascimento = campos[7];
			}

		} else {
			this.cidadeNascimento = "nao informado";
			this.estadoNascimento = "nao informado";
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public void setAnoNascimento(int anoNascimento){
		this.anoNascimento = anoNascimento;
	}

	public int getAnoNascimento(){
		return anoNascimento;
	}

	public String getUniversidade() {
		return universidade;
	}

	public void setUniversidade(String universidade) {
		this.universidade = universidade;
	}

	public String getCidadeNascimento() {
		return cidadeNascimento;
	}

	public void setCidadeNascimento(String cidadeNascimento) {
		this.cidadeNascimento = cidadeNascimento;
	}

	public String getEstadoNascimento() {
		return estadoNascimento;
	}

	public void setEstadoNascimetno(String estadoNascimento) {
		this.estadoNascimento = estadoNascimento;
	}

	public Jogador clone() {

		Jogador copia = new Jogador();

		copia.id = this.id;
		copia.nome = this.nome;
		copia.altura = this.altura;
		copia.peso = this.peso;
		copia.universidade = this.universidade;
		copia.anoNascimento = this.anoNascimento;
		copia.cidadeNascimento = this.cidadeNascimento;
		copia.estadoNascimento = this.estadoNascimento;

		return copia;
	}

	public void imprimir() {
		//System.out.println("[" + id + " ## " + nome + " ## " + altura + " ## " + peso + " ## " + anoNascimento + " ## "
		//		+ universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + "]");
		System.out.println(" ## " + id + " ## " + nome + " ## " + altura + " ## " + peso + " ## " + anoNascimento + " ## "
				+ universidade + " ## " + cidadeNascimento + " ## " + estadoNascimento + " ## ");

	}
}


// ========================
// OPERAÇÕES DE LISTA E PESQUISA PADRÃO
//
//

// =========== Pesquisa Sequencial
class PesquisaSequencial {

	public Jogador buscarId(Jogador[] listaJogadores, int id) {

		for (int i = 0; i < listaJogadores.length; i++)
			if (listaJogadores[i].getId() == id)
				return listaJogadores[i];

		return null;
	}
}

class CelulaJogador {

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

class Lista {

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


// ========================
// OPERAÇÕES DE MENSURAÇÃO DE TEMPO E ESCRITA EM ARQUIVO
//
//

// =========== Timer
class Timer {

	long initTime;
	String fileName;

	public Timer(String name) {
		this.initTime = System.currentTimeMillis();
		this.fileName = "713557_" + name + ".txt";
	}

	public void recordTime(int comparisonCount) {
		long elapsedTime = System.currentTimeMillis() - this.initTime;
		try {
			FileWriter  logFile = new FileWriter(this.fileName);
			logFile.write("713557\t" + elapsedTime + "\t" + comparisonCount);
			logFile.close();
		}
		catch (IOException e) {
			System.out.println("Erro ao gerar o log");
		}
	}

}

// ========================
// OPERAÇÕES E CLASSES ESPECÍFICAS DA QUESTÃO
//
//

// =========== Hash Table

class HashTable {
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


// ========================
// CLASSE PRINCIPAL
//
//

public class MainVerde {

	public static int contarTotalJogadores() {
		int numJogadores = 0;
		ArquivoTextoLeitura arquivoJogadores = new ArquivoTextoLeitura();
		
		arquivoJogadores.abrirArquivo("/tmp/players.csv");
		
		while (arquivoJogadores.ler() != null)
			numJogadores++;
		
		arquivoJogadores.fecharArquivo();
		
		return numJogadores;
	}
	
	public static void lerJogadores(Jogador[] listaJogadores) {
		ArquivoTextoLeitura arquivoJogadores = new ArquivoTextoLeitura();
		int i = 0;
		String dadosJogador;
		
		arquivoJogadores.abrirArquivo("/tmp/players.csv");
		
		dadosJogador = arquivoJogadores.ler();
		while ((dadosJogador = arquivoJogadores.ler()) != null) {
			listaJogadores[i] = new Jogador(dadosJogador);
			i++;
		}
	
		arquivoJogadores.fecharArquivo();
	}
	
	public static void main(String[] args) {
		Timer scriptTime = new Timer("hashSeparado");
		int numTotalJogadores = contarTotalJogadores();
		Jogador[] listaTodosJogadores = new Jogador[numTotalJogadores];
		Jogador pesquisado = null;
		PesquisaSequencial pesquisa = new PesquisaSequencial();
		String entrada;
		int id;

		HashTable mainTable = new HashTable(37);
		
		lerJogadores(listaTodosJogadores);
		
		entrada = MyIO.readLine();
		while (!entrada.equals("FIM")) {
			id = Integer.parseInt(entrada);
			pesquisado = pesquisa.buscarId(listaTodosJogadores, id);
			
			mainTable.insertJogador(pesquisado);
			
			entrada = MyIO.readLine();
		}	
		
		entrada = MyIO.readLine();
		while (!entrada.equals("FIM")) {
			System.out.println(mainTable.searchJogador(entrada));
			
			entrada = MyIO.readLine();
		}

		scriptTime.recordTime(mainTable.getComparisonCount());

	}
}
