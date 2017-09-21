package gov.nist.forecast.fhir.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyReader;

import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.nist.forecast.fhir.ForecastApplication;

//This class is required to get XML serialization done the EMF way.
//All EMF objects are EObjects.
@Consumes(MediaType.APPLICATION_XML)
public class EMF2XMLReader<T extends EObject> extends AbstractEMFReader<T> implements XMLReaderWriter {

	private static Logger log = LoggerFactory
			.getLogger(ForecastApplication.class);

	public EMF2XMLReader() {
		super(_URI);
		log.trace("EMF2XMLReader==>" + _URI);
	}	
}