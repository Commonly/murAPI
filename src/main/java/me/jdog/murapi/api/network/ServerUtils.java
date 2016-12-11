package me.jdog.murapi.api.network;

import me.jdog.murapi.api.logger.LogType;
import me.jdog.murapi.api.logger.Logger;
import me.jdog.murapi.api.network.type.ConnectionType;
import org.bukkit.Bukkit;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by Muricans on 11/23/16.
 */
public class ServerUtils {
    private static ServerUtils instance = new ServerUtils();
    private ServerUtils() {
    }
    public static ServerUtils getInstance() {
        return instance;
    }

    private String server = Bukkit.getServer().getIp();
    private int port = Bukkit.getServer().getPort();

    /**
     *
     * @param type The connection you want to get.
     * @return Connection type.
     */
    public String getDataType(ConnectionType type) {
        try {
            Socket socket = new Socket();
            OutputStream outputStream;
            DataOutputStream dataOutputStream;
            InputStream inputStream;
            InputStreamReader inputStreamReader;
            socket.setSoTimeout(2500);
            socket.connect(new InetSocketAddress(server, port), 2500);
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-16BE"));
            dataOutputStream.write(new byte[]{(byte) (0xFE), (byte) 0x01});
            int packetID = inputStream.read();
            int length = inputStreamReader.read();
            if(packetID == -1)
                Logger.getInstance().log(LogType.SEVERE, "End of stream");
            if (packetID != 0xFF)
                Logger.getInstance().log(LogType.SEVERE, "Invalid ID!" + packetID);
            if(length == -1)
                Logger.getInstance().log(LogType.SEVERE, "End of stream");
            if(length == 0)
                Logger.getInstance().log(LogType.SEVERE, "Invalid length");
            char[] chars = new char[length];
            if(inputStreamReader.read(chars, 0, length) != length)
                Logger.getInstance().log(LogType.SEVERE, "End of stream");
            String string = new String(chars);
            String[] data = string.split("\0");
            if(type == ConnectionType.SV_ONLINE)
                return Integer.parseInt(data[4]) + "/" + Integer.parseInt(data[5]);
            if(type == ConnectionType.SV_MOTD)
                return data[3];
            if(type == ConnectionType.SV_VERSION)
                return data[2];
        } catch (Exception e) {
            return null;
        }
        return null;
    }

}
