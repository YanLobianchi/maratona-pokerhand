package br.com.zgsolucoes.maratonapokerhand

import br.com.zgsolucoes.maratonapokerhand.mao.*
import br.com.zgsolucoes.maratonapokerhand.model.ResultadoRodada
import br.com.zgsolucoes.maratonapokerhand.model.Rodada

class Main {

	List<Mao> maos = [
			new RoyalFlushMao(),
			new StraightFlushMao(),
			new QuadraMao(),
			new FullHouseMao(),
			new FlushMao(),
			new SequenciaMao(),
			new TrincaMao(),
			new DoisParesMao(),
			new UmParMao(),
	]

	List<Rodada> rodadas = []

	static void main(String[] args) {
		// inicar rodadas
	}

	List<ResultadoRodada> obtenhaResultadoRodadas() {

		List<ResultadoRodada> resultadosRodadas = []

		for(rodada in rodadas) {
			rodada.calculeMelhoresMaos(maos)
			resultadosRodadas.add(rodada.obtenhaResultado())
		}

		return resultadosRodadas

	}
}
