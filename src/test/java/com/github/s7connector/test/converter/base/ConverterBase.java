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
package com.github.s7connector.test.converter.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ConverterBase {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConverterBase.class);

	protected static void dump(byte[] b) {
		if (LOGGER.isDebugEnabled()) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < b.length; i++) {
				stringBuilder.append(Integer.toHexString(b[i] & 0xFF) + ",");
			}
			LOGGER.debug(stringBuilder.toString());
		}
	}

	protected static void dump(Byte[] b) {
		if (LOGGER.isDebugEnabled()) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < b.length; i++) {
				stringBuilder.append(Integer.toHexString(b[i] & 0xFF) + ",");
			}
			LOGGER.debug(stringBuilder.toString());
		}
	}

}
