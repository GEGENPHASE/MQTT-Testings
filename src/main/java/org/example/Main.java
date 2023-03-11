package org.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author GEGENPHASE
 * @date ${DAY}.${MONTH}.${YEAR} ${HOUR}:${MINUTE}
 */
public class Main
{
    public static void main(String[] args) throws MqttException
    {
        Client test = new Client("tcp://192.168.178.30:1883", "testc", "ESP32");

        test.subscribe("esp32/temp1");
    }
}