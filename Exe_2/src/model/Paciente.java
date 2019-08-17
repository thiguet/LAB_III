package model;

public class Paciente extends Pessoa {
	private static final long serialVersionUID = -2342074125044993048L;
	private String endereco;

	public Paciente(int codigo, String nome) {
		super(codigo, nome);
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}