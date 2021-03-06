package ro.pub.cs.systems.eim.practicaltest02.network;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import ro.pub.cs.systems.eim.practicaltest02.general.Constants;
import ro.pub.cs.systems.eim.practicaltest02.general.Utilities;

public class ClientThread extends Thread {
    private String address;
    private int port;
    private String informationType;
    private TextView bitcoinTextView;

    private Socket socket;

    public ClientThread(String address, int port, String informationType, TextView bitcoinTextView) {
        this.address = address;
        this.port = port;
        this.informationType = informationType;
        this.bitcoinTextView = bitcoinTextView;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(address, port);
            if (socket == null) {
                Log.e(Constants.TAG, "[CLIENT THREAD] Could not create socket!");
                return;
            }
            BufferedReader bufferedReader = Utilities.getReader(socket);
            PrintWriter printWriter = Utilities.getWriter(socket);
            if (bufferedReader == null || printWriter == null) {
                Log.e(Constants.TAG, "[CLIENT THREAD] Buffered Reader / Print Writer are null!");
                return;
            }
            printWriter.println(informationType);
            printWriter.flush();

            String bitcoinInformation;
            while ((bitcoinInformation = bufferedReader.readLine()) != null) {
                final String finalizedBitcoinInformation = bitcoinInformation;
                bitcoinTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        bitcoinTextView.setText(finalizedBitcoinInformation);
                    }
                });
                Log.e(Constants.TAG, bitcoinInformation);
            }
        } catch (IOException ioException) {
            Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
            if (Constants.DEBUG) {
                ioException.printStackTrace();
            }
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ioException) {
                    Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
                    if (Constants.DEBUG) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }
}
