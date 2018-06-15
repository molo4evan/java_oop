package Classes.Factory;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SynchronousFileWriter{
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private BufferedWriter writer;

    SynchronousFileWriter(String s) throws IOException{
        writer = new BufferedWriter(new FileWriter(s, true));
        writer.write("\n");
    }

    public void write(String s) throws IOException{
        lock.writeLock().lock();
        try {
            writer.write(s);
            writer.flush();
        }
        finally{
            lock.writeLock().unlock();
        }
    }

    public void close() throws IOException{
        writer.close();
    }
}
