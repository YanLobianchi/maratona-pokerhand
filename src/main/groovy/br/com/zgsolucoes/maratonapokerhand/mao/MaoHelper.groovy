package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta

import java.util.stream.Collectors

class MaoHelper {

	static Mao getMelhorMao(List<Carta> cartas) {
		Mao mao = new Mao(categoria: Categoria.CARTA_ALTA)
		Boolean mesmoNaipe = false
		Boolean sequencia = false
		Boolean maiorSequencia = false
		Boolean possuiGrupo = false

		if (possuiCincoComMesmoNaipe(cartas)) {
			mesmoNaipe = true
			mao.categoria = Categoria.FLUSH
		}

		if (possuiCincoCartasEmSequencia(cartas)) {
			sequencia = true
			mao.categoria = Categoria.SEQUENCIA
		}

		if (mesmoNaipe && sequencia) {
			mao.categoria = Categoria.STRAIGHT_FLUSH
		}

		if (mesmoNaipe && sequencia && possuiMaiorSequencia(cartas)) {
			maiorSequencia = true
			mao.categoria = Categoria.ROYAL_FLUSH
		}

		if (mao.categoria == Categoria.ROYAL_FLUSH || Categoria.STRAIGHT_FLUSH) {
			return mao
		}

		Grupo grupo = agrupe(cartas)
		if (grupo.existe()) {
			possuiGrupo = true
			if (grupo.qtdPrimeiraCombinacao == 4) {
				mao.categoria = Categoria.QUADRA
				return mao
			}

			if (grupo.qtdPrimeiraCombinacao == 3) {
				mao.categoria = Categoria.TRINCA
			}

			if (grupo.qtdPrimeiraCombinacao == 2) {
				mao.categoria = Categoria.UM_PAR
			}

			if (!grupo.possuiGrupo2()) {
				return mao
			} else {
				if (grupo.qtdSegundaCombinacao == 2) {
					if (mao.categoria == Categoria.TRINCA) {
						mao.categoria = Categoria.FULL_HOUSE
					}
					if (mao.categoria == Categoria.UM_PAR) {
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

	static Grupo agrupe(List<Carta> cartas) {
		List<List<Carta>> cartasPorValor = cartas.groupBy { it.valorCarta }.entrySet()*.value
		List<List<Carta>> cartasPorValorOrdenadas = cartasPorValor.stream().sorted(new GrupoComparator()
				.reversed()).collect(Collectors.<List<Carta>> toList())

		List<Carta> primeiraCombinacao = cartasPorValorOrdenadas.first()
		List<Carta> segundaCombinacao = cartasPorValorOrdenadas.get(1)

		return new Grupo(primeiraCombinacao, segundaCombinacao)
	}
}