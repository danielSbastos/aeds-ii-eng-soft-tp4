package tp4.tabelaHashEnderecamentoSeparado.hashtable;

public class Jogador {

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
