package br.com.zgsolucoes.maratonapokerhand.mao

import br.com.zgsolucoes.maratonapokerhand.model.Carta

class GrupoComparator implements Comparator<List<Carta>> {


	@Override
	int compare(List<Carta> lista1, List<Carta> lista2) {
		if (!lista1) {
			return -1
		}
		if (!lista2) {
			return 1
		}

		List<Carta> copyLista1 = lista1.toSorted()
		List<Carta> copyLista2 = lista2.toSorted()

		if (copyLista1.size() > copyLista2.size()) {
			return 1
		}
		if (copyLista1.size() < copyLista2.size()) {
			return -1
		}
		if (copyLista1.size() == copyLista2.size()) {
			for (int i = copyLista1.size() - 1; i > -1; i--) {
				if (copyLista1[i] > copyLista2[i]) {
					return 1
				}
				if (copyLista1[i] < copyLista2[i]) {
					return -1
				}
			}
		}
		return 0
	}
}
