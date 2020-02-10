package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta

class ResultadoMao implements Comparable<ResultadoMao> {
	Categoria categoria
	List<Carta> cartas

	ResultadoMao(Categoria categoria, List<Carta> cartas) {
		this.categoria = categoria
		this.cartas = cartas
	}

	@Override
	int compareTo(ResultadoMao o) {
		if (!o || categoria.ordinal() > o.categoria.ordinal()) {
			return 1
		}
		if (categoria.ordinal() < o.categoria.ordinal()) {
			return -1
		}
		if (categoria.ordinal() == o.categoria.ordinal()) {
			return cartas.last() > o.cartas.last() ? 1 : (cartas.last() < o.cartas.last() ? -1 : 0)
		}
	}
}
