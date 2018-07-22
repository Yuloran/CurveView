package com.yuloran.demo.ui.rxjava2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.yuloran.demo.R;
import com.yuloran.demo.model.bean.IBook;
import com.yuloran.demo.model.bean.Novel;
import com.yuloran.demo.model.bean.NovelAdapter;
import com.yuloran.demo.model.bean.RxJava2Tutorial;
import com.yuloran.demo.model.bean.RxJava2TutorialAdapter;
import com.yuloran.demo.model.bean.UserInfo;
import com.yuloran.demo.util.Logger;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author & Date: Yuloran, 2018/7/22 8:54
 * Function:
 */
public class RxJava2Activity extends Activity {
    private static final String TAG = RxJava2Activity.class.getSimpleName();

    private EditText mEditText;
    private TextView mTextView;

    private Random mRandom = new SecureRandom();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rxjava2);

        mEditText = (EditText) findViewById(R.id.edit_text);
        mTextView = (TextView) findViewById(R.id.textView);

        test();
    }

    @SuppressLint("CheckResult")
    private void test() {
        // case1: 在非UI线程执行不关注结果
        // (1) fromCallable
        Single.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Logger.d(TAG, "test: fromCallable() invoked on %s", Thread.currentThread().getName());
                return generateRandom();
            }
        }).subscribeOn(Schedulers.io()).subscribe();
        // (2) defer
        Single.defer(new Callable<SingleSource<Integer>>() {
            @Override
            public SingleSource<Integer> call() throws Exception {
                Logger.d(TAG, "test: defer() invoked on %s", Thread.currentThread().getName());
                return Single.just(generateRandom());
            }
        }).subscribeOn(Schedulers.io()).subscribe();

        // case2: 在非UI线程执行并关注结果
        Single.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return generateRandom();
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.d(TAG, "test: accept(Integer integer) invoked on %s", Thread.currentThread().getName());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Logger.d(TAG, "test: accept(Throwable throwable) invoked on %s", Thread.currentThread().getName());
            }
        });

        // case2: 在非UI线程执行但在UI线程关注结果
        Single.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return generateRandom();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.d(TAG, "test: accept(Integer integer) invoked on %s", Thread.currentThread().getName());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Logger.d(TAG, "test: accept(Integer integer) invoked on %s", Thread.currentThread().getName());
            }
        });

        // case3: 变换返回值
        Single.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return generateRandom();
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return String.valueOf(integer + "_mapped");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Logger.d(TAG, "test: " + s);
            }
        });

        // case4: 按顺序做某事且下一件事依赖上一件事的结果[常用于网络请求接口依赖时]
        Single.defer(new Callable<SingleSource<String>>() {
            @Override
            public SingleSource<String> call() throws Exception {
                return getUserId();
            }
        }).flatMap(new Function<String, SingleSource<UserInfo>>() {
            @Override
            public SingleSource<UserInfo> apply(String userId) throws Exception {
                return getUserInfo(userId);
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Consumer<UserInfo>() {
            @Override
            public void accept(UserInfo userInfo) throws Exception {
                Logger.d(TAG, "test: get userInfo success: " + userInfo);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Logger.e(TAG, "test: get userInfo error.", throwable);
            }
        });

        // case5: 并发读取不同数据源，转换成同类型后，合并
        Single<IBook> novel = Single.fromCallable(new Callable<Novel>() {
            @Override
            public Novel call() throws Exception {
                return getNovel();
            }
        }).map(new Function<Novel, IBook>() {
            @Override
            public IBook apply(Novel novel) throws Exception {
                return new NovelAdapter(novel);
            }
        }).subscribeOn(Schedulers.io());

        Single<IBook> rxJava2Tutorial = Single.fromCallable(new Callable<RxJava2Tutorial>() {
            @Override
            public RxJava2Tutorial call() throws Exception {
                return getRxJava2Tutorial();
            }
        }).map(new Function<RxJava2Tutorial, IBook>() {
            @Override
            public IBook apply(RxJava2Tutorial rxJava2Tutorial) throws Exception {
                return new RxJava2TutorialAdapter(rxJava2Tutorial);
            }
        }).subscribeOn(Schedulers.io());

        Single.zip(novel, rxJava2Tutorial, new BiFunction<IBook, IBook, List<IBook>>() {
            @Override
            public List<IBook> apply(IBook iBook, IBook iBook2) throws Exception {
                List<IBook> books = new ArrayList<>(2);
                books.add(iBook);
                books.add(iBook2);
                return books;
            }
        }).subscribe(new Consumer<List<IBook>>() {
            @Override
            public void accept(List<IBook> iBooks) throws Exception {
                Logger.d(TAG, "test: books are " + iBooks);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Logger.d(TAG, "test: get books error.", throwable);
            }
        });

        // case6: 搜索频率限制
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                mEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (!emitter.isDisposed()) {
                            emitter.onNext(s.toString().trim());
                        }
                    }
                });
            }
        }).debounce(200, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String keyword) throws Exception {
                        mTextView.setText(search(keyword));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e(TAG, "test: emitter keyword error.", throwable);
                    }
                });
    }

    private String search(String keyword) {
        if (keyword.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < generateRandom(); i++) {
            sb.append(keyword).append('_').append(i).append('\n');
        }
        return sb.toString();
    }

    private RxJava2Tutorial getRxJava2Tutorial() {
        RxJava2Tutorial tutorial = new RxJava2Tutorial();
        tutorial.setTitle("RxJava2 Tutorial");
        tutorial.setAuthor("Android Noob");
        return tutorial;
    }

    private Novel getNovel() {
        Novel novel = new Novel();
        novel.setSerialNumber(1);
        novel.setName("The Crazy Disk");
        return novel;
    }

    private int generateRandom() {
        return mRandom.nextInt(100);
    }

    /**
     * 模拟网络请求
     *
     * @return {@link Single}
     */
    private Single<String> getUserId() {
        return Single.just("USER_ID_1234567");
    }

    /**
     * 模拟网络请求
     *
     * @param userId userId
     * @return {@link Single}
     */
    private Single<UserInfo> getUserInfo(String userId) {
        UserInfo userInfo = new UserInfo(userId, "test");
        return Single.just(userInfo);
    }


}
