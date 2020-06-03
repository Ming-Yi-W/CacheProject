package com.chw;
import lombok.Data;
@Data
public class CacheNode {

    private String cacheName;
    private String cacheIP;
    private Long hashValue;
    String getCacheName() {
    	return cacheName;
    }
    String getCacheIP(){
    	return cacheIP;
    }
    void setCacheName(String Name) {
    	cacheName=Name;
    }
    void setCacheIP(String IP) {
    	cacheIP=IP;
    }
    void setHashValue(Long value){
    	hashValue=value;
    }
}