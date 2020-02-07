package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta
import br.com.zgsolucoes.maratonapokerhand.model.ValorCarta

class GrupoComparator implements Comparator<List<Carta>> {


	@Override
	int compare(List<Carta> lista1, List<Carta> lista2) {
		if (lista1.size() > lista2.size()) {
			return 1
		}
		if (lista1.size() < lista2.size()) {
			return -1
		}
		if (lista1.size() == lista2.size()) {
			return lista1?.first()?.compareTo(lista2?.first())
		}
		return 0
	}
}
