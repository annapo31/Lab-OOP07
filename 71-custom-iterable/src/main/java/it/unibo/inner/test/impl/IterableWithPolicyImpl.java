package it.unibo.inner.test.impl;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private List<T> list;
    private Predicate<T> filter;

    /**
     * Constructor of the class. It creates a new object of IterableWithPolicyImpl
     * 
     * @param elements array with the elements to set our list
     */
    public IterableWithPolicyImpl (final T[] elements){
        // Si fa con classe anonima che ritorna sempre un true
        this(elements, new Predicate<T>() {
            @Override
                public boolean test(T elem) { return true; }
            }
        );
    }

    /** 
     * Constructor of the class. It creates a new object of IterableWithPolicyImpl
     * 
     * @param elements array with the elements to set our list
     * @param filter will be used to filter the elements during the iteration
     */
    public IterableWithPolicyImpl (final T[] elements, final Predicate<T> filter){
        List<T> list = List.of(elements);

        /*for(int i = 0; i < elements.length; i++) {
            array_list.add(i, elements[i]);
        }*/
        this.list = list;
        this.filter = filter;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter){
        this.filter = filter;
    }

    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    class IteratorImpl implements Iterator<T> {

        private int position = 0;

        /* previous implementation
        @Override
        public boolean hasNext(){
            return (position < list.size());
        }

        @Override
        public T next() {
            if(hasNext()) {
                return list.get(position++);
            }
            throw new NoSuchElementException("You've reached the end of the list");
        }*/

        @Override
        public boolean hasNext(){
            boolean succeded = false;
            while(position < list.size() && succeded == false){
                succeded = filter.test(list.get(position));
                if(!succeded) {
                    position++;
                }
            }
            return succeded;
        }

        @Override
        public T next() {
            if(hasNext()) {
                return list.get(position++);
            }
            throw new NoSuchElementException("You've reached the end of the list");
        }
    }
}
