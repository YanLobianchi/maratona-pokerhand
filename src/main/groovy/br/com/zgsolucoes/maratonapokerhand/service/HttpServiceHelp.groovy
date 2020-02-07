package br.com.zgsolucoes.maratonapokerhand.service

class HttpServiceHelp {

	final static String urlBase = 'http://172.22.1.41:8080/poker-game/arquivo/'

	static Map obtenhaCookie(List response, String jsessionid) {
		String state = response.get(1).find('(?<=state=)[^,]+')
		Map paramsCookie = [state: state, JSESSIONID: jsessionid]
		return paramsCookie
	}

	static String obtenhaJSessionId(List response){
		return response.get(1).find('(?<=JSESSIONID=)[^;]+')
	}

	List<String> obtenhaLinksJogos(String conteudo){
		return conteudo.findAll('(?<=code=)[^"]+')
	}

	List<String> obtenhaLinksArquivos(String conteudo){
		return conteudo.findAll('(?<=arquivo\\?id=)[^"]+')
	}
}
