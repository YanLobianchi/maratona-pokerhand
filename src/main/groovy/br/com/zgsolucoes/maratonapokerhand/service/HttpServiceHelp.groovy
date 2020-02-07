package br.com.zgsolucoes.maratonapokerhand.service

class HttpServiceHelp {

	final static String urlBase = 'http://172.22.1.41:8080/poker-game/arquivo'

	static Map obtenhaCookie(List response, String jsessionid) {
		String state = response.get(1).find('(?<=state=)[^,]+')
		Map paramsCookie = [state: state, JSESSIONID: jsessionid]
		return paramsCookie
	}

	static List<String> obtenhaLinksJogos(String conteudo){
		return conteudo.findAll('(?<=code=)[^"]+')
	}

	static List<String> obtenhaLinksArquivos(String conteudo){
		return conteudo.findAll('(?<=arquivo\\?id=)[^"]+')
	}

	static String obtenhaSession(HttpURLConnection requisicao){
		requisicao.responses.toString().find('JSESSIONID=[^\\}\\;]+')
	}

	static void setaParamsRequiscao(HttpURLConnection connection, HttpURLConnection sessaoAtual, String sessionId){
		connection.setRequestMethod("GET")
		connection.setRequestProperty('Cookie', getCookies(obtenhaState(sessaoAtual), sessionId))
		connection.setRequestProperty('Content-Type', 'text/html')
	}

	static String obtenhaState(HttpURLConnection requisicao){
		requisicao.responses.toString().find('state=[^\\\\}\\\\;]+')
	}

	static String getCookies(String s1, String s2){
		return "$s1; $s2"
	}

}
