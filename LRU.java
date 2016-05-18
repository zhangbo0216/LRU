public class LRUCache {
    class DLinkedNode{
        int val;
        int key;
        DLinkedNode pre;
        DLinkedNode next;
    }
    
    int capacity;
    DLinkedNode head,tail;
    public void addNode(DLinkedNode node)
    {
        node.pre=head;
        node.next=head.next;
        head.next.pre=node;
        head.next=node;
    }
    public void removeNode(DLinkedNode node)
    {
        node.next.pre=node.pre;
        node.pre.next=node.next;
    }
    public void toHead(DLinkedNode node)
    {
        removeNode(node);
        addNode(node);
    }
    public void deleteTail()
    {
        tail.pre=tail.pre.pre;
        tail.pre.next=tail;
        
    }
    Map<Integer,DLinkedNode> map=new HashMap<Integer,DLinkedNode>();
    public LRUCache(int capacity) {
        this.capacity=capacity;
    
        head=new DLinkedNode();
        tail=new DLinkedNode();
        head.pre=null;
        head.next=tail;
        tail.next=null;
        tail.pre=head;
    }
    
    public int get(int key) {
        if (map.containsKey(key))
        {
            toHead(map.get(key));
            return map.get(key).val;
        }
        return -1;
    }
    
    public void set(int key, int value) {
        if (map.containsKey(key)) 
        {
            map.get(key).val=value;
            toHead(map.get(key));
        }else
        {
            DLinkedNode node=new DLinkedNode();
            node.val=value;
            node.key=key;
            addNode(node);
            map.put(key,node);
            if (map.size()>capacity)
            {
                DLinkedNode Lnode=tail.pre;
                map.remove(Lnode.key);
                deleteTail();
            }
        }
        
    }
}
