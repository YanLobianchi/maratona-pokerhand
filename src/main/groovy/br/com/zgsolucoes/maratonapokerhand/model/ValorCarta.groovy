package br.com.zgsolucoes.maratonapokerhand.model

import groovy.transform.CompileStatic

@CompileStatic
enum ValorCarta {
	DOIS('2'),
	TRES('3'),
	QUATRO('4'),
	CINCO('5'),
	SEIS('6'),
	SETE('7'),
	OITO('8'),
	NOVE('9'),
	DEZ('T'),
	VALETE('J'),
	RAINHA('Q'),
	REI('K'),
	AS('A'),

	final String codigo

	ValorCarta(final String codigo) {
		this.codigo = codigo
	}

	static ValorCarta valueOfCodigo(final String codigo) {
		return ValorCartaCache.CACHE[codigo]
	}

	private static class ValorCartaCache {
		private static final Map<String, ValorCarta> CACHE = [:] as HashMap

		static {
			for (final ValorCarta naipe : values()) {
				CACHE[naipe.codigo] = naipe
			}
		}
	}
}
