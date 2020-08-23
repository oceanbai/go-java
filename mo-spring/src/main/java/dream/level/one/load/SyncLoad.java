package dream.level.one.load;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 同步加载
 * @author ocean
 * @date 2020/8/23
 */
public class SyncLoad {
    /**
     *
     * 构造Cache时候，build方法传入一个CacheLoader实现类。实现load方法，通过key加载value。
     * @param key
     * @return
     */
    public Object syncOperator(String key){
        LoadingCache<String, Object> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(k -> setValue(key).apply(key));
        return cache.get(key);
    }

    public Function<String, Object> setValue(String key){
        return t -> key + "value";
    }
}
