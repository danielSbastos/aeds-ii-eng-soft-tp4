package tp4.arvoreBinariaBusca;

import tp4.arvoreBinariaBusca.arvore.ABBJoga;
import tp4.arvoreBinariaBusca.arvore.Joga;
import tp4.tabelaHashEnderecamentoAberto.MyIO;

import java.io.*;
import java.util.Date;

public class Main {
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
        log.abrirArquivo("/tmp/696777_arvoreBinaria.txt");
        Date date = new Date();
        long t1 = date.getTime();

        entrada = MyIO.readLine();
        while (!(entrada.equals("FIM"))) {
            MyIO.println(jogadores.localizar(entrada));
            entrada = MyIO.readLine();
        }

        Date date2 = new Date();
        long t2 = date2.getTime();
        long diferenca = t2 - t1;
        log.escrever("696777" + '\t' + diferenca + '\t' + jogadores.comparacoes);
        log.fecharArquivo();
    }

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

