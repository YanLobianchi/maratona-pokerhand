package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta
import br.com.zgsolucoes.maratonapokerhand.model.Naipe
import br.com.zgsolucoes.maratonapokerhand.model.ValorCarta
import spock.lang.Specification
import spock.lang.Unroll

class MaoHelperSpec extends Specification {

	final MaoHelper maoHelper = new MaoHelper()

	@Unroll
	def "Reconhece cartas de mesmo naipe"() {
		expect:
		maoHelper.extrairCincoComMesmoNaipe(cartas) == esperado

		where:
		cartas                                                            | esperado
		[new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.QUATRO),
		 new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.DEZ),
		 new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.TRES),
		 new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.SEIS),
		 new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.SETE),
		 new Carta(naipe: Naipe.PAUS, valorCarta: ValorCarta.VALETE),
		 new Carta(naipe: Naipe.ESPADAS, valorCarta: ValorCarta.VALETE),] | true

		[new Carta(naipe: Naipe.ESPADAS, valorCarta: ValorCarta.QUATRO),
		 new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.DEZ),
		 new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.TRES),
		 new Carta(naipe: Naipe.PAUS, valorCarta: ValorCarta.SEIS),
		 new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.SETE),
		 new Carta(naipe: Naipe.OUROS, valorCarta: ValorCarta.OITO),
		 new Carta(naipe: Naipe.ESPADAS, valorCarta: ValorCarta.SETE),]   | false
	}

	def "PossuiCincoCartasEmSequencia"() {
	}

	def "PossuiMaiorSequencia"() {
	}

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
