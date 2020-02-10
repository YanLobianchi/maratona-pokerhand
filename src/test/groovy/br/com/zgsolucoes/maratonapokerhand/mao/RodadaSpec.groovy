package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.*
import spock.lang.Specification

class RodadaSpec extends Specification {

	final private static List<Mao> MAOS = [
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

	@SuppressWarnings("GroovyAccessibility")
	def "testa calculeMelhoresMaos"() {
		given:
		def cartasMesa = [new Carta(Naipe.COPAS, ValorCarta.SEIS),
						  new Carta(Naipe.COPAS, ValorCarta.SETE),
						  new Carta(Naipe.COPAS, ValorCarta.DEZ),
						  new Carta(Naipe.PAUS, ValorCarta.VALETE),
						  new Carta(Naipe.ESPADAS, ValorCarta.VALETE),]

		def jogadores = [
				new Jogador([new Carta(Naipe.COPAS, ValorCarta.TRES),
							 new Carta(Naipe.COPAS, ValorCarta.QUATRO),]),
				new Jogador([new Carta(Naipe.ESPADAS, ValorCarta.QUATRO),
							 new Carta(Naipe.PAUS, ValorCarta.DEZ),]),
				new Jogador([new Carta(Naipe.ESPADAS, ValorCarta.AS),
							 new Carta(Naipe.PAUS, ValorCarta.AS),])
		]

		def rodada = new Rodada(1L, cartasMesa, jogadores)

		when:
		rodada.calculeMelhoresMaos(MAOS)

		then:
		rodada.jogadores[0].melhorMao.categoria == Categoria.FLUSH
		rodada.jogadores[1].melhorMao.categoria == Categoria.DOIS_PARES
		rodada.jogadores[2].melhorMao.categoria == Categoria.DOIS_PARES
	}

	def "testa Jogador com RoyalFlush"() {
		given:
		def cartasMesa = [new Carta(Naipe.OUROS, ValorCarta.SEIS),
						  new Carta(Naipe.COPAS, ValorCarta.SETE),
						  new Carta(Naipe.ESPADAS, ValorCarta.DEZ),
						  new Carta(Naipe.ESPADAS, ValorCarta.REI),
						  new Carta(Naipe.ESPADAS, ValorCarta.VALETE),]

		def vencedor = new Jogador([new Carta(Naipe.ESPADAS, ValorCarta.RAINHA),
									new Carta(Naipe.ESPADAS, ValorCarta.AS),])
		def jogadores = [new Jogador([new Carta(Naipe.PAUS, ValorCarta.DEZ),
									  new Carta(Naipe.COPAS, ValorCarta.AS),]),
						 new Jogador([new Carta(Naipe.OUROS, ValorCarta.DOIS),
									  new Carta(Naipe.COPAS, ValorCarta.TRES),]), vencedor]

		def rodada = new Rodada(1L, cartasMesa, jogadores)

		when:
		final ResultadoRodada resultadoRodada = rodada.obtenhaResultado(MAOS)

		then:
		resultadoRodada.ranking == Ranking.VITORIA
		resultadoRodada.jogadores.first() == vencedor
		resultadoRodada.jogadores.first().melhorMao.categoria == Categoria.ROYAL_FLUSH
	}

	def "testa Empate"() {
		given:
		def cartasMesa = [new Carta(Naipe.ESPADAS, ValorCarta.SEIS),
						  new Carta(Naipe.ESPADAS, ValorCarta.SETE),
						  new Carta(Naipe.ESPADAS, ValorCarta.DEZ),
						  new Carta(Naipe.PAUS, ValorCarta.VALETE),
						  new Carta(Naipe.ESPADAS, ValorCarta.VALETE),]

		def jogadoresEmpatados = [
				new Jogador([new Carta(Naipe.PAUS, ValorCarta.DEZ),
							 new Carta(Naipe.COPAS, ValorCarta.AS),]),
				new Jogador([new Carta(Naipe.OUROS, ValorCarta.DEZ),
							 new Carta(Naipe.PAUS, ValorCarta.AS),])
		]
		def jogadores = jogadoresEmpatados + [new Jogador([new Carta(Naipe.OUROS, ValorCarta.DOIS),
														   new Carta(Naipe.COPAS, ValorCarta.TRES),])]

		def rodada = new Rodada(1L, cartasMesa, jogadores)

		when:
		final ResultadoRodada resultadoRodada = rodada.obtenhaResultado(MAOS)

		then:
		resultadoRodada.ranking == Ranking.EMPATE
		resultadoRodada.jogadores == jogadoresEmpatados
	}

	def "testa Desempate"() {
		given:
		def cartasMesa = [new Carta(Naipe.ESPADAS, ValorCarta.SEIS),
						  new Carta(Naipe.ESPADAS, ValorCarta.SETE),
						  new Carta(Naipe.ESPADAS, ValorCarta.DEZ),
						  new Carta(Naipe.PAUS, ValorCarta.VALETE),
						  new Carta(Naipe.ESPADAS, ValorCarta.VALETE),]

		def vencedor = new Jogador([new Carta(Naipe.OUROS, ValorCarta.VALETE),
									new Carta(Naipe.PAUS, ValorCarta.AS),])
		def jogadores = [new Jogador([new Carta(Naipe.PAUS, ValorCarta.DEZ),
									  new Carta(Naipe.COPAS, ValorCarta.AS),]),
						 new Jogador([new Carta(Naipe.OUROS, ValorCarta.DOIS),
									  new Carta(Naipe.COPAS, ValorCarta.TRES),]), vencedor]

		def rodada = new Rodada(1L, cartasMesa, jogadores)

		when:
		final ResultadoRodada resultadoRodada = rodada.obtenhaResultado(MAOS)

		then:
		resultadoRodada.ranking == Ranking.VITORIA
		resultadoRodada.jogadores.first() == vencedor
	}

}
