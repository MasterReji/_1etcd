import com.coreos.jetcd.Client;
import com.coreos.jetcd.KV;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.kv.DeleteResponse;
import com.coreos.jetcd.kv.GetResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EtcdClient implements Runnable{

    public EtcdClient(String hostAdress){
// create client

        Client client = Client.builder().endpoints("http://127.0.0.1:2379","http://127.0.0.1:22379","http://127.0.0.1:32379").build();
        KV kvClient = client.getKVClient();

        ByteSequence key = ByteSequence.fromString("test_key");
        ByteSequence value = ByteSequence.fromString("br?");

// put the key-value
        try {

            kvClient.put(key, value).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
// get the CompletableFuture
        CompletableFuture<GetResponse> getFuture = kvClient.get(key);
// get the value from CompletableFuture
        try {
            GetResponse response = getFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
/*// delete the key
        try {
            DeleteResponse deleteRangeResponse = kvClient.delete(key).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }/**/

    }

    @Override
    public void run() {

    }
}