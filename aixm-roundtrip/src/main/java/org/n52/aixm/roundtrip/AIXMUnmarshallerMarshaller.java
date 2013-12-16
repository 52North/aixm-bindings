/**
 * Copyright (C) 2013
 * by 52 North Initiative for Geospatial Open Source Software GmbH
 *
 * Contact: Andreas Wytzisk
 * 52 North Initiative for Geospatial Open Source Software GmbH
 * Martin-Luther-King-Weg 24
 * 48155 Muenster, Germany
 * info@52north.org
 *
 * This program is free software; you can redistribute and/or modify it under
 * the terms of the GNU General Public License version 2 as published by the
 * Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; even without the implied
 * WARRANTY OF MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program (see gnu-gpl v2.txt). If not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA or
 * visit the Free Software Foundation web page, http://www.fsf.org.
 */
package org.n52.aixm.roundtrip;

import java.util.HashMap;
import java.util.Map;


import aero.aixm.v510.ObjectFactory;

public class AIXMUnmarshallerMarshaller extends BasicUnmarshallerMarshaller {

	public AIXMUnmarshallerMarshaller() {
		super(ObjectFactory.class.getPackage().getName());
		setPrefixMapper(new AIXMPrefixMapper());
	}
	
	public static class AIXMPrefixMapper extends GMLNamespacePrefixMapper {

		private static Map<String, String> prefixes = new HashMap<>();
		
		static {
			prefixes.put("http://www.aixm.aero/schema/5.1", "aixm");
			prefixes.put("http://www.aixm.aero/schema/5.1/event", "event");
			prefixes.put("http://www.aixm.aero/schema/5.1/message", "message");
		}
		
		@Override
		protected Map<String, String> getPrefixes() {
			Map<String, String> supPrefs = super.getPrefixes();
			supPrefs.putAll(prefixes);
			return supPrefs;
		}

	}
}
