/*
 * Copyright (c) 2017 Josh
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
    private String server = Bukkit.getServer().getIp();
    private int port = Bukkit.getServer().getPort();

    public static ServerUtils getInstance() {
        return instance;
    }

    /**
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
            if (packetID == -1)
                Logger.getLogger().log(LogType.SEVERE, "End of stream");
            if (packetID != 0xFF)
                Logger.getLogger().log(LogType.SEVERE, "Invalid ID!" + packetID);
            if (length == -1)
                Logger.getLogger().log(LogType.SEVERE, "End of stream");
            if (length == 0)
                Logger.getLogger().log(LogType.SEVERE, "Invalid length");
            char[] chars = new char[length];
            if (inputStreamReader.read(chars, 0, length) != length)
                Logger.getLogger().log(LogType.SEVERE, "End of stream");
            String string = new String(chars);
            String[] data = string.split("\0");
            if (type == ConnectionType.SV_ONLINE)
                return Integer.parseInt(data[4]) + "/" + Integer.parseInt(data[5]);
            if (type == ConnectionType.SV_MOTD)
                return data[3];
            if (type == ConnectionType.SV_VERSION)
                return data[2];
        } catch (Exception e) {
            return null;
        }
        return null;
    }

}
