/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.azurecompute.xml;

import static org.jclouds.azurecompute.domain.NetworkConfiguration.AddressSpace;
import static org.jclouds.util.SaxUtils.currentOrNull;

import org.jclouds.http.functions.ParseSax;
import org.xml.sax.Attributes;

public class AddressSpaceHandler extends ParseSax.HandlerForGeneratedRequestWithResult<AddressSpace> {

   private String addressPrefix;

   private StringBuilder currentText = new StringBuilder();

   @Override
   public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) {
   }

   @Override
   public AddressSpace getResult() {
      AddressSpace result = AddressSpace.create(addressPrefix);
      resetState(); // handler is called in a loop.
      return result;
   }

   private void resetState() {
      addressPrefix = null;
   }

   @Override
   public void endElement(final String ignoredUri, final String ignoredName, final String qName) {
      if (qName.equals("AddressPrefix")) {
         addressPrefix = currentOrNull(currentText);
      }
      currentText.setLength(0);
   }

   @Override
   public void characters(final char ch[], final int start, final int length) {
      currentText.append(ch, start, length);
   }

}
