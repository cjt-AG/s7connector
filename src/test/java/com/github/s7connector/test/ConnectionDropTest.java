/*
Copyright 2016 S7connector members (github.com/s7connector)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.github.s7connector.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.s7connector.api.DaveArea;
import com.github.s7connector.api.S7Connector;
import com.github.s7connector.api.factory.S7ConnectorFactory;

public class ConnectionDropTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionDropTest.class);

	@Test
	public void test() throws Exception {

		final int port = (int) (Math.random() * 10000) + 10000;
		final ServerSocket serverSocket = new ServerSocket(port);
		new Thread(() -> {
			try {
				Socket socket = serverSocket.accept();
			} catch (IOException e) {
				LOGGER.error("Interrupted {}", e);
			}
		});

		S7Connector connector = S7ConnectorFactory.buildTCPConnector().withHost("127.0.0.1").withPort(port).build();

		serverSocket.close();

		try {
			connector.read(DaveArea.DB, 1, 1, 0);
		} catch (IllegalArgumentException e) {
			return;
		}

		throw new IllegalArgumentException("fail-case not reached!");
	}

}
