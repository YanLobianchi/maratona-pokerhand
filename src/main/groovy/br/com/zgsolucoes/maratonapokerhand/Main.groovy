package br.com.zgsolucoes.maratonapokerhand

import br.com.zgsolucoes.maratonapokerhand.mao.*
import br.com.zgsolucoes.maratonapokerhand.model.ResultadoRodada
import br.com.zgsolucoes.maratonapokerhand.model.Rodada

class Main {

	private final static List<Mao> MAOS = [
			new UmParMao(),
			new DoisParesMao(),
			new TrincaMao(),
			new SequenciaMao(),
			new FlushMao(),
			new FullHouseMao(),
			new QuadraMao(),
			new StraightFlushMao(),
			new RoyalFlushMao(),
	]

	List<Rodada> rodadas = []

	static void main(String[] args) {
		// inicar rodadas
	}

	List<ResultadoRodada> obtenhaResultadoRodadas() {

		List<ResultadoRodada> resultadosRodadas = []

		for (Rodada rodada in rodadas) {
			resultadosRodadas.add(rodada.obtenhaResultado(MAOS))
		}

		return resultadosRodadas

	}
}
