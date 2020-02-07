package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta
import groovy.transform.CompileStatic

@CompileStatic
class Mao implements Comparable<Mao> {
	Categoria categoria = Categoria.CARTA_ALTA
	Set<Carta> cartas

	@Override
	int compareTo(Mao o) {
		return 0
	}

	void setCategoria(Categoria categoria) {
		if(this.categoria < categoria) {
			this.categoria = categoria
		}
	}

}
