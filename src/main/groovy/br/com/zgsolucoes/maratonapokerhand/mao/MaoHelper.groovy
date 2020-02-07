package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta

class MaoHelper {

	Mao getMelhorMao(List<Carta> cartas) {

		Categoria melhorCategoria
		final Boolean mesmoNaipe
		final Boolean sequencial
		final Boolean maiorSequencia
		final Boolean possuiGrupo

		if(possuiCincoComMesmoNaipe(cartas)) {
			mesmoNaipe = true
			melhorCategoria = Categoria.FLUSH
		}

		if(possuiCincoCartasEmSequencia(cartas)){
			sequencial = true
		}

		return new Mao()
	}




	Boolean possuiCincoComMesmoNaipe(List<Carta> cartas) {

	}

	Boolean possuiCincoCartasEmSequencia(List<Carta> cartas) {

	}

	Boolean possuiMaiorSequencia(List<Carta> cartas) {

	}

	List<Carta> getGrupos(List<Carta> cartas) {

	}
}
