package br.com.zgsolucoes.maratonapokerhand.model

import br.com.zgsolucoes.maratonapokerhand.mao.Mao
import br.com.zgsolucoes.maratonapokerhand.mao.MaoHelper

class Jogador implements Comparable<Jogador> {
	List<Carta> cartas

	Jogador(List<Carta> cartas) {
		this.cartas = cartas
	}

	void calculaMelhorMao() {
	}

	@Override
	int compareTo(Jogador o) {
		return 0
	}

	Mao getMelhorMao(List<Carta> cartas) {
		return MaoHelper.getMelhorMao(cartas)
	}
}
