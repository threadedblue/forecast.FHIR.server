package gov.nist.forecast.fhir.resources;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import java.text.ParseException;

import org.hl7.fhir.FhirFactory;
import org.hl7.fhir.Parameters;
import org.hl7.fhir.impl.ParametersImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import fhir.util.FHIRUtil;
import fhir.util.Serialize;
import gov.nist.forecast.fhir.service.ImmunizationRecommendationServiceTest;

public class IndexResourceTest {

	private static Logger log = LoggerFactory.getLogger(IndexResourceTest.class);

	// @Test
	public void testJSON() {
		given().accept(ContentType.JSON).when().get("/forecast").then().body(containsString("healthy"));
	}

	// @Test
	public void testImplementationGuideJSON() {
		given().accept(ContentType.JSON).when().get("/forecast/ImplementationGuide").then()
				.body(containsString("ImplementationGuide"));
		given().accept(ContentType.JSON).when().get("/forecast/ImplementationGuide").then().log().body();
	}

	// @Test
	public void testImplementationGuideXML() {
		given().accept(ContentType.XML).when().get("/forecast/ImplementationGuide").then()
				.body(containsString("ImplementationGuide"));
		given().accept(ContentType.XML).when().get("/forecast/ImplementationGuide").then().log().body();
	}

	// @Test
	public void testConformanceJSON() {
		given().accept(ContentType.JSON).when().get("/forecast/Conformance").then().body(containsString("Conformance"));
		given().accept(ContentType.JSON).when().get("/forecast/Conformance").then().log().body();
	}

	// @Test
	public void testConformanceXML() {
		given().accept(ContentType.XML).when().get("/forecast/Conformance").then().body(containsString("Conformance"));
		given().accept(ContentType.XML).when().get("/forecast/Conformance").then().log().body();
	}

	// @Test
	public void testForecastImmunizationRecomendationTCH_JSON() {
		runQuery("TCH", ContentType.JSON);
	}

	// @Test
	public void testForecastImmunizationRecomendationTCH_XML() {
		runQuery("TCH", ContentType.XML);
	}

	// @Test
	public void testForecastImmunizationRecomendationSWP_JSON() {
		runQuery("SWP", ContentType.JSON);
	}

	// @Test
	public void testForecastImmunizationRecomendationSWP_XML() {
		runQuery("SWP", ContentType.XML);
	}

	// @Test
	public void testForecastImmunizationRecomendationSTC_JSON() {
		runQuery("STC", ContentType.JSON);
	}

	// @Test
	public void testForecastImmunizationRecomendationSTC_XML() {
		runQuery("STC", ContentType.XML);
	}

	// @Test
	public void testForecastImmunizationRecomendationICE_JSON() {
		runQuery("ICE", ContentType.JSON);
	}

	// @Test
	public void testForecastImmunizationRecomendationICE_XML() {
		runQuery("ICE", ContentType.XML);
	}

	@SuppressWarnings("static-access")
	void runQuery(String serviceType, ContentType type) {
		given().accept(type.XML).param("serviceType", serviceType).param("evalDate", "2011-03-11")
				.param("birthDate", "2012-01-01").param("gender", "M").param("strings", "08")
				.param("strings", "2011-01-02").param("strings", "42").param("strings", "2011-01-02")
				.param("strings", "53").param("strings", "2011-01-03").when().
				// get("/forecast/ForecastImmunizationRecomendation").then().body(containsString("OK"));
				// get("/forecast/ForecastImmunizationRecomendation").then().log().body();
				get("/forecast/ForecastImmunizationRecomendation").then().extract().response();

	}

	// @Test
	public void testGetProfileXML() {
		Response response = given().accept(ContentType.XML).when().get("/forecast/Profile/ForecastPatient").then()
				.extract().response();
		String s = response.asString();
	}

	// @Test
	public void testGetProfileJSON() {
		Response response = given().accept(ContentType.JSON).when().get("/forecast/Profile/ForecastPatient").then()
				.extract().response();
		String s = response.asString();
	}

	@Test
	public void testParametersXML() {
		try {
			Parameters parameters = ImmunizationRecommendationServiceTest.createParameters("TCH");
			Serialize save = new Serialize();
			String s = save.it(parameters, "xxx.xml");
			given().contentType("application/xml").accept("application/xml").body(s).when()
					.post("/forecast/ImmunizationRecommendations").then().statusCode(200);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testParametersJSON() {
		try {
			Parameters parameters = ImmunizationRecommendationServiceTest.createParameters("TCH");
			Serialize save = new Serialize();
			String s = save.it(parameters, "xxx.json");
			given().contentType("application/json").accept("application/json").body(s).when()
					.post("/forecast/ImmunizationRecommendations").then().statusCode(200);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public ParametersImpl createParameters() throws ParseException {
		ParametersImpl parameters = (ParametersImpl)FhirFactory.eINSTANCE.createParameters();
		parameters.setId(FHIRUtil.createId());
		return parameters;
	}

}
