package com.tp4.arvoreBinariaBusca;

import com.tp4.arvoreBinariaBusca.arvore.ABBJoga;
import com.tp4.arvoreBinariaBusca.arvore.Joga;
import com.tp4.tabelaHashEnderecamentoAberto.MyIO;

import java.io.*;

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

        entrada = MyIO.readLine();
        while (!(entrada.equals("FIM"))) {
            MyIO.println(jogadores.localizar(entrada));
            entrada = MyIO.readLine();
        }
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
