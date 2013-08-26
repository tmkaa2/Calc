package kaa.calculator.controler.rpn.stack;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.Green
 * Date: 25.08.13
 * Time: 7:17
 * To change this template use File | Settings | File Templates.
 */
    public class StackList<E> extends ArrayList<E> {
        private static final long serialVersionUID = 1L;

        public E pop() {
            if(size() !=0){
                E e = get(size() - 1);
                remove(size() - 1);
                return e;
            } else {
                return null;
            }
        }

        public E getTopElement() {
            if(size() !=0){
                E e = get(size() - 1);
                return e;
            } else {
                return null;
            }
        }

        public void push(E e) {
            add(e);
        }

    }

