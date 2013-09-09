package cl.builderSoft.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CtxListener implements ServletContextListener {

	public CtxListener() {
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Contexto Terminado");
	}

	public void contextInitialized(ServletContextEvent arg0) {
//		System.out.println("Contexto Inicializado");
		URL url = null;
		try {
			url = new URL("http://www.emol.com");

			BufferedReader in = new BufferedReader(new InputStreamReader(url
					.openStream()));
			String str = null;
			while ((str = in.readLine()) != null) {
				System.out.println(str);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
