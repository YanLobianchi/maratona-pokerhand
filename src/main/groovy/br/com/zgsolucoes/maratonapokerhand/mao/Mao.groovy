package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta
import groovy.transform.CompileStatic

@CompileStatic
interface Mao extends Comparable<? extends Mao> {
	Categoria getCategoria()

	void setCategoria(final Categoria categoria)

	Set<Carta> getCartas()

	void setCartas(Set<Carta> cartas)
}
