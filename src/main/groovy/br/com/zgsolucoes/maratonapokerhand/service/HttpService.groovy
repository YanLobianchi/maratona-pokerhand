package br.com.zgsolucoes.maratonapokerhand.service

import com.google.gson.Gson
import org.apache.http.HttpHeaders
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.client.methods.RequestBuilder
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils

class HttpService {


	List<String> sendRequest(String url, String method, Map<String, Object> params = null) {
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(2000)
				.setSocketTimeout(3000)
				.build()
		HttpUriRequest request = null

		if (params) {
			StringEntity entity = new StringEntity(new Gson().toJson(params), "UTF-8")
			request = RequestBuilder.create(method)
					.setConfig(requestConfig)
					.setUri(url)
					.setHeader(HttpHeaders.CONTENT_TYPE, "text/html")
					.addHeader('Cookie', "state=${params.getAt('state')}; JSESSIONID=${params.getAt('JSESSIONID')}")
					.setEntity(entity)
					.build()
		} else {
			request = RequestBuilder.create(method)
					.setConfig(requestConfig)
					.setUri(url)
					.build()
		}

		String req = "REQUEST:" + "\n" + request.getRequestLine() + "\n" + "Headers: " +
				request.getAllHeaders() + "\n" + EntityUtils.toString() + "\n"

		//todo criar um map ou objeto para armazenar a resposta da requisicao

		HttpClientBuilder.create().build().withCloseable { httpClient ->

			httpClient.execute(request).withCloseable { response ->

				String res = "RESPONSE:" + "\n" + response.getStatusLine() + "\n" + "Headers: " +
						response.getAllHeaders() + "\n" +
						(response.getEntity() != null ? EntityUtils.toString(response.getEntity()) : "") + "\n"

				System.out.println(req + "\n" + res)

				return Arrays.asList(req, res)
			}
		}
	}

	List<String> sendSimpleRequest(){

		List<String> jogos = []

		URL urlBase = new URL('http://172.22.1.41:8080/poker-game/arquivo/index')
		HttpURLConnection primeiraRequisicao = (HttpURLConnection) urlBase.openConnection()
		primeiraRequisicao.setRequestMethod('GET')

		String resultadoPrimeiraReq = (String) primeiraRequisicao.content.text
		List<String> codigosObtidos = resultadoPrimeiraReq.findAll('(?<=code=)[^"]+')

		String sessionId = obtenhaSession(primeiraRequisicao)

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

        return jogos
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

	List<String> obtenhaRodadas() {

	}
}
