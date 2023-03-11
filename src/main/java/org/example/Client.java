package org.example;

import org.eclipse.paho.client.mqttv3.*;

import javax.swing.*;

/**
 * @author GEGENPHASE
 * @date 10.03.2023 17:31
 */
public class Client
{
    // Feldvariablen
    private final MqttClient _client;
    private String _name;

    /**
     * Konstruktor der Klasse {@link Client}.
     *
     * @throws MqttException Die Exception, falls etwas passiert.
     */
    public Client(String ip, String clientId) throws MqttException
    {
        this(ip, clientId, "Client");
    }

    public Client(String ip, String clientId, String name) throws MqttException
    {
        _name = "[" + name + "] ";
        _client = new MqttClient(ip, clientId);
        _client.connect();
        System.out.println(_name + "Erfolgreich verbunden.");
        setupCallback();
    }

    /**
     * Abonniere ein Thema.
     *
     * @param topic Das Thema.
     */
    public void subscribe(String topic)
    {
        try
        {
            _client.subscribe(topic);
        }
        catch (MqttException e)
        {
            System.out.println("> " + _name + "Beim Abonnieren ist ein Fehler aufgetreten.");
        }
    }

    private void setupCallback()
    {
        _client.setCallback(new MqttCallback()
        {
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception
            {
                System.out.println(_name + message.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token)
            {
                // TODO Auto-generated method stub
            }

            @Override
            public void connectionLost(Throwable cause)
            {
                // TODO Auto-generated method stub
            }
        });

    }

    public void publish(String topic, String message)
    {
        try
        {
            _client.publish(topic, new MqttMessage(message.getBytes()));
        }
        catch (MqttException e)
        {
            System.out.println("> " + _name + "Beim Senden der Nachricht '" + message + "' unter dem Thema '" + topic + "' ist ein Fehler aufgetreten!");
        }
    }

}