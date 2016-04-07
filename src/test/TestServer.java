import com.wgh.core.Global.BeanFactory;
import com.wgh.core.channel.tcp.TCPServer;
import org.junit.Test;

/**
 *
 */
public class TestServer {
    public static void main(String[] args){
        TCPServer s = (TCPServer) BeanFactory.getInstance().getIo("TCPServer");
        s.run();
    }
}
