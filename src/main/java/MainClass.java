import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MainClass {
    public static void main(String[] arg) {
        List<String> list = Arrays.asList("Akshay", "BobTheBuilder", "CatDog", "DoggyStyle"
                , "ElfAndTheCorks", "FuckBitch");
        Observable<Integer> observable = Observable.fromIterable(list)
                .map(String::length)
                .filter(l -> l > 3);
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Received " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        observable.subscribe(s -> System.out.println("Observer1: " + s), Throwable::printStackTrace,
                () -> {
                    Thread.sleep(10000);
                    System.out.println("Observer1: Done");
                });
        observable.subscribe(s -> System.out.println("Observer2: " + s), Throwable::printStackTrace,
                () -> System.out.println("Observer2: Done"));
    }

    /**
     * ConnectableObservable is a hot observable, which start emitting when connect()
     * is called, if observer subscribe to it after connect() call, it will miss
     * emission fired previously.
     * ConnectableObservable supports multi-casting as it forces each emission to
     * each observable simultaneously
     */
    void getConnectableObservable() {

        ConnectableObservable<String> source =
                Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
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
        Observable<Integer> rangeObservable = Observable.range(10, 10);
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
        Observable<Long> seconds = Observable.interval(1,
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
        Observable.fromFuture(f)
                .subscribe(System.out::println);
    }

    /**
     * Observable.empty() Observable.never()
     */
}
