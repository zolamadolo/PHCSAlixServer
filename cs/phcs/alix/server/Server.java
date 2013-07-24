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

import java.io.IOException;

import phcs.alix.client.AlixClient;

/**
 * Server, it is the foundation of ServerThread...
 * @author Zola Madolo
 *
 */
public class Server {
	private final int _port;
	private boolean _started = false;
	private ServerThread _serverThread;
	
	private final String REMOTE_HOST = "127.0.0.1";
	private final int REMOTE_PORT = 6000;
	
	private static AlixClient serverClient = null;
	
	public Server(int port)
	{
		this._port = port;
	}
	/**
	 * Get ServerClient to send data to remote server
	 * @return Client
	 */
	public static AlixClient getServerClient()
	{
		return serverClient;
	}
	/**
	 * Start Server... instantiate ServerThread
	 */
	public void start() {
		if(!_started)
		{
			_serverThread = new ServerThread(_port);
			_serverThread.start();
			_started = true;
			System.out.println("Server Started on port: "+_port);
			//Now start Client to connect to remote server
			
//			try {
//				serverClient = new AlixClient(REMOTE_HOST,REMOTE_PORT);
//				//serverClient.connect();
//			} catch (IOException e) {
//				System.out.print("Something went wrong while starting Client ");
//				System.out.println("to connect to remote server");
//				e.printStackTrace();
//			} catch (Exception e) {
//				System.out.print("Something went wrong while starting Client ");
//				System.out.println("to connect to remote server");
//				e.printStackTrace();
//			}
			
		}else 
		{
			System.out.println("Server already started ");
		}
	}
	
	/**
	 * Stop server...
	 */
	public void stop(){
		if(_started)
		{
			_serverThread.terminateServer();
			try
			{
				Thread.sleep(500);
			}catch(InterruptedException ex)
			{
				System.out.println("Thread Interrupted");
			}
		}
	}
}
