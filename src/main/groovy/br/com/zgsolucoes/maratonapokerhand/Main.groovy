package br.com.zgsolucoes.maratonapokerhand

import br.com.zgsolucoes.maratonapokerhand.mao.*

class Main {


	Main() {

	}

	static obterMaos() {
		return [
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
	}
}
