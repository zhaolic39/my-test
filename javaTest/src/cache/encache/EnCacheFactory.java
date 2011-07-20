package cache.encache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import cache.CacheFactory;

/**
 * <p>Title: encache组件实现的缓存管理</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * @author Zhaoli
 * @version 1.0 Apr 27, 2011
 */
public class EnCacheFactory extends CacheFactory{
  private CacheManager manager = null;
  
  public EnCacheFactory() {
    manager = CacheManager.create();
  }
  
  protected <T>T getCacheObj(String cache_name, String key, Class<T> clz) {
    Cache cache = manager.getCache(cache_name);
    Element ele = cache.get(key);
    Object obj = null;
    if(ele != null) {
      obj = ele.getObjectValue();
    }
    return clz.cast(obj);
  }

  protected void setCacheObj(String cache_name, String key, Object obj) {
    if(obj == null) return;
    Cache cache = manager.getCache(cache_name);
    Element ele = new Element(key, obj);
    cache.put(ele);
  }

  public void clearCache(String cache_name) {
    Cache cache = manager.getCache(cache_name);
    cache.removeAll();
  }

  public void updateCacheObj(String cache_name, String key) {
    Cache cache = manager.getCache(cache_name);
    cache.remove(key);
  }
}
