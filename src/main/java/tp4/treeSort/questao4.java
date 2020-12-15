import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;

public class questao4 {
	/*Cria uma árvore de busca binária e insere todos os jogadores na ordem do .csv
    Depois, imprime os jogadores por caminhamento em ordem
    Também escreve num arquivo a quantidade de comparações feitas da busca, a duração e a matrícula de um dos autores */
    public static void main(String[] args) throws IOException {
        int qtdLinhas = qtdLinhasArquivo();
        Joga[] todosJogadores = populaJogadores(qtdLinhas);
        Date date = new Date();
        long t1 = date.getTime();
        ABBJoga jogadores = new ABBJoga();

       //insere todos os jogadores na arvore
        for(int i=0;i<qtdLinhas;i++) {
        	jogadores.inserir(todosJogadores[i]);
        }

        ArquivoTextoEscrita log = new ArquivoTextoEscrita();
        log.abrirArquivo("/tmp/718301_arvoreBinaria.txt");
        //log.abrirArquivo("C:\\Users\\Gustavo\\Desktop\\718301_arvoreBinaria.txt");
        
        jogadores.emordem(jogadores.raiz);
        
        Date date2 = new Date();
        long t2 = date2.getTime();
        long diferenca = t2 - t1;
        log.escrever("718301" + '\t' + diferenca + '\t' + jogadores.comparacoes);
        log.fecharArquivo();
    }
    
    /* Lê o arquivo players.csv e para cada linha cria um objeto de Jogador e o adiciona na lista de jogadores, no final a retornando.
    Argumentos:
    int qtd -> quantidade de jogadores que serão criados*/
    private static Joga[] populaJogadores(int qtd) throws IOException {
        Joga[] jogadores = new Joga[qtd];

        BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream("/tmp/players.csv")));
        //BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Gustavo\\Desktop\\players.csv")));
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
        File arquivoLeitura = new File("/tmp/players.csv");
        //File arquivoLeitura = new File("players.csv");
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


class ABBJoga {
    public int comparacoes = 0;
    public NodoJogador raiz;

    public ABBJoga()
    {
        raiz = null;
    }

    public Boolean arvoreVazia() {
        return this.raiz == null;
    }

    private NodoJogador adicionar(NodoJogador raizArvore, Joga jogadorNovo) {
        if (raizArvore == null){
            comparacoes++;
        	raizArvore = new NodoJogador(jogadorNovo);
        } else {
            String raizNome = raizArvore.item.getNome();
            if (raizNome.compareTo(jogadorNovo.getNome()) >= 0) {
            	comparacoes++;
                raizArvore.esquerda = adicionar(raizArvore.esquerda, jogadorNovo);

            } else {
                if (raizNome.compareTo(jogadorNovo.getNome()) < 0) {
                	comparacoes++;
                    raizArvore.direita = adicionar(raizArvore.direita, jogadorNovo);

                } else
                    System.out.println("O jogador " + jogadorNovo.getNome() + " já foi inserido anteriormente na árvore.");
            }
        }
        return raizArvore;
    }

    public void inserir(Joga jogadorNovo) {
        this.raiz = adicionar(this.raiz, jogadorNovo);
    }
    // faz o caminhamento em ordem, imprimindo todo o conteudo de jogador
    public void emordem(NodoJogador raizArvore) {
    	if(raizArvore != null) {
    		emordem(raizArvore.esquerda);
    		MyIO.println("[" + raizArvore.item.getId() + " ## " +
    				raizArvore.item.getNome() + " ## " +
    				raizArvore.item.getAltura() + " ## " +
    				raizArvore.item.getPeso() +  " ## " +
    				raizArvore.item.getAnoNascimento() + " ## " +
    				raizArvore.item.getUniversidade() + " ## " +
    				raizArvore.item.getCidadeNascimento() + " ## " +
    				raizArvore.item.getEstadoNascimento() + "]");
    		emordem(raizArvore.direita);    		
    	}
    }

    public String localizar(String nome) {
        return localizar(raiz, nome);
    }

    private String localizar(NodoJogador raizArvore, String nome) {
        String resultado;

        if (raizArvore == null) {
            comparacoes++;
            resultado = "NAO";
        } else {
            String raizNome = raizArvore.item.getNome();
            if (raizNome.compareTo(nome) == 0) {
                resultado = nome + " SIM";
            } else if (raizNome.compareTo(nome) < 0) {
                resultado = raizNome + " " + localizar(raizArvore.direita, nome);
            } else {
                resultado = raizNome + " " + localizar(raizArvore.esquerda, nome);
            }
        }

        return resultado;
    }
}

class NodoJogador {

    Joga item;           // contém os dados do aluno armazenado no nodo da árvore.
    NodoJogador direita;    // referência ao nodo armazenado, na árvore, à direita do aluno em questão.
    NodoJogador esquerda;   // referência ao nodo armazenado, na árvore, à esquerda do aluno em questão.

    public NodoJogador(Joga registro) {
        item = registro;
        direita = null;
        esquerda = null;
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

    public Joga (String n, int p) {
        nome = n;
        peso = p;
    }

    public Joga () {
        this.setNome("ROBERTO");
    }

    public void imprimir() {
        MyIO.println("## " + this.id + " ## " +
                this.nome + " ## " +
                this.altura + " ## " +
                this.peso +  " ## " +
                this.anoNascimento + " ## " +
                this.universidade + " ## " +
                this.cidadeNascimento + " ## " +
                this.estadoNascimento + " ## ");
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

