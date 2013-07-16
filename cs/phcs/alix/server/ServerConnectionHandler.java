/**
 *  Copyright 2013 Zola Madolo (http://people.cs.uct.ac.za/~zmadolo/)
 *	This work is licenced under the Creative Commons Attribution 2.5 South Africa License. 
 *	To view a copy of this licence, visit http://creativecommons.org/licenses/by/2.5/za/ or 
 *	send a letter to Creative Commons, 171 Second Street, 
 *	Suite 300, San Francisco, California 94105, USA.
 *
 *  Visit http://www.rcips.uct.ac.za/ip/copyright/bla/
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/*******************************************************************************
 * Copyright (c) 2013 Zola Madolo (http://people.cs.uct.ac.za/~zmadolo/).
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package phcs.alix.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import phcs.lib.ConnectionHandler;
import phcs.lib.VitalParameter;
/**
 * Handle Server Connection... extends ConnectionHandler class
 * @author Zola Madolo
 * respond,and send data to remote server upon receiving it.
 */
public class ServerConnectionHandler extends ConnectionHandler {
	private int socket_id;
	/**
	 * Overload Constructor to include Socket_id
	 * @param socket
	 * @param id
	 */
	public ServerConnectionHandler(Socket socket,int id) {
		super(socket);
		this.socket_id = id;
	}
	/**
	 * Display Connection information for debugging purposes only.
	 * @param socket
	 */
	@Override
	protected void displayConnectionInfo(Socket socket)
	{
		System.out.println("Client "+this.socket_id +" Connection Information");
		System.out.println("Just connected on "+ socket.getPort()+" Host: "+socket.getInetAddress().getHostAddress());
	}
	/**
	 * Override handler method... from the super class
	 */
	@Override
	protected void handle(Socket socket) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String data_in = in.readLine();
		if(data_in!=null)
		{
			try {
				JSONObject object = new JSONObject(data_in);
				VitalParameter parameter = VitalParameter.getInstance();
				parameter = parameter.convertToVitilParameter(object);
				System.out.println(parameter.getName());
				System.out.println(parameter.getValues().get("Low"));
				System.out.println(parameter.getValues().get("High"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

}
