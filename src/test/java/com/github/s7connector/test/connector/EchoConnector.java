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
package com.github.s7connector.test.connector;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.s7connector.api.DaveArea;
import com.github.s7connector.api.S7Connector;

/**
 * Echo connector for testing
 * 
 * returns the same buffer in read() as given in write() regardless of the
 * byte-range or area
 * 
 * @author Thomas Rudin
 *
 */
public class EchoConnector implements S7Connector {

	private static final Logger LOGGER = LoggerFactory.getLogger(EchoConnector.class);

	@Override
	public byte[] read(DaveArea area, int areaNumber, int bytes, int offset) {
		LOGGER.debug("Reading area={} areaNumber={}, bytes={} offset={}", area, areaNumber, bytes, offset);
		return buffer;
	}

	public byte[] buffer;

	@Override
	public void write(DaveArea area, int areaNumber, int offset, byte[] buffer) {
		LOGGER.debug("Writing area={} areaNumber={}, offset={} buffer.length={}", area, areaNumber, offset,
				buffer.length);

		this.buffer = buffer;
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Size: {}", buffer.length);
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < buffer.length; i++) {
				stringBuilder.append(Integer.toHexString(buffer[i] & 0xFF) + ",");
			}
			LOGGER.debug(stringBuilder.toString());
		}
	}

	@Override
	public void close() throws IOException {
	}

}
