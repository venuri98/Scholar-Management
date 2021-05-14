package com;

import model.ScholarManagement;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Scholar")

public class ScholarManagementServices {

	ScholarManagement scholar = new ScholarManagement();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readResearch() {
		return scholar.readscholordata();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertscholordata(@FormParam("name") String name,
			@FormParam("email") String email,
			@FormParam("approvedProjectTitle") String approvedProjectTitle,
			@FormParam("approvedDate") String approvedDate) {
		String output = scholar.insertscholordata(name, email, approvedProjectTitle, approvedDate);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatescholordata(String ScholarData) {
		// Convert the input string to a JSON object
		JsonObject ScholarObject = new JsonParser().parse(ScholarData).getAsJsonObject();
		// Read the values from the JSON object

		String sid = ScholarObject.get("sid").getAsString();
		String name = ScholarObject.get("name").getAsString();
		String email = ScholarObject.get("email").getAsString();
		String approvedProjectTitle = ScholarObject.get("approvedProjectTitle").getAsString();
		String approvedDate = ScholarObject.get("approvedDate").getAsString();
		String output = scholar.updatescholordata(sid, name, email, approvedProjectTitle, approvedDate);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearch(String ScholarData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(ScholarData, "", Parser.xmlParser());

		String sid = doc.select("sid").text();
		String output = scholar.deleteResearch(sid);
		return output;
	}


}
