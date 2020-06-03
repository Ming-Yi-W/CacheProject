## 一致性hash算法原理及实现


### 原理
&emsp;&emsp;对存储节点的哈希值进行计算，其将存储空间抽象为一个环，将存储节点配置到环上。环上所有的节点都有一个值。其次，对数据进行哈希计算，按顺时针方向将其映射到离其最近的节点上去。当有节点出现故障离线时，受影响的仅仅为环上故障节点开始逆时针方向至下一个节点之间区间的数据对象。

### 算法
&emsp;&emsp;通过List对CacheNode类进行操作。


### 说明
`void initCacheNode(int size)`:初始化节点列表。  
`void initVitualNode(int size, int virtualSize)`：当服务器节点比较少的时候会出现一致性hash倾斜的问题，可以引用虚拟节点。该函数初始化带有虚拟节点的节点链表。
`String putHash(Long hashvalue)`:返回服务器IP。  
`void addCacheNode(String Name,String Ip)`:添加存储服务器。

<img src="https://github.com/Ming-Yi-W/CacheProject/tree/master/Hash/log.png" style="width: 60%; clear: both;display: block;margin: auto;"/>
&emsp;&emsp;如上图所示，首先初始化了5个服务器，然后根据用户的ID来选取接受服务的服务器；之后添加服务器`node_5(192.168.1.105)`,还是上述用户，所产生的结果发生变化。