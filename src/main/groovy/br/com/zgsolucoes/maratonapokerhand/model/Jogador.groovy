package br.com.zgsolucoes.maratonapokerhand.model

import br.com.zgsolucoes.maratonapokerhand.mao.GrupoComparator
import br.com.zgsolucoes.maratonapokerhand.mao.MaoHelper
import br.com.zgsolucoes.maratonapokerhand.mao.ResultadoMao

class Jogador implements Comparable<Jogador> {
	private MaoHelper maoHelper
	List<Carta> cartas
	ResultadoMao melhorMao

	Jogador(final List<Carta> cartas) {
		this.cartas = cartas.toSorted(Comparator.reverseOrder())
	}

	MaoHelper extrairMaoHelper(List<Carta> cartasMesa) {
		if (!maoHelper) {
			maoHelper = new MaoHelper(cartas + cartasMesa)
		}
		return maoHelper
	}

	@Override
	int compareTo(Jogador o) {
		if (melhorMao?.categoria > o.melhorMao?.categoria) {
			return 1
		}
		if (melhorMao?.categoria < o.melhorMao?.categoria) {
			return -1
		}
		return new GrupoComparator().compare(cartas, o.cartas)
	}

}
