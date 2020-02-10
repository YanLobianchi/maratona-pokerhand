package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta
import br.com.zgsolucoes.maratonapokerhand.model.Naipe
import br.com.zgsolucoes.maratonapokerhand.model.ValorCarta
import spock.lang.Specification
import spock.lang.Unroll

class MaoHelperSpec extends Specification {

	@Unroll
	def "Reconhece cartas de mesmo naipe"() {
		given:
		MaoHelper maoHelper = new MaoHelper(cartas)

		expect:
		maoHelper.cartasMesmoNaipe == cartasMesmoNaipe

		where:
		cartasMesmoNaipe                          | cartas
		[new Carta(Naipe.COPAS, ValorCarta.TRES),
		 new Carta(Naipe.COPAS, ValorCarta.QUATRO),
		 new Carta(Naipe.COPAS, ValorCarta.SEIS),
		 new Carta(Naipe.COPAS, ValorCarta.SETE),
		 new Carta(Naipe.COPAS, ValorCarta.DEZ),] | cartasMesmoNaipe + [new Carta(Naipe.PAUS, ValorCarta.VALETE),
																		new Carta(Naipe.ESPADAS, ValorCarta.VALETE),]

		[]                                        | [new Carta(Naipe.ESPADAS, ValorCarta.QUATRO),
													 new Carta(Naipe.COPAS, ValorCarta.DEZ),
													 new Carta(Naipe.COPAS, ValorCarta.TRES),
													 new Carta(Naipe.PAUS, ValorCarta.SEIS),
													 new Carta(Naipe.COPAS, ValorCarta.SETE),
													 new Carta(Naipe.OUROS, ValorCarta.OITO),
													 new Carta(Naipe.ESPADAS, ValorCarta.SETE),]
	}

	def "Reconhece 5 cartas em sequência"() {
		given:
		MaoHelper maoHelper
		List<Carta> cartasSequencia = [new Carta(Naipe.COPAS, ValorCarta.DOIS),
									   new Carta(Naipe.PAUS, ValorCarta.TRES),
									   new Carta(Naipe.COPAS, ValorCarta.QUATRO),
									   new Carta(Naipe.PAUS, ValorCarta.CINCO),
									   new Carta(Naipe.COPAS, ValorCarta.SEIS)]
		List<Carta> todasCartas = cartasSequencia + [new Carta(Naipe.PAUS, ValorCarta.VALETE),
													 new Carta(Naipe.ESPADAS, ValorCarta.VALETE),]

		when:
		maoHelper = new MaoHelper(todasCartas)

		then:
		maoHelper.cartasEmSequencia == cartasSequencia
	}

	def "Reconhece que não tem 5 cartas em sequência"() {
		given:
		MaoHelper maoHelper
		List<Carta> todasCartas = [new Carta(Naipe.COPAS, ValorCarta.DOIS),
								   new Carta(Naipe.ESPADAS, ValorCarta.CINCO),
								   new Carta(Naipe.COPAS, ValorCarta.QUATRO),
								   new Carta(Naipe.PAUS, ValorCarta.CINCO),
								   new Carta(Naipe.COPAS, ValorCarta.SEIS),
								   new Carta(Naipe.PAUS, ValorCarta.VALETE),
								   new Carta(Naipe.ESPADAS, ValorCarta.VALETE),]

		when:
		maoHelper = new MaoHelper(todasCartas)

		then:
		maoHelper.cartasEmSequencia == []
	}

	def 'agrupe() deve retornar corretamente'() {
		given: 'Criando Jogador'
		List<Carta> cartas = [
				new Carta(Naipe.COPAS, ValorCarta.RAINHA),
				new Carta(Naipe.ESPADAS, ValorCarta.RAINHA),
				new Carta(Naipe.OUROS, ValorCarta.RAINHA),
				new Carta(Naipe.PAUS, ValorCarta.CINCO),
				new Carta(Naipe.OUROS, ValorCarta.CINCO),
				new Carta(Naipe.PAUS, ValorCarta.AS),
				new Carta(Naipe.ESPADAS, ValorCarta.AS)
		]

		when: 'executando agrupe()'
		Grupo grupo = MaoHelper.agrupe(cartas)

		then:
		grupo.qtdPrimeiraCombinacao == 3
		grupo.qtdSegundaCombinacao == 2
	}

}
