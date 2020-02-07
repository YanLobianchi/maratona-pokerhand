package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta
import br.com.zgsolucoes.maratonapokerhand.model.Naipe
import br.com.zgsolucoes.maratonapokerhand.model.ValorCarta
import spock.lang.Specification
import spock.lang.Unroll

class MaoHelperSpec extends Specification {

	@Unroll
	def "Reconhece cartas de mesmo naipe"() {
		expect:
		new MaoHelper(cartas).cartasMesmoNaipe == cartasMesmoNaipe

		where:
		cartasMesmoNaipe                                             | cartas
		[new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.QUATRO),
		 new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.DEZ),
		 new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.TRES),
		 new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.SEIS),
		 new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.SETE)] | cartasMesmoNaipe + [new Carta(naipe: Naipe.PAUS, valorCarta: ValorCarta.VALETE),
																						   new Carta(naipe: Naipe.ESPADAS, valorCarta: ValorCarta.VALETE),]

		[]                                                           | [new Carta(naipe: Naipe.ESPADAS, valorCarta: ValorCarta.QUATRO),
																		new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.DEZ),
																		new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.TRES),
																		new Carta(naipe: Naipe.PAUS, valorCarta: ValorCarta.SEIS),
																		new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.SETE),
																		new Carta(naipe: Naipe.OUROS, valorCarta: ValorCarta.OITO),
																		new Carta(naipe: Naipe.ESPADAS, valorCarta: ValorCarta.SETE),]
	}

	def "Reconhece 5 cartas em sequência"() {
		given:
		MaoHelper maoHelper
		List<Carta> cartasSequencia = [new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.DOIS),
									   new Carta(naipe: Naipe.PAUS, valorCarta: ValorCarta.TRES),
									   new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.QUATRO),
									   new Carta(naipe: Naipe.PAUS, valorCarta: ValorCarta.CINCO),
									   new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.SEIS)]
		List<Carta> todasCartas = cartasSequencia + [new Carta(naipe: Naipe.PAUS, valorCarta: ValorCarta.VALETE),
													 new Carta(naipe: Naipe.ESPADAS, valorCarta: ValorCarta.VALETE),]

		when:
		maoHelper = new MaoHelper(todasCartas)

		then:
		maoHelper.cartasEmSequencia == cartasSequencia
	}

	def "Reconhece que não tem 5 cartas em sequência"() {
		given:
		MaoHelper maoHelper
		List<Carta> todasCartas = [new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.DOIS),
								   new Carta(naipe: Naipe.ESPADAS, valorCarta: ValorCarta.CINCO),
								   new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.QUATRO),
								   new Carta(naipe: Naipe.PAUS, valorCarta: ValorCarta.CINCO),
								   new Carta(naipe: Naipe.COPAS, valorCarta: ValorCarta.SEIS),
								   new Carta(naipe: Naipe.PAUS, valorCarta: ValorCarta.VALETE),
								   new Carta(naipe: Naipe.ESPADAS, valorCarta: ValorCarta.VALETE),]

		when:
		maoHelper = new MaoHelper(todasCartas)

		then:
		maoHelper.cartasEmSequencia == []
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
