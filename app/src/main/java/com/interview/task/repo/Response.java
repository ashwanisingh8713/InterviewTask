package com.interview.task.repo;

/**
 * Created by ashwanisingh on 24/06/18.
 */
public interface Response<T> {

    void onNext(T t);
    void onError(Throwable throwable);
    void onComplete();

}
