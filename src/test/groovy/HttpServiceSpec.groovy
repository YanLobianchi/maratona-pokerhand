import br.com.zgsolucoes.maratonapokerhand.service.HttpService
import spock.lang.Specification

class HttpServiceSpec extends Specification {

	void 'testa resultado requisicao'() {
		setup:
		HttpService httpAcces = new HttpService()
		String url = 'http://172.22.1.41:8080/poker-game/arquivo/index'

		when:
		List response = httpAcces.sendRequest(url, 'GET')

		then:
		response
	}


    void 'testa '() {
		setup:

        List<String> jogos = []

        URL urlBase = new URL('http://172.22.1.41:8080/poker-game/arquivo/index')
        HttpURLConnection primeiraRequisicao = (HttpURLConnection) urlBase.openConnection()
        primeiraRequisicao.setRequestMethod('GET')

        String resultadoPrimeiraReq = (String) primeiraRequisicao.content.text
        List<String> codigosObtidos = resultadoPrimeiraReq.findAll('(?<=code=)[^"]+')

        String sessionId = obtenhaSession(primeiraRequisicao)
    // seg for        state=f8f336a5-825f-4bd6-9845-a51fc97c245f; JSESSIONID=22889123BBEF58A27A6DDFAFF027EFFB
        // ter for    state=d0b1bed2-52de-4915-aebf-5e5a35b5993a; JSESSIONID=22889123BBEF58A27A6DDFAFF027EFFB
        // qua for //sun.net.www.MessageHeader@12d45e55 pairs: {null: HTTP/1.1 200}{Set-Cookie: state=06e7a890-19fa-442a-85aa-4a2a41a6538c}{Content-Type: text/xml;charset=UTF-8}{Transfer-Encoding: chunked}{Date: Fri, 07 Feb 2020 18:55:23 GMT}

        HttpURLConnection sessaoAtual = primeiraRequisicao

        for(String codigo in codigosObtidos){

            URL url2 = new URL("http://172.22.1.41:8080/poker-game/arquivo/arquivos?code=${codigo}")
            HttpURLConnection segundaRequisicao = (HttpURLConnection) url2.openConnection()
            segundaRequisicao.setRequestMethod("GET")
            segundaRequisicao.setRequestProperty('Cookie', getCookies(obtenhaState(sessaoAtual), sessionId))
            segundaRequisicao.setRequestProperty('Content-Type', 'text/html')
            sessaoAtual = segundaRequisicao

            String resultadoSegundaReq = (String) segundaRequisicao.content.text
            List<String> pastasObtidas = resultadoSegundaReq.findAll('(?<=arquivo\\?id=)[^"]+')

            for(String pasta in pastasObtidas){
                URL url3 = new URL("http://172.22.1.41:8080/poker-game/arquivo/arquivo?id=${pasta}")
                HttpURLConnection terceiraRequisicao = (HttpURLConnection) url3.openConnection()
                terceiraRequisicao.setRequestMethod("GET")
                terceiraRequisicao.setRequestProperty('Cookie', getCookies(obtenhaState(sessaoAtual), sessionId))
                terceiraRequisicao.setRequestProperty('Content-Type', 'text/html')
                sessaoAtual = terceiraRequisicao

                String resultadoTerceiraReq = (String) terceiraRequisicao.content.text

                jogos.add(resultadoTerceiraReq)
            }
        }



		when:
		//List response = httpAcces.sendRequest(url, 'GET')
        jogos

		then:
        jogos
	}

    String obtenhaState(HttpURLConnection requisicao){
        requisicao.responses.toString().find('state=[^\\\\}\\\\;]+')
    }

    String obtenhaSession(HttpURLConnection requisicao){
        requisicao.responses.toString().find('JSESSIONID=[^\\}\\;]+')
    }

    String getCookies(String s1, String s2){
       return "$s1; $s2"
    }

	void 'testa resultado requisicao 2'() {
		setup:


		URL url1 = new URL('http://172.22.1.41:8080/poker-game/arquivo/index')
		HttpURLConnection con1 = (HttpURLConnection) url1.openConnection()
		con1.setRequestMethod("GET")

		List str = con1.getHeaderFields()['Set-Cookie'] as List<String>
		String cookies = str[0].concat('; ').concat(str[1])


		URL url2 = new URL("http://172.22.1.41:8080/poker-game/arquivo/arquivos?code=HH20161020")
		HttpURLConnection con2 = (HttpURLConnection) url2.openConnection()
		con2.setRequestMethod("GET")
		con2.setRequestProperty('Cookie', cookies)
		con2.setRequestProperty('Content-Type', 'text/html')

        con2.getHeaderField('Set-Cookie')

        String cookieReq3 = con2.getHeaderField('Set-Cookie')

        URL url3 = new URL('http://172.22.1.41:8080/poker-game/arquivo/arquivo?id=SEgyMDE2MTAyMCBUMTcwMjY3MDA0OSBObyBMaW1pdCBIb2xkJ2VtICQwLDIzICsgJDAsMDIudHh0LnhtbA==')
        HttpURLConnection con3 = (HttpURLConnection) url3.openConnection()
        con3.setRequestMethod("GET")
        con3.setRequestProperty('Cookie', cookieReq3.concat('; ').concat(str[1].toString()))
        con3.setRequestProperty('Content-Type', 'text/html')




		/*HttpService httpAcces = new HttpService()
		String url = 'http://172.22.1.41:8080/poker-game/arquivo/index'*/

		/*def url = new URL('http://groovy.codehaus.org/')
		def connection = url.openConnection()
		connection.requestMethod = 'GET'
		if (connection.responseCode == 200) {
			println connection.content.text
			println connection.contentType
			println connection.lastModified
			connection.headerFields.each { println "> ${it}"}
		} else {
			println...*/

		when:
		//List response = httpAcces.sendRequest(url, 'GET')

		url2

		then:
		//response
		url2
		con1
	}

}
