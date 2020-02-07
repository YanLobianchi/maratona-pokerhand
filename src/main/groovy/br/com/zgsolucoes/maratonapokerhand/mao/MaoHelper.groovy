package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta
import br.com.zgsolucoes.maratonapokerhand.model.ValorCarta

class MaoHelper {

	Mao getMelhorMao(List<Carta> cartas) {
		Mao mao = new Mao()
		final Boolean mesmoNaipe
		final Boolean sequencia
		final Boolean maiorSequencia
		final Boolean possuiGrupo

		if(possuiCincoComMesmoNaipe(cartas)) {
			mesmoNaipe = true
			mao.categoria = Categoria.FLUSH
		}

		if(possuiCincoCartasEmSequencia(cartas)){
			sequencia = true
			mao.categoria = Categoria.SEQUENCIA
		}

		if(mesmoNaipe && sequencia) {
			mao.categoria = Categoria.STRAIGHT_FLUSH
		}

		if(mesmoNaipe && sequencia && possuiMaiorSequencia(cartas)) {
			maiorSequencia = true
			mao.categoria = Categoria.ROYAL_FLUSH
			return mao
		}

		Grupo grupo = agrupe(cartas)
		if(grupo.existe()) {
			possuiGrupo = true
			if(grupo.primeiraCombinacao == 4) {
				mao.categoria = Categoria.QUADRA
				return mao
			}

			if(grupo.primeiraCombinacao == 3) {
				mao.categoria = Categoria.TRINCA
			}

			if(grupo.primeiraCombinacao == 2) {
				mao.categoria = Categoria.UM_PAR
			}

			if(!grupo.possuiGrupo2()) {
				return mao
			} else {
				if(grupo.segundaCombinacao == 2){
					if(mao.categoria == Categoria.TRINCA) {
						mao.categoria = Categoria.FULL_HOUSE
					}
					if(mao.categoria == Categoria.UM_PAR) {
						mao.categoria = Categoria.DOIS_PARES
					}
				}
			}

		}
		return mao
	}


	Boolean possuiCincoComMesmoNaipe(List<Carta> cartas) {

	}

	Boolean possuiCincoCartasEmSequencia(List<Carta> cartas) {

	}

	Boolean possuiMaiorSequencia(List<Carta> cartas) {

	}

	Grupo agrupe(List<Carta> cartas) {
		Map<ValorCarta, List<Carta>> cartasPorValor = cartas.groupBy { it.valorCarta }
		List<Map.Entry<ValorCarta, List<Carta>>> cartasPorValorOrdenadas = cartasPorValor.entrySet().toList().sort {-it.value.size()}
		BigInteger primeiraCombinacao = cartasPorValorOrdenadas.first().value.size()
		BigInteger segundaCombinacao = cartasPorValorOrdenadas.size() > 1 ? cartasPorValorOrdenadas.get(1).value.size() : 0

		return new Grupo(primeiraCombinacao, segundaCombinacao)
	}
}
