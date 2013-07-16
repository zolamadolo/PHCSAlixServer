package phcs.alix.test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONObject;


import phcs.lib.VitalParameter;

public class TestClass {
	public static void main(String[]args)
	{
		try {
			@SuppressWarnings("resource")
			Socket socket = new Socket("127.0.0.1", 5000);
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			VitalParameter parameter = VitalParameter.getInstance();
			parameter.setName("Temperature");
			parameter.addValue("High", 90.0);
			parameter.addValue("Low", 5.0);
			JSONObject object = parameter.convertToJsonObject();
			out.println(object.toString()); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
}
