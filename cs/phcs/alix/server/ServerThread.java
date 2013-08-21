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
import java.net.ServerSocket;
import java.net.Socket;
/**
 * ServerThread, it start connectionHandler for the server... or incoming data
 * @author Zola Madolo
 *
 */
public class ServerThread extends Thread implements Runnable{
	private final int _port;
	public ServerSocket _socketServer;
	public int count = 0;
	/**
	 * Used when setting up ServerThread
	 * @param port
	 */
	public ServerThread(int port)
	{
		this._port = port;
	}
	public void run()
	{
		try
		{
			while(!isInterrupted())
			{
				Socket socket = _socketServer.accept();
				ServerConnectionHandler handler = new ServerConnectionHandler(socket, count);
				Thread thread = new Thread(handler);
				thread.start();
				count++;
			}
		}catch(IOException ex)
		{
			System.out.println("Unexpected error while listening to incoming connection");
			System.out.println(ex.getMessage());
		}
	}
	/**
	 * Start Server ...
	 */
	public void start()
	{
		try
		{
			_socketServer = new ServerSocket(_port);
			super.start();
		}catch(Exception ex)
		{
			System.out.println("Unexpected error while starting a server");
			System.out.println(ex.getMessage());
		}
	}
	/**
	 * Close Server...
	 */
	public void terminateServer()
	{
		try
		{
			if(_socketServer!=null)
			{
				_socketServer.close();
				System.out.println("Android Connection Closed...");
			}
			this.interrupt();
		}catch(Exception ex)
		{
			//ignore
		}
	}
}
