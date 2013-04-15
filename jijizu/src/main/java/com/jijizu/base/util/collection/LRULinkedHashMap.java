package com.jijizu.base.util.collection;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V>
{
    private static final long serialVersionUID = -4511831672741864848L;
    private int coreSize;

    public LRULinkedHashMap(int coreSize)
    {
        super(coreSize + 1, 1.1f, true);
        this.coreSize = coreSize;
    }

    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest)
    {
        return size() > coreSize;
    }
}
