package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta
import groovy.transform.CompileStatic

@CompileStatic
class Mao implements Comparable<Mao> {
	Categoria categoria
	Set<Carta> cartas

	@Override
	int compareTo(Mao o) {
		return 0
	}
}
