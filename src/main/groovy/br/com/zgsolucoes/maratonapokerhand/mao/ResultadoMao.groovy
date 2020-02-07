package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta

class ResultadoMao {
	Categoria categoria
	List<Carta> cartas

	ResultadoMao(Categoria categoria, List<Carta> cartas) {
		this.categoria = categoria
		this.cartas = cartas
	}
}
