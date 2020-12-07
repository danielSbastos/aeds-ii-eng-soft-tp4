package tp4.arvoreBinariaBuscaArvoreBinariaBusca;

import tp4.arvoreBinariaBuscaArvoreBinariaBusca.arvore.ABBJoga;
import tp4.arvoreBinariaBuscaArvoreBinariaBusca.arvore.Joga;
import tp4.tabelaHashEnderecamentoAberto.MyIO;

import java.io.*;
import java.util.Date;

public class Main {
    // Cria uma árvore binária de busca que utiliza a chave como o módulo de 17 com a altura do jogador
    // e uma segunda árvore binária de nomes do jogador, utilizando a primeira e depois a segunda como pesquisa
    // Depois insere alguns jogadores definidos pelo stdin, procura por jogadores na árvore e
    // imprime o caminho da busca e se o encontrou ou não.
    // Também escreve num arquivo a quantidade de comparações feitas da busca, a duração e
    // a matrícula de um dos autores
    public static void main(String[] args) throws IOException {
        int qtdLinhas = qtdLinhasArquivo();
        Joga[] todosJogadores = populaJogadores(qtdLinhas);

        ABBJoga jogadores = new ABBJoga();

        String entrada;
        entrada = MyIO.readLine();

        int id;
        while (!(entrada.equals("FIM"))) {
            id = Integer.parseInt(entrada);
            jogadores.inserir(todosJogadores[id]);
            entrada = MyIO.readLine();
        }

        ArquivoTextoEscrita log = new ArquivoTextoEscrita();
        //log.abrirArquivo("/tmp/696777_arvoreBinaria.txt");
        log.abrirArquivo("696777_arvoreBinaria.txt");
        Date date = new Date();
        long t1 = date.getTime();

        entrada = MyIO.readLine();
        while (!(entrada.equals("FIM"))) {
            int altura = pegaAlturaDeNome(todosJogadores, entrada);
            MyIO.println(jogadores.localizar(entrada, altura));
            entrada = MyIO.readLine();
        }

        Date date2 = new Date();
        long t2 = date2.getTime();
        long diferenca = t2 - t1;
        log.escrever("696777" + '\t' + diferenca + '\t' + jogadores.comparacoes);
        log.fecharArquivo();
    }

    // Retorna a altura do jogador com base no nome
    // Argumentos:
    //   Joga[] jogadores -> lista de objetos de jogadores
    //   String nome -> nome do jogador para retornar a altura
    private static int pegaAlturaDeNome(Joga[] jogadores, String nome) {
        int altura = 0;

        for (int i = 0; i < jogadores.length && altura == 0; i++) {
            if (jogadores[i].getNome().equals(nome)) {
                altura = jogadores[i].getAltura();
            }
        }

        return altura;
    }

    // Lê o arquivo players.csv e para cada linha cria um objeto de Jogador e o adiciona
    // na lista de jogadores, no final a retornando.
    // Argumentos:
    //   int qtd -> quantidade de jogadores que serão criados
    private static Joga[] populaJogadores(int qtd) throws IOException {
        Joga[] jogadores = new Joga[qtd];

        BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream("players.csv")));
        String linha = null;
        leitor.readLine();

        int n = 0;
        while ((linha = leitor.readLine()) != null) {
            jogadores[n] = new Joga(linha);
            n++;
        }
        leitor.close();

        return jogadores;
    }

    // Lê o arquivo players.csv e retorna a quantidade de linhas
    private static int qtdLinhasArquivo() throws IOException {
        File arquivoLeitura = new File("players.csv");
        LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivoLeitura));
        linhaLeitura.skip(arquivoLeitura.length());
        int lineNumber = linhaLeitura.getLineNumber();
        linhaLeitura.close();

        return (lineNumber - 1);
    }
}

class ArquivoTextoEscrita {
    private BufferedWriter saida;

    public void abrirArquivo(String nomeArquivo){
        try {
            saida = new BufferedWriter(new FileWriter(nomeArquivo));
        }
        catch (FileNotFoundException excecao) {
            System.out.println("Arquivo não encontrado");
        }
        catch (IOException excecao) {
            System.out.println("Erro na abertura do arquivo de escrita: " + excecao);
        }
    }

    public void fecharArquivo() {
        try {
            saida.close();
        }
        catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de escrita: " + excecao);
        }
    }

    public void escrever(String textoEntrada) {
        try {
            saida.write(textoEntrada);
            saida.newLine();
        }
        catch (IOException excecao){
            System.out.println("Erro de entrada/saída " + excecao);
        }
    }
}
