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

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.s7connector.impl.serializer.converter.RealConverter;
import com.github.s7connector.test.converter.base.ConverterBase;

public class RealConverterTest extends ConverterBase

{
	private static final Logger LOGGER = LoggerFactory.getLogger(RealConverterTest.class);

	@Test
	public void insert1() {
		RealConverter c = new RealConverter();
		byte[] buffer = new byte[4];
		c.insert(3.141f, buffer, 0, 0, 4);
		dump(buffer);
	}

	@Test
	public void loopTest1() {
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			loop(r.nextFloat() * r.nextInt(1000000));
		}
	}

	public void loop(float f) {
		LOGGER.debug("Testing: {}", f);

		RealConverter c = new RealConverter();
		byte[] buffer = new byte[4];
		c.insert(f, buffer, 0, 0, 4);

		float ret = c.extract(Float.class, buffer, 0, 0);

		Assert.assertEquals(f, ret, 0.1);
	}

}
