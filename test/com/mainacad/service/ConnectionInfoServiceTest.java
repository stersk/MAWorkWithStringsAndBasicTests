package com.mainacad.service;

import com.mainacad.model.ConnectionInfo;
import com.mainacad.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionInfoServiceTest {

  @Test
  void testGenerateConectionInfoQueue() {
    Queue<ConnectionInfo> testQueqe = ConnectionInfoService.generateConectionInfoQueue(5);

    assertNotNull(testQueqe);
    assertEquals(5, testQueqe.size());

    ConnectionInfo[] testElements = testQueqe.toArray(new ConnectionInfo[0]);
    for (int i = 0; i < testElements.length; i++) {
      String error = "Error: null object generated at index " + i;
      assertNotNull(testElements[i], error);
    }
  }

  @Test
  void testGetConnectionAsText() {
    String expectedString = "1562532168172 9823155 135.148.182.163 boiXULXRhc vCHOmfdhgLTc 7810\n";
    expectedString += "1562616768620 2693229 200.145.138.201 hTGcrOpAPQ ZRykarraxbWx 55503\n";

    User user1 = new User(7810, "boiXULXRhc", "vCHOmfdhgLTc");
    ConnectionInfo connection1 = new ConnectionInfo(user1, 9823155, "135.148.182.163", 1562532168172L);

    User user2 = new User(55503, "hTGcrOpAPQ", "ZRykarraxbWx");
    ConnectionInfo connection2 = new ConnectionInfo(user2, 2693229, "200.145.138.201", 1562616768620L);

    List<ConnectionInfo> testList = new ArrayList<>();
    testList.add(connection1);
    testList.add(connection2);

    assertEquals(expectedString, ConnectionInfoService.getConnectionAsText(testList));
  }

  @Test
  void testGetConnectionsFromText() {
    String testString = "1562532168172 9823155 135.148.182.163 boiXULXRhc vCHOmfdhgLTc 7810\n";
    testString += "1562616768620 2693229 200.145.138.201 hTGcrOpAPQ ZRykarraxbWx 55503\n";

    User user1 = new User(7810, "boiXULXRhc", "vCHOmfdhgLTc");
    ConnectionInfo expectedConnection1 = new ConnectionInfo(user1, 9823155, "135.148.182.163", 1562532168172L);

    User user2 = new User(55503, "hTGcrOpAPQ", "ZRykarraxbWx");
    ConnectionInfo expectedConnection2 = new ConnectionInfo(user2, 2693229, "200.145.138.201", 1562616768620L);

    Queue<ConnectionInfo> resultQueue = ConnectionInfoService.getConnectionsFromText(testString);

    assertNotNull(resultQueue);
    assertTrue((resultQueue.size() == 2), "Size of result Queque must be 2, actual size is " + resultQueue.size());

    assertEquals(expectedConnection1, resultQueue.poll());
    assertEquals(expectedConnection2, resultQueue.poll());
  }

  @Test
  void testFilterConnectionsByTime() {
    User user1 = new User(7810, "boiXULXRhc", "vCHOmfdhgLTc");
    ConnectionInfo testConnection1 = new ConnectionInfo(user1, 9823155, "135.148.182.163", 1562532168172L);

    User user2 = new User(55503, "hTGcrOpAPQ", "ZRykarraxbWx");
    ConnectionInfo testConnection2 = new ConnectionInfo(user2, 2693229, "200.145.138.201", 1562616768620L);

    User user3 = new User(12441, "eUUReDqEaZ", "KVvQLdVzItAO");
    ConnectionInfo testConnection3 = new ConnectionInfo(user3, 5102110, "200.145.138.201", 1562702751014L);

    User user4 = new User(14331, "jjfhsyyGnK", "KTtkennxoXUi");
    ConnectionInfo testConnection4 = new ConnectionInfo(user4, 4972283, "230.218.233.33", 1562533228619L);

    List<ConnectionInfo> testList = new ArrayList<>();
    testList.add(testConnection3);
    testList.add(testConnection2);
    testList.add(testConnection4);
    testList.add(testConnection1);

    Queue<ConnectionInfo> resultQueue = ConnectionInfoService.filterConnectionsByTime(testList, 1562532228619L, 1562636768620L);

    assertNotNull(resultQueue);
    assertTrue((resultQueue.size() == 2), "Size of result Queque must be 2, actual size is " + resultQueue.size());

    assertEquals(testConnection4, resultQueue.poll());
    assertEquals(testConnection2, resultQueue.poll());
  }
}