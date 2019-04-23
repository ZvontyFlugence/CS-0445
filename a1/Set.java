package cs445.a1;

public class Set<E> implements SetInterface<E> {
    private E[] contents;
    private int size;
    private final int DEFAULT_SIZE = 25;

    public Set() {
        contents = (E[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    public Set(int capacity) {
        if(capacity < 0) throw new IllegalArgumentException("Capacity cannot be less than 0.");
        contents = (E[]) new Object[capacity];
        size = 0;
    }

    public Set(E[] entries) {
        this();
        for (E item : entries) {
            this.add(item);
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(E newEntry) throws NullPointerException {
        if(newEntry == null) throw new NullPointerException("Cannot add a null object");
        if(!this.contains(newEntry)) {
            if((size+1) > contents.length) {
                E[] tempContents = (E[]) new Object[size+25];
                for(int i=0; i < contents.length; i++) {
                    tempContents[i] = contents[i];
                }
                contents = tempContents;
            }
            contents[size] = newEntry;
            size++;
            return true;
        } else {
            return false;
        }
    }

    public E remove(E entry) throws NullPointerException {
        if(entry == null) throw new NullPointerException("Cannot remove a null object");
        if(!this.isEmpty()) {
            if(this.contains(entry)) {
                E[] temp = (E[]) new Object[contents.length];
                int j = 0;
                int removeIndex = -1;
                for(int i=0; i<contents.length; i++) {
                    if(entry.equals(contents[i])) {
                        removeIndex = i;
                    } else {
                        temp[j] = contents[i];
                        j++;
                    }
                }
                E result = contents[removeIndex];
                contents = temp;
                size--;
                return result;
            }
        }
        return null;
    }

    public E remove() {
        if(!this.isEmpty()) {
            E elem = contents[size-1];
            contents[size-1] = null;
            return elem;
        } else {
            return null;
        }
    }

    public void clear() {
        if(!this.isEmpty()) {
            contents = (E[]) new Object[DEFAULT_SIZE];
            size = 0;
        }
    }

    public boolean contains(E entry) throws NullPointerException {
        if(entry == null) throw new NullPointerException("Cannot find object of type null");
        for(E item : contents) {
            if(entry.equals(item)) return true;
        }
        return false;
    }

    public Object[] toArray() {
        return contents;
    }
}
