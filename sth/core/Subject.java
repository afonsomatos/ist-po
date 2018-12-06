package sth.core;

interface Subject<T> {

	void attach(Observer<T> obs);
	void dettach(Observer<T> obs);
	void notifyObservers(T obj);
	
}
