public class Launcher {

    public static void main(String[] args) {

        final int TOT_NO_OF_NODES = 100;
        int noOfClients = 1;
        int firstClient = noOfClients-1;
        int nodesPerClient = TOT_NO_OF_NODES/noOfClients;

        String host = "http://10.130.92.207";

        while(noOfClients-- != 0) {
           new Thread(new EtcdClient(host,noOfClients,TOT_NO_OF_NODES)).start();

          /*  try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(noOfClients == 499) System.out.println("halvvägs");*/
        }
    }
}
