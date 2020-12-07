package tp4.arvoreBinariaBuscaArvoreBinariaBusca;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;

public class MainVerde {
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

        ArquivoTextoEscritaa log = new ArquivoTextoEscritaa();
        log.abrirArquivo("/tmp/696777_arvoreArvore.txt");
        //log.abrirArquivo("696777_arvoreBinaria.txt");
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

    private static int pegaAlturaDeNome(Joga[] jogadores, String nome) {
        int altura = 0;

        for (int i = 0; i < jogadores.length && altura == 0; i++) {
            if (jogadores[i].getNome().equals(nome)) {
                altura = jogadores[i].getAltura();
            }
        }

        return altura;
    }

    private static Joga[] populaJogadores(int qtd) throws IOException {
        Joga[] jogadores = new Joga[qtd];

        //BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream("players.csv")));
        BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream("/tmp/players.csv")));
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
        //File arquivoLeitura = new File("players.csv");
        File arquivoLeitura = new File("/tmp/players.csv");
        LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivoLeitura));
        linhaLeitura.skip(arquivoLeitura.length());
        int lineNumber = linhaLeitura.getLineNumber();
        linhaLeitura.close();

        return (lineNumber - 1);
    }
}

class ArquivoTextoEscritaa {
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

class ABBJoga {
    public int comparacoes = 0;
    private NodoJogador raiz;

    public ABBJoga() {
        raiz = null;
    }

    public String localizar(String nome, int altura) {
        return localizarPorAltura(raiz, nome, altura);
    }

    public void inserir(Joga jogadorNovo) {
        this.raiz = inserirPorAltura(this.raiz, jogadorNovo);
    }

    private NodoJogador inserirPorAltura(NodoJogador raizArvore, Joga jogadorNovo) {
        if (raizArvore == null) {
            raizArvore = new NodoJogador(jogadorNovo);
            raizArvore.arvore.raiz = raizArvore.arvore.inserirPorNome(raizArvore.arvore.raiz, jogadorNovo);
        } else {
            int raizAltura = raizArvore.item.getAltura();
            if ((jogadorNovo.getAltura() % 17) < (raizAltura % 17)) {
                raizArvore.esquerda = inserirPorAltura(raizArvore.esquerda, jogadorNovo);
            } else {
                if ((jogadorNovo.getAltura() % 17) > (raizAltura % 17)) {
                    raizArvore.direita = inserirPorAltura(raizArvore.direita, jogadorNovo);
                } else {
                    raizArvore.arvore.raiz = raizArvore.arvore.inserirPorNome(raizArvore.arvore.raiz, jogadorNovo);
                }
            }
        }

        return raizArvore;
    }

    private NodoJogador inserirPorNome(NodoJogador raizArvore, Joga jogadorNovo) {
        if (raizArvore == null){
            raizArvore = new NodoJogador(jogadorNovo);
        } else {
            String raizNome = raizArvore.item.getNome();
            if (raizNome.compareTo(jogadorNovo.getNome()) >= 0) {
                raizArvore.esquerda = inserirPorNome(raizArvore.esquerda, jogadorNovo);

            } else {
                if (raizNome.compareTo(jogadorNovo.getNome()) < 0) {
                    raizArvore.direita = inserirPorNome(raizArvore.direita, jogadorNovo);
                } else
                    System.out.println("O jogador " + jogadorNovo.getNome() + " já foi inserido anteriormente na árvore.");
            }
        }
        return raizArvore;
    }

    private String localizarPorAltura(NodoJogador raizArvore, String nome, int altura) {
        String resultado = "";

        if (raizArvore == null) {
            comparacoes++;
        } else {
            int raizAltura = raizArvore.item.getAltura();
            if ((raizAltura % 17) == (altura % 17)) {
                resultado = raizAltura % 17 + " " + localizarPorNome(raizArvore.arvore.raiz, nome);
            } else if ((raizAltura % 17) < (altura % 17)) {
                resultado = raizAltura % 17 + " " + localizarPorAltura(raizArvore.direita, nome, altura);
            } else {
                resultado = raizAltura % 17 + " " + localizarPorAltura(raizArvore.esquerda, nome, altura);
            }
        }

        return resultado;
    }

    private String localizarPorNome(NodoJogador raizArvore, String nome) {
        String resultado;

        if (raizArvore == null) {
            comparacoes++;
            resultado = "NAO";
        } else {
            String raizNome = raizArvore.item.getNome();
            if (raizNome.compareTo(nome) == 0) {
                comparacoes++;
                resultado = nome + " SIM";
            } else if (raizNome.compareTo(nome) < 0) {
                comparacoes++;
                resultado = raizNome + " " + localizarPorNome(raizArvore.direita, nome);
            } else {
                resultado = raizNome + " " + localizarPorNome(raizArvore.esquerda, nome);
            }
        }

        return resultado;
    }
}

class Joga {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private int anoNascimento;
    private String universidade;
    private String cidadeNascimento;
    private String estadoNascimento;

