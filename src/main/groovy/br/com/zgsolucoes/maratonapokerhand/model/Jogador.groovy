package br.com.zgsolucoes.maratonapokerhand.model

import br.com.zgsolucoes.maratonapokerhand.mao.Mao

class Jogador implements Comparable<Jogador> {
	List<Carta> cartas
	Mao melhorMao

	void calculaMelhorMao() {
	}

	@Override
	int compareTo(Jogador o) {
		return 0
	}
}
