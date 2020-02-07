package br.com.zgsolucoes.maratonapokerhand.model

import groovy.transform.CompileStatic

@CompileStatic
enum Naipe {
	ESPADAS('s'),
	COPAS('h'),
	OUROS('d'),
	PAUS('c')

	final String codigo

	Naipe(final String codigo) {
		this.codigo = codigo
	}

	static Naipe valueOfCodigo(final String codigo) {
		return NaipeCache.CACHE[codigo]
	}



	private static class NaipeCache {
		private static final Map<String, Naipe> CACHE = [:] as HashMap

		static {
			for (final Naipe naipe : values()) {
				CACHE[naipe.codigo] = naipe
			}
		}
	}

}
