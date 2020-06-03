package com.chw;
import lombok.extern.slf4j.Slf4j;
import lombok.Data;
import java.util.*;

@Data
@Slf4j
public class CacheManage {
    private List<CacheNode> cacheNodeList;
    private long MAX_CIRCLE = (1L << 32) - 1;
    private Long hash(String nodeName) {
        return MAX_CIRCLE & nodeName.hashCode();
    }

    /**
     * 获取节点所在的下标
     */
    private int getNodeIndex(Long hash) {
        int index = cacheNodeList.size();
        for (int i = 0; i < cacheNodeList.size(); i++) {
            if (hash <= hash(cacheNodeList.get(i).getCacheName())) {
                index = i;
                break;
            }
        }
        return index;
    }
    private void printList() {
        for (CacheNode cacheNode : cacheNodeList) {
            log.info("cachenode: {}", cacheNode);
        }
    }
    /**
     * 初始化带有虚拟节点的节点链表
     */
    public void initVitualNode(int size, int virtualSize) {
        cacheNodeList = new ArrayList<>();
        log.info("mx: {}", MAX_CIRCLE);
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < virtualSize; j++) {
                CacheNode cacheNode = new CacheNode();
                cacheNode.setCacheName("" + i + "_" + j + i + j + "_node_" + i + "_" + j);
                cacheNode.setCacheIP("192.168.1.10" + i);
                Long hashValue = hash(cacheNode.getCacheName());
                cacheNode.setHashValue(hashValue);
                int index = getNodeIndex(hashValue);
                if (index == cacheNodeList.size()) {
                    cacheNodeList.add(cacheNode);
                } else {
                    cacheNodeList.add(index, cacheNode);
                }
            }
        }
        printList();
    }

    /**
     * 初始化节点列表
     */
    public void initCacheNode(int size) {
        cacheNodeList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            CacheNode cacheNode = new CacheNode();
            cacheNode.setCacheName("node_" + 2*i);
            cacheNode.setCacheIP("192.168.1.10" + 2*i);
            Long hashValue = hash(cacheNode.getCacheName());
            cacheNode.setHashValue(hashValue);
            int index = getNodeIndex(hashValue);
            if (index == 0) {
                cacheNodeList.add(cacheNode);
            } else {
                cacheNodeList.add(index, cacheNode);
            }

            //printList();
        }

        printList();
    }
    public void addCacheNode(String Name,String Ip) {

        CacheNode newCacheNode=new CacheNode();
        newCacheNode.setCacheIP(Ip);
        newCacheNode.setCacheName(Name);
        Long newHashValue = hash(newCacheNode.getCacheName());
        newCacheNode.setHashValue(newHashValue);
        int index = getNodeIndex(newHashValue);
        cacheNodeList.add(index, newCacheNode);
        printList();
    }
    /**
     * 存数据
     * data:用户id
     */
    public String putData(String data) {
        Long hashValue = hash(data);
        int index = getNodeIndex(hashValue);
        if(index == cacheNodeList.size()){
            index = 0;
        }
        log.info("data:{}[{}] put into ====>{}", data, hashValue, cacheNodeList.get(index));
        return cacheNodeList.get(index).getCacheIP();
    }
    
    /**
     * 存数据
     * hashvalue:用户Hash值
     */
    public String putHash(Long hashvalue) {
        int index = getNodeIndex(hashvalue);
        if(index == cacheNodeList.size()){
            index = 0;
        }
        log.info("data:{} put into ====>{}", hashvalue, cacheNodeList.get(index));
        return cacheNodeList.get(index).getCacheIP();
    }
}
