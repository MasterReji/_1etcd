import com.coreos.jetcd.Client;
import com.coreos.jetcd.KV;
import com.coreos.jetcd.Watch;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.kv.GetResponse;
import com.coreos.jetcd.options.WatchOption;

import java.util.concurrent.ExecutionException;

public class EtcdClient implements Runnable{
    private int myId = 0;
    private int nodeCounter = 0;
    private int firstClient = 0;
    private String watchNodePath;
    private KV kvClient = null;

    public EtcdClient(String hostAdress, int id, int nodesPerClient){
        myId = id;
        nodeCounter = nodesPerClient;
        String server1 = hostAdress + ":2379";
        String server2 = hostAdress + ":22379";
        String server3 = hostAdress + ":32379";
        Client client = Client.builder().endpoints(server1,server2,server3).build();
        kvClient = client.getKVClient();
    }

    @Override
    public void run() {
        try {
            createNodes();
            if(myId==23) System.out.println("Sover nu");
            Thread.sleep(10000);


            deleteNodes();
            System.exit(0);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void createNodes() throws InterruptedException {
        for (int i = nodeCounter; i > 0; i--) {
            try {
                kvClient.put(ByteSequence.fromString(new String("/node" + myId+ "_"+i)),ByteSequence.fromString(new String(""))).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            //System.out.println("KLAR: "+"/node" + myId+ "_"+i);
        }
    }

    private void deleteNodes() throws InterruptedException {
        for(int i = nodeCounter; i > 0; i--) {
            kvClient.delete(ByteSequence.fromString(new String("/node" + myId + "_" + i)));
            //System.out.println("KLAR2");
        }
        kvClient.close();
    }



    /*
        // put the key-value
        kvClient.put(key, value).get();
        // get the CompletableFuture
        CompletableFuture<GetResponse> getFuture = kvClient.get(key);
        // get the value from CompletableFuture
        GetResponse response = getFuture.get();
        // delete the key
        DeleteResponse deleteRangeResponse = kvClient.delete(key).get();
    */




         /*   try {

            kvClient.put(key, value).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        CompletableFuture<GetResponse> getFuture = kvClient.get(key);
        try {
            GetResponse response = getFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }  /***/
}