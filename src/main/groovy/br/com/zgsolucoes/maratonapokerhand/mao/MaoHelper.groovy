package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta
import br.com.zgsolucoes.maratonapokerhand.model.Naipe
import br.com.zgsolucoes.maratonapokerhand.model.ValorCarta

import java.util.stream.Collectors

class MaoHelper {

	final List<Carta> cartasMesmoNaipe
	final List<Carta> cartasEmSequencia
	final Grupo grupo

	MaoHelper(List<Carta> cartas) {
		this.cartasEmSequencia = extrairCincoCartasEmSequencia(cartas)
		this.cartasMesmoNaipe = extrairCincoComMesmoNaipe(cartas)
		this.grupo = agrupe(cartas)
	}

	Categoria getMelhorMao(List<Carta> cartas) {
		Categoria categoria = Categoria.CARTA_ALTA
		Boolean mesmoNaipe = false
		Boolean sequencia = false

		if (this.cartasMesmoNaipe) {
			mesmoNaipe = true
			categoria = Categoria.FLUSH
		}

		if (this.cartasEmSequencia) {
			sequencia = true
			categoria = Categoria.SEQUENCIA
		}

		if (mesmoNaipe && sequencia) {
			categoria = Categoria.STRAIGHT_FLUSH
		}

		if (mesmoNaipe && sequencia && this.cartasEmSequencia.last().valorCarta == ValorCarta.AS) {
			categoria = Categoria.ROYAL_FLUSH
		}

		if (categoria == Categoria.ROYAL_FLUSH || Categoria.STRAIGHT_FLUSH) {
			return categoria
		}

		Grupo grupo = agrupe(cartas)
		if (grupo.existe()) {
			if (grupo.qtdPrimeiraCombinacao == 4) {
				return Categoria.QUADRA
			}

			if (grupo.qtdPrimeiraCombinacao == 3) {
				categoria = Categoria.TRINCA
			}

			if (grupo.qtdPrimeiraCombinacao == 2) {
				categoria = Categoria.UM_PAR
			}

			if (!grupo.possuiGrupo2()) {
				return categoria
			} else {
				if (grupo.qtdSegundaCombinacao == 2) {
					if (categoria == Categoria.TRINCA) {
						categoria = Categoria.FULL_HOUSE
					}
					if (categoria == Categoria.UM_PAR) {
						categoria = Categoria.DOIS_PARES
					}
				}
			}

		}
		return categoria
	}


	private static List<Carta> extrairCincoComMesmoNaipe(List<Carta> cartas) {
		final Map<Naipe, List<Carta>> cartasAgrupadas = cartas
				.stream().collect(Collectors.<Naipe, List<Carta>> groupingBy({ Carta carta -> carta.naipe }))

		return cartasAgrupadas.find { Map.Entry<Naipe, List<Carta>> entry -> entry.value.size() >= 5 }?.value
	}

	private static List<Carta> extrairCincoCartasEmSequencia(List<Carta> cartas) {
		int valorAnterior = cartas[0]?.valorCarta?.ordinal()
		final List<Carta> cartasEmSequencia = []

		for (int i = 1; i < cartas.size(); i++) {
			final int valorAtual = cartas[i].valorCarta.ordinal()
			if (valorAtual == valorAnterior + 1) {
				cartasEmSequencia.add(cartas[i])
				valorAnterior++
			} else {
				cartasEmSequencia.clear()
				valorAnterior = valorAtual
			}
		}

		return cartasEmSequencia
	}

	static Grupo agrupe(List<Carta> cartas) {
		List<List<Carta>> cartasPorValor = cartas.groupBy { it.valorCarta }.entrySet()*.value
		List<List<Carta>> cartasPorValorOrdenadas = cartasPorValor.stream()
				.sorted(new GrupoComparator().reversed())
				.collect(Collectors.<List<Carta>> toList())

		List<Carta> primeiraCombinacao = cartasPorValorOrdenadas.first()
		List<Carta> segundaCombinacao = cartasPorValorOrdenadas.get(1)

		return new Grupo(primeiraCombinacao, segundaCombinacao)
	}
}
