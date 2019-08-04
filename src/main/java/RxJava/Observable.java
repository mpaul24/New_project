package RxJava;

import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Observable {
    /**
     * ConnectableObservable is a hot observable, which start emitting when connect()
     * is called, if observer subscribe to it after connect() call, it will miss
     * emission fired previously.
     * ConnectableObservable supports multi-casting as it forces each emission to
     * each observable simultaneously
     */
    void getConnectableObservable() {

        ConnectableObservable<String> source =
                io.reactivex.Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                        .publish();
        //Set up observer 1
        source.subscribe(s -> System.out.println("Observer 1: " + s));
        //Set up observer 2
        source.map(String::length)
                .subscribe(i -> System.out.println("Observer 2: " + i));
        //Fire!
        source.connect();
    }

    /**
     * Observable.range(start value, number of emission)
     * long equivalent is Observable.rangeLong()
     */
    void getRangeObservable() {
        io.reactivex.Observable<Integer> rangeObservable = io.reactivex.Observable.range(10, 10);
        rangeObservable.subscribe(System.out::println);
    }

    /**
     * Observable.interval(time, time unit)
     * emit indefinitely at specified interval
     * It runs on Computation Scheduler.
     * It is a cold observable, but can be converted to hot observable
     * using ConnectableObservable
     */
    void getIntervalObservable() throws InterruptedException {
        io.reactivex.Observable<Long> seconds = io.reactivex.Observable.interval(1,
                TimeUnit.SECONDS);
        //Observer 1
        seconds.subscribe(l -> System.out.println("Observer 1: " + l));
        //sleep 5 seconds
        Thread.sleep(5000);
        //Observer 2
        seconds.subscribe(l -> System.out.println("Observer 2: " + l));
        //sleep 5 seconds
        Thread.sleep(5000);
    }

    /**
     * Observable.future()
     * convert a Future value yielded to Observable
     */
    void getFutureObservable(Future<Integer> f){
        io.reactivex.Observable.fromFuture(f)
                .subscribe(System.out::println);
    }

    /**
     * Observable.empty() Observable.never()
     */

    /**
     *  If your Observable source was implemented naively and behaves brokenly
     *  with more than one Observer (for example, it reuses an Iterator that only
     *  iterates data once), Observable.defer() provides a quick workaround for this
     *  as well.
     *
     *  Cold Observable
     */
    private int count = 5;
    void getDeferredObservable(){
        int start = 0;
        io.reactivex.Observable<Integer> observable = io.reactivex.Observable.defer(()->
                io.reactivex.Observable.range(start, count));
        observable.subscribe(System.out::println);
        count = 10;
        observable.subscribe(System.out::println);
    }

    /**
     * Observable.fromCallable()
     * If we are going to be reactive in our error handling, this may not
     * be desirable. Perhaps you would like the error to be emitted down
     * the chain to the Observer where it will be handled. If that is the
     * case, use Observable.fromCallable() instead, as it accepts a lambda
     * Supplier<T> and it will emit any error that occurs down to Observer:
     */

    void getCallableObservable(){
        io.reactivex.Observable.fromCallable(() -> 1/0)
                .subscribe(System.out::println, Throwable::printStackTrace);
    }
}
