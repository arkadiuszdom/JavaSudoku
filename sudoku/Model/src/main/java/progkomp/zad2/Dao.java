package progkomp.zad2;

public interface Dao<T> {

    public void write(T obj) throws Exception;

    public T read() throws Exception;

}
