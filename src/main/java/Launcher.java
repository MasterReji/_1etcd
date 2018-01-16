



public class Launcher {

    public static void main(String[] args) {

        final int TOT_NO_OF_NODES = 1000;
        int noOfClients = 1000;
        int firstClient = noOfClients-1;
        int nodesPerClient = TOT_NO_OF_NODES/noOfClients;

        String zkHost = "10.130.95.80:2181,10.130.95.80:2182,10.130.95.80:2183";
        //ClientNode cN = new ClientNode(zkHost);
        //cN.run();

        while(noOfClients-- != 0) {

           new Thread(new EtcdClient()).start();
        }
    }
}