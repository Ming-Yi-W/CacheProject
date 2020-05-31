package org.cachestudy.writeitbyself.store.impl;

import java.lang.ref.WeakReference;

import org.cachestudy.writeitbyself.store.ValueHolder;

public class WeakValueHolder<V> implements ValueHolder<V> {

	private WeakReference<V> v;
	
	public WeakValueHolder(V value) {
		super();
		if (null == value) {
			return;
		}
		this.v = new WeakReference<V>(value);
	}

	

	@Override
	public V value() {
		return this.v.get();
	}

}
