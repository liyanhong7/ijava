package collection.map;

import java.util.HashMap;

public class HashMapSource<K,V> extends HashMap<K,V> {
    /*
     * 1. 看源码之前需要了解的一些内容
     *
     * Node<K, V>[] tables       哈希表结构中数组的名字
     * DEFAULT_INITIAL_CAPACITY 数组默认长度16
     * DEFAULT_LOAD_FACTOR      默认加载因子0.75
     *
     * HashMap里面每一个对象包含以下内容
     * 1.1 链表中的键值对对象
     *     包含：
     *          int hash;       // 键的哈希值
     *          final K key;    // 键
     *          V value;        // 值
     *          Node<K,V> next; // 下一个节点的地址值
     * 1.2 红黑树中的键值对对象
     *      包含：
     *          int hash;       // 键的哈希值
     *          final K key;    // 键
     *          V value;        // 值
     *          TreeNode<K,V> parent;   // 父节点的地址值
     *          TreeNode<K,V> left;     // 左子节点的地址值
     *          TreeNode<K,V> right;    // 右子节点的地址值
     *          boolean red;    // 节点的颜色
     */
}
