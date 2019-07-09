package com.mainacad.service;

import com.mainacad.model.ConnectionInfo;
import com.mainacad.model.User;
import com.mainacad.util.Randomizer;

import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;

public class ConnectionInfoService {
  public static Queue<ConnectionInfo> generateConectionInfoQueue(int amount) {
    Queue<ConnectionInfo> connections = new PriorityQueue<>();
    for (int i = 0; i < amount; i++) {
      Integer sessionId = Randomizer.getRandomNumber(1_000_000, 9_999_999);
      String userIp = Randomizer.getRandomNumber(2, 255) + "." +
              Randomizer.getRandomNumber(2, 255) + "." +
              Randomizer.getRandomNumber(2, 255) + "." +
              Randomizer.getRandomNumber(2, 255);
      Long time = new Date().getTime() - Randomizer.getRandomNumber(0, 1000*60*60*24*3);
      User user = new User(Randomizer.getRandomNumber(1000, 99999), Randomizer.getRandomString(10), Randomizer.getRandomString(12));

      ConnectionInfo connectionInfo = new ConnectionInfo(user, sessionId, userIp, time);
      connections.add(connectionInfo);
    }

    return connections;
  }
}