    // Cria um jogador com os argumentos
    public Joga (String nome, int altura, int peso, int anoNascimento, String universidade, String cidadeNascimento, String estadoNascimento) {
        this.id = 1;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.anoNascimento = anoNascimento;
        this.universidade = universidade;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    // Cria um jogador com base numa string contendo a informação dele
    public Joga (String entrada) {
        int i;

        String dadosUsuario[] = entrada.split(",",-1);

        i = Integer.parseInt(dadosUsuario[0]);
        this.setId(i);
        this.setNome(dadosUsuario[1]);

        i = Integer.parseInt(dadosUsuario[2]);
        this.setAltura(i);

        i = Integer.parseInt(dadosUsuario[3]);
        this.setPeso(i);

        if(dadosUsuario[4].isEmpty()) {
            this.setUniversidade("nao informado");
        } else {
            this.setUniversidade(dadosUsuario[4]);
        }

        i = Integer.parseInt(dadosUsuario[5]);
        this.setAnoNascimento(i);

        if(dadosUsuario[6].isEmpty()) {
            this.setCidadeNascimento("nao informado");
        } else {
            this.setCidadeNascimento(dadosUsuario[6]);
        }

        if(dadosUsuario[7].isEmpty()) {
            this.setEstadoNascimento("nao informado");
        } else {
            this.setEstadoNascimento(dadosUsuario[7]);
        }
    }

    public Joga clonar() {
        Joga clone = new Joga(this.nome,
                this.altura,
                this.peso,
                this.anoNascimento,
                this.universidade,
                this.cidadeNascimento,
                this.estadoNascimento);

        return clone;
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

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
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

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }
}

class NodoJogador {
    Joga item;           // contém os dados do jogador armazenado no nodo da árvore.
    NodoJogador direita;    // referência ao nodo armazenado, na árvore, à direita do jogador em questão.
    NodoJogador esquerda;   // referência ao nodo armazenado, na árvore, à esquerda do jogador em questão.
    ABBJoga arvore;

    // Instancia um novo nodo com o jogador
    public NodoJogador(Joga registro) {
        item = registro;
        arvore = new ABBJoga();
        direita = null;
        esquerda = null;
    }
}

class MyIO {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
    private static String charset = "ISO-8859-1";

    public static void setCharset(String charset_){
        charset = charset_;
        in = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
    }

    public static void print(){
    }

    public static void print(int x){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(x);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
    }

    public static void print(double x){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(x);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
    }

    public static void print(String x){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(x);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
    }

    public static void print(boolean x){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(x);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
    }

    public static void print(char x){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(x);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
    }

    public static void println(){
    }

    public static void println(int x){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.println(x);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
    }

    public static void println(double x){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.println(x);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
    }

    public static void println(String x){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.println(x);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
    }

    public static void println(boolean x){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.println(x);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
    }

    public static void println(char x){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.println(x);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
    }

    public static void printf(String formato, double x){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.printf(formato, x);// "%.2f"
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
    }

    public static double readDouble(){
        double d = -1;
        try{
            d = Double.parseDouble(readString().trim().replace(",","."));
        }catch(Exception e){}
        return d;
    }

    public static double readDouble(String str){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(str);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        return readDouble();
    }

    public static float readFloat(){
        return (float) readDouble();
    }

    public static float readFloat(String str){
        return (float) readDouble(str);
    }

    public static int readInt(){
        int i = -1;
        try{
            i = Integer.parseInt(readString().trim());
        }catch(Exception e){}
        return i;
    }

    public static int readInt(String str){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(str);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        return readInt();
    }

    public static String readString(){
        String s = "";
        char tmp;
        try{
            do{
                tmp = (char)in.read();
                if(tmp != '\n' && tmp != ' ' && tmp != 13){
                    s += tmp;
                }
            }while(tmp != '\n' && tmp != ' ');
        }catch(IOException ioe){
            System.out.println("lerPalavra: " + ioe.getMessage());
        }
        return s;
    }

    public static String readString(String str){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(str);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        return readString();
    }

    public static String readLine(){
        String s = "";
        char tmp;
        try{
            do{
                tmp = (char)in.read();
                if(tmp != '\n' && tmp != 13){
                    s += tmp;
                }
            }while(tmp != '\n');
        }catch(IOException ioe){
            System.out.println("lerPalavra: " + ioe.getMessage());
        }
        return s;
    }

    public static String readLine(String str){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(str);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        return readLine();
    }

    public static char readChar(){
        char resp = ' ';
        try{
            resp  = (char)in.read();
        }catch(Exception e){}
        return resp;
    }

    public static char readChar(String str){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(str);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        return readChar();
    }

    public static boolean readBoolean(){
        boolean resp = false;
        String str = "";

        try{
            str = readString();
        }catch(Exception e){}

        if(str.equals("true") || str.equals("TRUE") || str.equals("t") || str.equals("1") ||
                str.equals("verdadeiro") || str.equals("VERDADEIRO") || str.equals("V")){
            resp = true;
        }

        return resp;
    }

    public static boolean readBoolean(String str){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(str);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        return readBoolean();
    }

    public static void pause(){
        try{
            in.read();
        }catch(Exception e){}
    }

    public static void pause(String str){
        try {
            PrintStream out = new PrintStream(System.out, true, charset);
            out.print(str);
        }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
        pause();
    }
}