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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

public class Roundtrip {

	public void execute(InputStream in, OutputStream target) throws Exception {
		AIXMUnmarshallerMarshaller gmu = new AIXMUnmarshallerMarshaller();
		JAXBElement<?> g = gmu.unmarshal(in);
		gmu.marshal(g, target);
	}

	public static void main(String[] args) throws FileNotFoundException, Exception {
		new Roundtrip().execute(
				Roundtrip.class.getResourceAsStream("navaid.xml"),
				new FileOutputStream(new File("/home/matthes/opt/navaid-re.xml")));
	}

	public ByteArrayOutputStream execute(InputStream dataStream) throws JAXBException {
		AIXMUnmarshallerMarshaller gmu = new AIXMUnmarshallerMarshaller();
		JAXBElement<?> je = gmu.unmarshal(dataStream);
		return gmu.marshal(je);
	}

}
