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
        URL urlBase = new URL('http://172.22.1.41:8080/poker-game/arquivo/index')
        HttpURLConnection primeiraRequisicao = (HttpURLConnection) urlBase.openConnection()
        primeiraRequisicao.setRequestMethod('GET')

        String jsessionid = primeiraRequisicao.getHeaderFields()['Set-Cookie'][1]

        String resultadoPrimeiraReq = (String) primeiraRequisicao.content.text
        List<String> codigosObtidos = resultadoPrimeiraReq.findAll('(?<=code=)[^"]+')

        for(String codigo in codigosObtidos){
            URL url2 = new URL("http://172.22.1.41:8080/poker-game/arquivo/arquivos?code=${codigo}")
            HttpURLConnection segundaRequisicao = (HttpURLConnection) url2.openConnection()
            segundaRequisicao.setRequestMethod("GET")
            segundaRequisicao.setRequestProperty('Cookie', getCookiesParaHeaders(primeiraRequisicao, jsessionid))
            segundaRequisicao.setRequestProperty('Content-Type', 'text/html')


            String resultadoSegundaReq = (String) segundaRequisicao.content.text
            List<String> pastasObtidas = resultadoSegundaReq.findAll('(?<=arquivo\\?id=)[^"]+')

            for(String pasta in pastasObtidas){
                URL url3 = new URL("http://172.22.1.41:8080/poker-game/arquivo/arquivo?id=${pasta}")
                HttpURLConnection terceiraRequisicao = (HttpURLConnection) url3.openConnection()
                terceiraRequisicao.setRequestMethod("GET")
                terceiraRequisicao.setRequestProperty('Cookie', getCookiesParaHeaders(segundaRequisicao, jsessionid))
                terceiraRequisicao.setRequestProperty('Content-Type', 'text/html')

                String resultadoTerceiraReq = (String) terceiraRequisicao.content.text
            }
        }

		when:
		//List response = httpAcces.sendRequest(url, 'GET')
        terceiraRequisicao

		then:
        terceiraRequisicao
	}

    String getCookiesParaHeaders(HttpURLConnection requisicao, String jsessionid){
        String state = requisicao.getHeaderField('Set-Cookie')
        return "$state; $jsessionid"
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
