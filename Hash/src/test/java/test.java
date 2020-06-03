import com.chw.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class test {
	 public static void main(String[] args) {
		 CacheManage CM=new CacheManage();
		 //CM.initVitualNode(2, 5);
		 CM.initCacheNode(5);
		 CM.putData("node_2");
		 CM.putData("node_1");
		 CM.putData("node_5");
		 CM.putData("node_7");
		 
		 CM.addCacheNode("node_5", "192.168.1.105");
		 
		 log.info("**********Add Node********");
		 
		 CM.putData("node_2");
		 CM.putData("node_1");
		 CM.putData("node_5");
		 CM.putData("node_7");
	  }
}
