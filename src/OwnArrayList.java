import java.util.*;

public class OwnArrayList<T> implements List<T> {
    private static final int BASIC_SIZE_OF_ARRAY = 10;

    private static final Object[] EMPTY_ARRAY = {};

    private int size;

    private Object[] basicDataArray;

    public OwnArrayList(int ownedCapacity) {
        if (ownedCapacity > 0) {
            this.basicDataArray = new Object[ownedCapacity];
        } else if (ownedCapacity == 0) {
            this.basicDataArray = EMPTY_ARRAY;
        } else {
            throw new IllegalArgumentException("Illegal capacity" + ownedCapacity);
        }
    }

    public OwnArrayList() {
        this.basicDataArray = new Object[BASIC_SIZE_OF_ARRAY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Unexpected index " + "'" + index + "'" + "or no Object in this position");
        }
    }

    private void checkIsFullArray() {
        if (size == basicDataArray.length) {
            basicDataArray = Arrays.copyOf(basicDataArray, basicDataArray.length + 10);
        }
    }

    @Override
    public boolean add(T t) {
        checkIsFullArray();

        basicDataArray[size] = t;
        this.size++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);
        checkIsFullArray();
        //check is empty slot

        if (basicDataArray[index] != null) {
            System.arraycopy(basicDataArray, index, basicDataArray, index + 1, size - index);//bugaga
        }
        basicDataArray[index] = element;
        this.size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIndex(index);

        return (T) basicDataArray[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public T set(int index, T element) {
        checkIndex(index);

        return (T) (basicDataArray[index] = element);
    }

    @Override
    public boolean contains(Object o) {
        for (int arrayIndex = 0; arrayIndex < size; arrayIndex++) {
            if (o.equals(basicDataArray[arrayIndex])) {
                return true;
            }
        }
        return false;//use stream
    }

    @Override
    public boolean remove(Object o) {
        for (int arrayIndex = 0; arrayIndex < size; arrayIndex++) {
            if (o.equals(basicDataArray[arrayIndex])) {
                System.arraycopy(basicDataArray, arrayIndex + 1/*if last then IndexOutOfBouns*/, basicDataArray, arrayIndex, size - arrayIndex - 1);//bugaga again lol)
                this.size--;
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkIndex(index);

        System.arraycopy(basicDataArray, index + 1, basicDataArray, index, size - index - 1);//the same as above
        this.size--;
        return (T) basicDataArray[index];
    }

    @Override
    public int indexOf(Object o) {
        for (int arrayIndex = 0; arrayIndex < size; arrayIndex++) {
            if (o.equals(basicDataArray[arrayIndex])) {
                return arrayIndex;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        for (int arrayIndex = size; arrayIndex > 0; arrayIndex--) {//get start from end
            if (o.equals(basicDataArray[arrayIndex])) {
                lastIndex = arrayIndex;
            }
        }
        return lastIndex;
    }


    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            Object[] elements = OwnArrayList.this.basicDataArray;
            if (i >= elements.length) {
                throw new ConcurrentModificationException();
            }

            cursor = i + 1;
            return (T) (elements[i]);
        }
    }

    @Override
    public void clear() {
        basicDataArray = new Object[BASIC_SIZE_OF_ARRAY];
        size = 0;
    }

    @Override
    public Object[] toArray() {
        return basicDataArray;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    private void checkNewListCapacity(int newCollSize) {
        if (newCollSize > basicDataArray.length) {
            int i = 1;
            while ((newCollSize % 10) != 0) {
                newCollSize += i;
            }
            basicDataArray = Arrays.copyOf(basicDataArray, newCollSize);
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] arr = c.toArray();
        int newCollectionSize = c.size() + OwnArrayList.this.size();

        checkNewListCapacity(newCollectionSize);

        System.arraycopy(arr, 0, basicDataArray, size, c.size());
        this.size += c.size();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Object[] arr = c.toArray();
        int newCollectionSize = c.size() + OwnArrayList.this.size();

        checkNewListCapacity(newCollectionSize);

        System.arraycopy(basicDataArray, index, basicDataArray, c.size() + index , OwnArrayList.this.size - index);
        System.arraycopy(arr, 0, basicDataArray, index, c.size());
        size += c.size();
        return true;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    ///////////////////////////////////////

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

}
