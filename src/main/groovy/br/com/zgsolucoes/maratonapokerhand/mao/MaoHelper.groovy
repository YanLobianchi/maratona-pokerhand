package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta
import br.com.zgsolucoes.maratonapokerhand.model.Naipe

import java.util.stream.Collectors

class MaoHelper {

	final List<Carta> cartasMesmoNaipe
	final List<Carta> cartasEmSequencia
	final private Grupo grupo

	MaoHelper(final List<Carta> cartas) {
		final List<Carta> cartasOrdenadas = cartas.sort()
		this.cartasEmSequencia = extrairCincoCartasEmSequencia(cartasOrdenadas)
		this.cartasMesmoNaipe = extrairCincoComMesmoNaipe(cartasOrdenadas)
		this.grupo = agrupe(cartasOrdenadas)
	}

	Boolean isGrupoExiste() {
		return grupo.temPeloMenosUmaCombinacao
	}

	Boolean isGrupoTemDuasCombinacoes() {
		return grupo.temDuasCombinacoes
	}

	Integer getQtdPrimeiraCombinacao() {
		return grupo.temPeloMenosUmaCombinacao ? grupo.qtdPrimeiraCombinacao : 0
	}

	Integer getQtdSegundaCombinacao() {
		return grupo.temDuasCombinacoes ? grupo.qtdSegundaCombinacao : 0
	}

	List<Carta> getPrimeiraCombinacao() {
		return grupo.primeiraCombinacao
	}

	List<Carta> getSegundaCombinacao() {
		return grupo.segundaCombinacao
	}

	private static Carta extrairMaiorCarta(List<Carta> cartas) {
		return cartas.last()
	}

	private static List<Carta> extrairCincoComMesmoNaipe(List<Carta> cartas) {
		final Map<Naipe, List<Carta>> cartasAgrupadas = cartas
				.stream().collect(Collectors.<Naipe, List<Carta>> groupingBy({ Carta carta -> carta.naipe }))

		return cartasAgrupadas.find { Map.Entry<Naipe, List<Carta>> entry -> entry.value.size() >= 5 }?.value ?: []
	}

	private static List<Carta> extrairCincoCartasEmSequencia(List<Carta> cartas) {
		int valorAnterior = cartas.first().valorCarta.ordinal()
		final List<Carta> cartasEmSequencia = [cartas.first()]

		for (int i = 1; i < cartas.size(); i++) {
			if (cartasEmSequencia.size() > 5) {
				cartasEmSequencia.remove(0)
			}
			final Carta cartaAtual = cartas[i]

			final int valorAtual = cartaAtual.valorCarta.ordinal()

			if (valorAtual == valorAnterior + 1) {
				valorAnterior++
			} else if (cartasEmSequencia.size() != 5) {
				cartasEmSequencia.clear()
				valorAnterior = valorAtual
			}
			cartasEmSequencia.add(cartaAtual)
		}

		return cartasEmSequencia.size() == 5 ? cartasEmSequencia : []
	}

	static Grupo agrupe(List<Carta> cartas) {
		List<List<Carta>> cartasPorValor = cartas.groupBy { it.valorCarta }.entrySet()*.value
		List<List<Carta>> cartasPorValorOrdenadas = cartasPorValor.stream()
																  .sorted(new GrupoComparator().reversed())
																  .collect(Collectors.<List<Carta>> toList())

		List<Carta> primeiraCombinacao = cartasPorValorOrdenadas.first()
		List<Carta> segundaCombinacao = cartasPorValorOrdenadas.size() > 1 ? cartasPorValorOrdenadas.get(1) : []

		return new Grupo(primeiraCombinacao, segundaCombinacao)
	}
}
