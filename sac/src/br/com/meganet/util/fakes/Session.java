package br.com.meganet.util.fakes;

import java.io.IOException;
import java.io.InputStream;

public class Session {

	public InputStream getStdout() {
		return new InputStream() {
			
			@Override
			public int read() throws IOException {
				return 0;
			}
		};
	}

	public void execCommand(String comando) {
		System.out.println(comando);
	}

}
