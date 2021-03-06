package TCP;

import TCP.handled_interfaces.InterruptableByConnection;
import TCP.threaded_classes.ConnectionsListProcessor;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * Сервер, основанный на сокетах. Автоматически принимает соединения. Есть возможность подключения различных обработчиков.
 *
 * @author Малякин Кирилл, гр. 15ИТ20.
 */
public class MultiClientHandledServer extends Thread implements Closeable {
    private ConnectionsListProcessor connectionsListProcessor;
    private ServerSocket serverSocket;

    public MultiClientHandledServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        connectionsListProcessor = new ConnectionsListProcessor(serverSocket);
        start();
    }

    public MultiClientHandledServer(int port, InterruptableByConnection connectionHandler) throws IOException {
        this(port);
        connectionsListProcessor.attachConnectionHandler(connectionHandler);
    }

    public ConnectionsListProcessor getConnectionsListProcessor() {
        return connectionsListProcessor;
    }

    public void close() throws IOException {
        connectionsListProcessor.close();
        serverSocket.close();
    }
}
