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
package phcs.lib;

import java.io.IOException;
import java.net.Socket;
/**
 * Handling network socket request... 
 * @author Zola Madolo
 *
 */
public abstract class ConnectionHandler implements Runnable {
 
	private Socket _socket;
	public ConnectionHandler(Socket socket)
	{
		this._socket = socket;
		displayConnectionInfo(_socket);
	}
	/**
	 * Handle incoming request, respond to request...
	 * @param socket
	 * @throws IOException
	 *
	 */
	protected abstract void handle(Socket socket) throws IOException;
	protected abstract void displayConnectionInfo(Socket socket);
	public void run()
	{
		try
		{
			handle(_socket);
		}catch(Exception ex)
		{
			System.out.println("Something went wrong while trying to handle... a socket");
		}
		finally
		{
			try {
				// Should never be null, but let's be on the safe side anyway
				if ( _socket != null ) {
					// Some options, faster & safer?
					_socket.setSoLinger(false, 0);
					_socket.shutdownInput();
					_socket.shutdownOutput();
					_socket.close();
				}
			} catch (IOException e) {
				// Ignore... OK.
			}
		}
	}
}
