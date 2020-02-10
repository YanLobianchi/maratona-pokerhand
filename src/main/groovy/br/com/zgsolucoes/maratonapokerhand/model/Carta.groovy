package br.com.zgsolucoes.maratonapokerhand.model

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Carta implements Comparable<Carta> {
	Naipe naipe
	ValorCarta valorCarta

	@Override
	int compareTo(Carta o) {
		int ordinalThis = valorCarta.ordinal()
		int ordinalO = o.valorCarta.ordinal()
		if (ordinalThis > ordinalO) {
			return 1
		}

		if (ordinalThis < ordinalO) {
			return -1
		}

		return 0
	}

	Carta(Naipe naipe, ValorCarta valorCarta){
		this.naipe = naipe
		this.valorCarta = valorCarta
	}
}
