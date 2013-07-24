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
package phcs.alix.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONObject;

import phcs.lib.ConnectionHandler;
import phcs.lib.VitalParameter;
/**
 * Serves as a Client Connection handler between Client and Remote Server
 * @author Zola Madolo
 * 
 */
public class AlixClientConnectionHandler extends ConnectionHandler {

	private VitalParameter _data;
	/**
	 * Used when creating ClientConnectioHandler
	 * @param socket
	 */
	public AlixClientConnectionHandler(Socket socket) {
		super(socket);
	}
	/**
	 * Set the data every time a new reading is received ... on the local server.
	 * this data will be used later on when the handler method is called
	 * @param data
	 */
	public void setData(VitalParameter data)
	{
		this._data = data;
	}
	@Override
	protected void handle(Socket socket) throws IOException {
		if(this._data!=null)
		{
			JSONObject object = this._data.convertToJsonObject();
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			out.println(object.toString());
		}
	}

	@Override
	protected void displayConnectionInfo(Socket socket) {
		System.out.println("Client Connected Information");
		System.out.println("Just connected on "+ socket.getPort()+" Host: "+socket.getInetAddress().getHostAddress());
	}
}
