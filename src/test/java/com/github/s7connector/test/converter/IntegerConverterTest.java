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
package com.github.s7connector.test.converter;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.s7connector.impl.serializer.converter.IntegerConverter;

public class IntegerConverterTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(IntegerConverterTest.class);

	@Test
	public void insert1() {

		IntegerConverter c = new IntegerConverter();
		byte[] buffer = new byte[] { 0, 0, 0, 0 };
		c.insert(666, buffer, 0, 0, 2);
		debug(buffer);
		Assert.assertEquals(0x02, buffer[0]);
		Assert.assertEquals((byte) 0x9a, buffer[1]);
	}

	@Test
	public void insert2() {
		IntegerConverter c = new IntegerConverter();
		byte[] buffer = new byte[] { 0, 0, 0, 0 };
		c.insert(666, buffer, 1, 0, 2);
		Assert.assertEquals(0x00, buffer[0]);
		Assert.assertEquals(0x02, buffer[1]);
		Assert.assertEquals((byte) 0x9a, buffer[2]);
		Assert.assertEquals(0x00, buffer[3]);
	}

	@Test
	public void extract1() {
		IntegerConverter c = new IntegerConverter();
		byte[] buffer = new byte[] { 0x02, (byte) 0x9a, 0, 0 };
		debug(buffer);
		int i = c.extract(Integer.class, buffer, 0, 0);
		Assert.assertEquals(666, i);
	}

	@Test
	public void extract2() {
		IntegerConverter c = new IntegerConverter();
		byte[] buffer = new byte[] { 0, 0, 0x02, (byte) 0x9a, 0, 0 };
		debug(buffer);
		int i = c.extract(Integer.class, buffer, 2, 0);
		Assert.assertEquals(666, i);
	}

	private void debug(byte[] buffer) {
		if (LOGGER.isDebugEnabled()) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < buffer.length; i++) {
				stringBuilder.append(Integer.toHexString(buffer[i] & 0xFF) + ",");
			}
			LOGGER.debug(stringBuilder.toString());
		}
	}

}
