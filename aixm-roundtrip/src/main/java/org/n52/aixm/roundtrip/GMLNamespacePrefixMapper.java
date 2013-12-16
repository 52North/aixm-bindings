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

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class GMLNamespacePrefixMapper extends NamespacePrefixMapper {

	private static Map<String, String> prefixes = new HashMap<>();
	
	static {
		prefixes.put("http://www.opengis.net/gml/3.2", "gml");
		prefixes.put("http://www.isotc211.org/2005/gmd", "gmd");
		prefixes.put("http://www.w3.org/1999/xlink", "xlink");
		prefixes.put("http://www.isotc211.org/2005/gco", "gco");
		prefixes.put("http://www.isotc211.org/2005/gss", "gss");
		prefixes.put("http://www.isotc211.org/2005/gts", "gts");
		prefixes.put("http://www.isotc211.org/2005/gsr", "gsr");		
	}
	
	@Override
	public String getPreferredPrefix(String namespaceUri, String suggestion,
			boolean requirePrefix) {
		Map<String, String> prefs = getPrefixes();
		if (prefs.containsKey(namespaceUri)) {
			return prefs.get(namespaceUri);
		}
		return suggestion;
	}
	
	protected Map<String, String> getPrefixes() {
		return new HashMap<>(prefixes);
	}

}
