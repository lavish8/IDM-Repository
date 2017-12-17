package com.identity.manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;

class SingletonImpl implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	public static SingletonImpl singleInstance = null;

    private SingletonImpl() {

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return singleInstance;
    };

    public Object readResolve() {
        return SingletonImpl.getInstance(); // 
    }

    public static SingletonImpl getInstance() {

        if (null == singleInstance) {
            singleInstance = new SingletonImpl();
        }
        return singleInstance;
    }
    
    public static void main(String[] args) {
    	SingletonImpl object1 = SingletonImpl.getInstance();
        System.out.println("Object1:" + object1);

        try {
            FileOutputStream fos = new FileOutputStream("abc.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object1);

            FileInputStream fis = new FileInputStream("abc.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            SingletonImpl object2 = (SingletonImpl) ois.readObject();
            System.out.println("Object2" + object2);

        } catch (Exception e) {
            // TODO: handle exception
        }
        try {
            Constructor[] constructors = SingletonImpl.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                // Below code will not destroy the singleton pattern
                constructor.setAccessible(true);
                SingletonImpl Object3 = (SingletonImpl) constructor.newInstance();
                System.out.println("Object3: Break through Reflection:" + Object3);
                break;
            }
        } catch (Exception ew) {

        }
	}

}

