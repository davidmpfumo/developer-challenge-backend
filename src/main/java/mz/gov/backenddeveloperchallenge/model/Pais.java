package mz.gov.backenddeveloperchallenge.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "pais")
public class Pais {

	@Id
	@GeneratedValue
	private Long id;
	@NonNull
	private String nome;
	private String capital;
	private String regiao;
	private String subRegiao;
	private String area;

	public Pais() {

	}

	public Pais(String nome) {
		this.nome = nome;
	}
	
	public Pais(String nome, String capital, String regiao, String subRegiao, String area) {
		this.nome = nome;
		this.capital = capital;
		this.regiao = regiao;
		this.subRegiao = subRegiao;
		this.area = area;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public String getSubRegiao() {
		return subRegiao;
	}

	public void setSubRegiao(String subRegiao) {
		this.subRegiao = subRegiao;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}