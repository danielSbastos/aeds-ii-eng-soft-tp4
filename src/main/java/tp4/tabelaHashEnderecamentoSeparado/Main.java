package tp4.tabelaHashEnderecamentoSeparado;
import tp4.tabelaHashEnderecamentoSeparado.hashtable.*;


// ========================
// CLASSE PRINCIPAL
//
//

public class Main {

	public static int contarTotalJogadores() {
		int numJogadores = 0;
		ArquivoTextoLeitura arquivoJogadores = new ArquivoTextoLeitura();
		
		arquivoJogadores.abrirArquivo("players.csv");
		
		while (arquivoJogadores.ler() != null)
			numJogadores++;
		
		arquivoJogadores.fecharArquivo();
		
		return numJogadores;
	}
	
	public static void lerJogadores(Jogador[] listaJogadores) {
		ArquivoTextoLeitura arquivoJogadores = new ArquivoTextoLeitura();
		int i = 0;
		String dadosJogador;
		
		arquivoJogadores.abrirArquivo("players.csv");
		
		dadosJogador = arquivoJogadores.ler();
		while ((dadosJogador = arquivoJogadores.ler()) != null) {
			listaJogadores[i] = new Jogador(dadosJogador);
			i++;
		}
	
		arquivoJogadores.fecharArquivo();
	}
	
	public static void main(String[] args) {
		/*
			Cria uma tabela hash de tamanho 37, com endereçamento em separado, e popula a tabela com
			os jogadores indicados pela entrada padrão stdin.
			Após popular a tabela, verifica se os jogadores buscados existem na tabela e, se sim, 
			indica em qual posição cada um está.
		*/
		Timer scriptTime = new Timer("hashSeparado");
		int numTotalJogadores = contarTotalJogadores();
		Jogador[] listaTodosJogadores = new Jogador[numTotalJogadores];
		Jogador pesquisado = null;
		PesquisaSequencial pesquisa = new PesquisaSequencial();
		String entrada;
		int id;

		HashTable mainTable = new HashTable(37);
		
		// Populando tabela
		lerJogadores(listaTodosJogadores);
		entrada = MyIO.readLine();
		while (!entrada.equals("FIM")) {
			id = Integer.parseInt(entrada);
			pesquisado = pesquisa.buscarId(listaTodosJogadores, id);
			
			mainTable.insertJogador(pesquisado);
			
			entrada = MyIO.readLine();
		}	
		
		// Buscando por jogadores
		entrada = MyIO.readLine();
		while (!entrada.equals("FIM")) {
			System.out.println(mainTable.searchJogador(entrada));
			
			entrada = MyIO.readLine();
		}

		// Gravando log com resultado
		scriptTime.recordTime(mainTable.getComparisonCount());

	}
}
