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
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class BasicUnmarshallerMarshaller {

	private String packageName;
	private NamespacePrefixMapper prefixMapper;
	
	public BasicUnmarshallerMarshaller(String pkg) {
		this.packageName = pkg;
	}
	
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public NamespacePrefixMapper getPrefixMapper() {
		return prefixMapper;
	}

	public void setPrefixMapper(NamespacePrefixMapper prefixMapper) {
		this.prefixMapper = prefixMapper;
	}

	public <T> JAXBElement<? extends T> unmarshal(InputStream is, Class<? extends T> c) throws JAXBException {
		return unmarshal(new StreamSource(is), c);
	}
	
	public <T> JAXBElement<? extends T> unmarshal(Source s, Class<? extends T> c) throws JAXBException {
		if (c == null) {
			throw new JAXBException("No specific type provided.");
		}
		
		return (JAXBElement<? extends T>) JAXBContext.newInstance(this.packageName).createUnmarshaller().unmarshal(s, c);
	}
	
	public JAXBElement<?> unmarshal(InputStream is) throws JAXBException {
		return unmarshal(new StreamSource(is));
	}
	
	public JAXBElement<?> unmarshal(Source s) throws JAXBException {
		return (JAXBElement<?>) JAXBContext.newInstance(packageName).createUnmarshaller().unmarshal(s);
	}
	
	public ByteArrayOutputStream marshal(JAXBElement<?> je) throws JAXBException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		marshal(je, os);
		return os;
	}
	
	public void marshal(JAXBElement<?> je, OutputStream os) throws JAXBException {
		Marshaller m = JAXBContext.newInstance(packageName).createMarshaller();
		m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
		if (this.prefixMapper != null) {
			m.setProperty("com.sun.xml.bind.namespacePrefixMapper",        
				    this.prefixMapper);  
		}
		m.marshal(je, os);
	}
	
}
