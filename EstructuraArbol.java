public interface EstructuraArbol<K,V> {

	public void add (K key,V value);
	
	public V get (K key);
	
	public V remove (K key);
	
	public int count ();
	
	public boolean isEmpty();
}