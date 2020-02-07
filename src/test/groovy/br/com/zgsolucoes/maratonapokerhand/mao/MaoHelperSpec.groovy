package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta
import br.com.zgsolucoes.maratonapokerhand.model.Naipe
import br.com.zgsolucoes.maratonapokerhand.model.ValorCarta
import spock.lang.Specification

class MaoHelperSpec extends Specification {

	def 'agrupe() deve retornar corretamente'() {
		given: 'Criando Jogador'
		List<Carta> cartas = [
				new Carta(valorCarta: ValorCarta.RAINHA, naipe: Naipe.COPAS),
				new Carta(valorCarta: ValorCarta.RAINHA, naipe: Naipe.ESPADAS),
				new Carta(valorCarta: ValorCarta.RAINHA, naipe: Naipe.OUROS),
				new Carta(valorCarta: ValorCarta.CINCO, naipe: Naipe.PAUS),
				new Carta(valorCarta: ValorCarta.CINCO, naipe: Naipe.OUROS),
				new Carta(valorCarta: ValorCarta.AS, naipe: Naipe.PAUS),
				new Carta(valorCarta: ValorCarta.AS, naipe: Naipe.ESPADAS)
		]

		when: 'executando agrupe()'
		Grupo grupo = MaoHelper.agrupe(cartas)

		then:
		grupo.qtdPrimeiraCombinacao == 3
		grupo.qtdSegundaCombinacao == 2
	}

}
