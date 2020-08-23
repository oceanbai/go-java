package dream.level.one.load;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * AsyncLoadingCache是继承自LoadingCache类的，
 * 异步加载使用Executor去调用方法并返回一个CompletableFuture。异步加载缓存使用了响应式编程模型。
 *
 * 如果要以同步方式调用时，应提供CacheLoader。
 * 要以异步表示时，应该提供一个AsyncCacheLoader，并返回一个CompletableFuture。
 * @author ocean
 * @date 2020/8/23
 */
public class AsyncLoad {
    /**
     * 异步加载
     *
     * @param key
     * @return
     */
    public Object asyncOperator(String key){
        AsyncLoadingCache<String, Object> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .buildAsync(k -> setAsyncValue(key).get());

        return cache.get(key);
    }

    public CompletableFuture<Object> setAsyncValue(String key){
        return CompletableFuture.supplyAsync(() -> {
            return key + "value";
        });
    }
}
