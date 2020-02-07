package br.com.zgsolucoes.maratonapokerhand.mao

import groovy.transform.CompileStatic

@CompileStatic
abstract class Mao {
	abstract ResultadoMao reconhecerMao(final MaoHelper maoHelper)
}
