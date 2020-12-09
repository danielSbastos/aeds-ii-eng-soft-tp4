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
		Timer scriptTime = new Timer("hashRehashing");
		int numTotalJogadores = contarTotalJogadores();
		Jogador[] listaTodosJogadores = new Jogador[numTotalJogadores];
		Jogador pesquisado = null;
		PesquisaSequencial pesquisa = new PesquisaSequencial();
		String entrada;
		int id;

		HashTable mainTable = new HashTable(79);
		
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
