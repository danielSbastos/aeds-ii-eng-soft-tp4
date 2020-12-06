package tp4.arvoreBinariaBusca.arvore;

import tp4.tabelaHashEnderecamentoAberto.MyIO;

public class Joga {
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
