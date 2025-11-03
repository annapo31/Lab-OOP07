package it.unibo.inner.test.impl;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private List<T> list;

    /**
     * Constructor of the class. It creates a new object of IterableWithPolicyImpl
     * 
     * @param elements array with the elements to set our list
     */
    public IterableWithPolicyImpl (final T[] elements){
        List<T> list = List.of(elements);

        /*for(int i = 0; i < elements.length; i++) {
            array_list.add(i, elements[i]);
        }*/

        this.list = list;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter){
    }

    public Iterator<T> iterator() {
        return new IteratorImpl();

    }

    class IteratorImpl implements Iterator<T> {

        private int position = 0;

        @Override
        public boolean hasNext(){
           return (position < list.size());
        }

        @Override
        public T next() {
            if(hasNext()) {
                return list.get(position++);
            }
            throw new NoSuchElementException("You're reached the end of the list");
        }
    }
}
