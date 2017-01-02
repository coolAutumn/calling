package net.leeautumn;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Unit test for simple ServiceServer.
 */
public class ServiceServerTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ServiceServerTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ServiceServerTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
//        try {
//            SocketChannel socketChannel = SocketChannel.open();
//
//            socketChannel.connect(new InetSocketAddress(3341));
//
//            if(socketChannel.isConnected()){
//                ByteBuffer byteBuffer = ByteBuffer.allocate(100);
//
//                byte[] bytes = new byte[]{(byte)0xCA,0x01,0x01};
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
