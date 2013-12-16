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
package org.n52.aixm.roundtrip.wps;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBException;

import org.n52.aixm.roundtrip.Roundtrip;
import org.n52.wps.algorithm.annotation.Algorithm;
import org.n52.wps.algorithm.annotation.ComplexDataInput;
import org.n52.wps.algorithm.annotation.ComplexDataOutput;
import org.n52.wps.algorithm.annotation.Execute;
import org.n52.wps.io.data.GenericFileData;
import org.n52.wps.io.data.binding.complex.GenericFileDataBinding;
import org.n52.wps.server.AbstractAnnotatedAlgorithm;

/**
 * WPS wrapper for the {@link Roundtrip} class.
 * 
 * @author matthes rieke
 *
 */
@Algorithm(
		version = "0.1",
		abstrakt = "AIXM 5.1 Roundtrip using JAXB Bindings",
		title = "AIXM 5.1 Roundtrip",
		statusSupported = false,
		storeSupported = false)
public class AIXMRoundtripAlgorithm extends AbstractAnnotatedAlgorithm {

	private GenericFileData output;
	
	@ComplexDataInput(binding = GenericFileDataBinding.class, identifier = "input")
	public GenericFileData input;
	
	@ComplexDataOutput(binding = GenericFileDataBinding.class, identifier = "output")
	public GenericFileData getOutput() {
		return this.output;
	}
	
	@Execute
	public void execute() throws JAXBException {
		Roundtrip r = new Roundtrip();
		
		ByteArrayOutputStream os = r.execute(this.input.getDataStream());
		this.output = new GenericFileData(new ByteArrayInputStream(os.toByteArray()), "application/xml");
	}

}
