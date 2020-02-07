package br.com.zgsolucoes.maratonapokerhand.model

class Carta implements Comparable<Carta> {
	Naipe naipe
	ValorCarta valorCarta

	@Override
	int compareTo(Carta o) {
		return 0
	}
}
