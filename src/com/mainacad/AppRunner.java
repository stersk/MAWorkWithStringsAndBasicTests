package com.mainacad;

import com.mainacad.model.ConnectionInfo;
import com.mainacad.service.ConnectionInfoService;
import com.mainacad.service.FileManagerService;

import java.util.Queue;
import java.util.logging.Logger;

public class AppRunner {
  private static Logger logger = Logger.getLogger(AppRunner.class.getName());

  public static void main(String[] args) {
    Queue<ConnectionInfo> connections = ConnectionInfoService.generateConectionInfoQueue(5);

    logger.info(String.format("My system generated %d connections. %s", connections.size(), "It's cool."));
    logger.info(ConnectionInfoService.getConnectionAsText(connections));

/*
    byte[] bytes = FileManagerService.readBytes("cat.jpg");
    FileManagerService.writeBytesToFileNio(bytes, "cat2.jpg");
*/

    ConnectionInfo connectionInfo = ConnectionInfoService.generateConectionInfoQueue(1).peek();
    FileManagerService.writeObject(connectionInfo, "object.fci");
    logger.info(FileManagerService.readObject("object.fci").toString());


    /*ConnectionInfo connectionInfo = ConnectionInfoService.generateConectionInfoQueue(1).peek();
    FileManagerService.writeText("connections.txt", connectionInfo.toString(), true);
    logger.info("\n" + FileManagerService.readText("connections.txt"));*/
  }
}

